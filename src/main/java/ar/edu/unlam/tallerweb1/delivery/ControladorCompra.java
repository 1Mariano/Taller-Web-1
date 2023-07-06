package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.enums.EstadoEnvio;
import ar.edu.unlam.tallerweb1.domain.enums.EstadoPago;
import ar.edu.unlam.tallerweb1.domain.enums.EstadoPedido;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.envio.ServicioEnvio;
import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.pedidos.ServicioCompra;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.CampoInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ControladorCompra {

    private final ServicioCompra servicioCompra;
    private final ServicioEnvio servicioEnvio;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession sesion;

    @Autowired
    public ControladorCompra(ServicioCompra servicioCompra, ServicioEnvio servicioEnvio) {
        this.servicioCompra = servicioCompra;
        this.servicioEnvio = servicioEnvio;
    }

    @RequestMapping("/compra")
    public ModelAndView compra() {
        ModelMap model = new ModelMap();

        model.put("datosBuscador", new DatosBuscador());

        List<Producto> productos = (List<Producto>) request.getSession().getAttribute("carritoCompleto");

        request.getSession().setAttribute("arrayProductos", productos);

        model.put("productos", productos);
        model.put("datosEnvio", new DatosEnvio());
        return new ModelAndView("/compra", model);
    }

    @RequestMapping(path = "/validar-datos-envio", method = RequestMethod.POST)
    public ModelAndView validarDatosEnvio(@ModelAttribute("datosEnvio") DatosEnvio datosEnvio,
                                          HttpServletRequest request, HttpServletResponse response) {
        ModelMap modelo = new ModelMap();

        modelo.put("datosBuscador", new DatosBuscador());

        Long usuario = (Long) this.request.getSession().getAttribute("idUsuario");
        Envio envioNuevo = new Envio();
        Pedido pedidoNuevo = new Pedido();
        List<Producto> productos = (List<Producto>) request.getSession().getAttribute("arrayProductos");

        try {
            envioNuevo.setCalle(datosEnvio.getCalle());
            envioNuevo.setNumero(datosEnvio.getNumero());
            envioNuevo.setPisoODepartamento(datosEnvio.getPisoODepartamento());
            envioNuevo.setCodigoPostal(datosEnvio.getCodigoPostal());
            envioNuevo.setLocalidad(datosEnvio.getLocalidad());
            envioNuevo.setVehiculo(this.servicioEnvio.obtenerVehiculoDePedido(envioNuevo));
            envioNuevo.setEstadoEnvio(EstadoEnvio.EN_ESPERA);
            this.servicioCompra.guardarDatosEnvio(envioNuevo);
            this.servicioCompra.empaquetarProductos(productos, envioNuevo);
            this.servicioEnvio.agregarAlVehiculo(envioNuevo);
            this.servicioEnvio.modificarEnvio(envioNuevo);

            if (!this.servicioCompra.verificarSiExistePedidoActivo(usuario)) {
                pedidoNuevo.setUsuario((Usuario) request.getSession().getAttribute("usuario"));
                pedidoNuevo.setFechaPedido(LocalDate.now());
                pedidoNuevo.setEstado(EstadoPedido.CREADO);
                pedidoNuevo.setEstadoPago(EstadoPago.NO_PAGADO);
                pedidoNuevo.setEnvio(envioNuevo);
                pedidoNuevo.setCostoTotal(this.servicioCompra.obtenerCostoTotalDelPedido(pedidoNuevo, envioNuevo));
                request.getSession().setAttribute("precioTotal", pedidoNuevo.getCostoTotal());
                this.servicioCompra.agregarPedido(pedidoNuevo);
                request.getSession().setAttribute("pedido", pedidoNuevo);
                request.getSession().setAttribute("numeroPedido", pedidoNuevo.getId());
            }

            request.getSession().setAttribute("envio", envioNuevo);
            request.getSession().setAttribute("numeroEnvio", envioNuevo.getId());

        } catch (CampoInvalidoException e) {
            return registroDeEnvioFallido(modelo, "El campo debe tener al menos 2 caracteres");
        }

        /*ModelMap model = new ModelMap();
        model.put("numeroPedido", envioNuevo);*/
        /*Contenedor_Producto envio = new Contenedor_Producto();
        envio.setEnvio(envioNuevo);*/
        //request.getSession().setAttribute("numeroPedido", envioNuevo);
        return new ModelAndView("redirect:/pago");
    }

    private ModelAndView registroDeEnvioFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        modelo.put("productos", request.getSession().getAttribute("arrayProductos"));
        return new ModelAndView("compra", modelo);
    }

}
