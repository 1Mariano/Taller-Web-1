package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor_Producto;
import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.envio.ServicioEnvio;
import ar.edu.unlam.tallerweb1.domain.pedidos.ServicioCompra;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.RepositorioVehiculo;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;
import ar.edu.unlam.tallerweb1.exceptions.CampoInvalidoException;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioVehiculoImpl;
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
import java.util.*;

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
        Envio envioNuevo = new Envio();
        try {

            envioNuevo.setCalle(datosEnvio.getCalle());
            envioNuevo.setNumero(datosEnvio.getNumero());
            envioNuevo.setPisoODepartamento(datosEnvio.getPisoODepartamento());
            envioNuevo.setCodigoPostal(datosEnvio.getCodigoPostal());
            envioNuevo.setLocalidad(datosEnvio.getLocalidad());
            envioNuevo.setVehiculo(this.servicioEnvio.obtenerVehiculoDePedido(envioNuevo));
            this.servicioCompra.guardarDatosEnvio(envioNuevo);
        } catch (CampoInvalidoException e) {
            return registroDeEnvioFallido(modelo, "El campo debe tener al menos 2 caracteres");
        }

        List<Producto> productos = (List<Producto>) request.getSession().getAttribute("arrayProductos");
        Long usuario = (Long) this.request.getSession().getAttribute("idUsuario");
        //TODO creacion de contenedores ver y separar responsabilidades
        this.servicioCompra.empaquetarProductos(productos, envioNuevo);

        this.servicioEnvio.agregarAlVehiculo(envioNuevo);

        /*ModelMap model = new ModelMap();
        model.put("numeroPedido", envioNuevo);*/
        /*Contenedor_Producto envio = new Contenedor_Producto();
        envio.setEnvio(envioNuevo);*/
        //request.getSession().setAttribute("numeroPedido", envioNuevo);
        request.getSession().setAttribute("numeroEnvio", envioNuevo.getId());
        return new ModelAndView("redirect:/pago");
    }

    @RequestMapping("/pago")
    public ModelAndView pago() {
        ModelMap model = new ModelMap();

        model.put("datosBuscador", new DatosBuscador());

        model.put("numeroEnvio", request.getSession().getAttribute("numeroEnvio"));
        Double peso = this.servicioCompra.obtenerPesoTotalDeLosContenedores();
        Double volumen = this.servicioCompra.obtenerVolumenTotalDeLosContenedores();
        model.put("peso", peso);
        model.put("volumen", volumen);
        //List<Contenedor_Producto> contenedoresConProductos = this.servicioCompra.devolverContenedoresConProductos();
        Long numeroEnvio = (Long) request.getSession().getAttribute("numeroEnvio");
        //Todo separar responsabilidades desde el respositorio. Necesito dos arrays para bolsa o caja distintos

        List<Contenedor> bolsas = this.servicioCompra.obtenerBolsasPorEnvio(numeroEnvio);
        Set<Long> idContenedor = new HashSet<>();
        for (Contenedor bolsa: bolsas) {
            idContenedor.add(bolsa.getId());
        }

        Map<Long, List<Producto>> addBolsas = new HashMap<>();
        for (Long contId : idContenedor) {
            List<Producto> productosPorContenedor = this.servicioCompra.obtenerProductosDeUnContenedor(contId);
            addBolsas.put(contId, productosPorContenedor);
        }

        // Cajas
        List<Contenedor> cajas = this.servicioCompra.obtenerCajasPorEnvio(numeroEnvio);
        Set<Long> idContenedorCaja = new HashSet<>();
        for (Contenedor caja: cajas) {
            idContenedorCaja.add(caja.getId());
        }

        Map<Long, List<Producto>> addCajas = new HashMap<>();
        for (Long contId : idContenedorCaja) {
            List<Producto> productosPorContenedorCaja = this.servicioCompra.obtenerProductosDeUnContenedor(contId);
            addCajas.put(contId, productosPorContenedorCaja);
        }




        //this.servicioCompra.obtenerProductosDeUnContenedor(caja.getId());
        model.put("bolsas", addBolsas);
        model.put("cajas", addCajas);



       // model.put("costoEnvio", this.servicioEnvio.calcularCostoEnvio());
        return new ModelAndView("/pago", model);
    }

    private ModelAndView registroDeEnvioFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        modelo.put("productos", request.getSession().getAttribute("arrayProductos"));
        return new ModelAndView("compra", modelo);
    }

}
