package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.envio.ServicioEnvio;
import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.pedidos.ServicioCompra;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.exceptions.NoSeConcretoElPagoException;
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
public class ControladorPago {

    private final ServicioCompra servicioCompra;
    private final ServicioEnvio servicioEnvio;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession sesion;

    @Autowired
    public ControladorPago(ServicioCompra servicioCompra, ServicioEnvio servicioEnvio) {

        this.servicioCompra = servicioCompra;
        this.servicioEnvio = servicioEnvio;
    }

    @RequestMapping("/pago")
    public ModelAndView pago() {
        ModelMap model = new ModelMap();

        model.put("datosBuscador", new DatosBuscador());

        Long numeroEnvio = (Long) request.getSession().getAttribute("numeroEnvio");

        List<Contenedor> bolsas = this.servicioCompra.obtenerBolsasPorEnvio(numeroEnvio);
        Set<Long> idContenedor = new HashSet<>();
        for (Contenedor bolsa : bolsas) {
            idContenedor.add(bolsa.getId());
        }

        Map<Long, List<Producto>> addBolsas = new HashMap<>();
        for (Long contId : idContenedor) {
            List<Producto> productosPorContenedor = this.servicioCompra.obtenerProductosDeUnContenedor(contId);
            addBolsas.put(contId, productosPorContenedor);
        }

        List<Contenedor> cajas = this.servicioCompra.obtenerCajasPorEnvio(numeroEnvio);
        Set<Long> idContenedorCaja = new HashSet<>();
        for (Contenedor caja : cajas) {
            idContenedorCaja.add(caja.getId());
        }

        Map<Long, List<Producto>> addCajas = new HashMap<>();
        for (Long contId : idContenedorCaja) {
            List<Producto> productosPorContenedorCaja = this.servicioCompra.obtenerProductosDeUnContenedor(contId);
            addCajas.put(contId, productosPorContenedorCaja);
        }

        model.put("numeroEnvio", numeroEnvio);
        model.put("bolsas", addBolsas);
        model.put("cajas", addCajas);

        model.put("costoProductos", this.servicioCompra.obtenerCostoTotalDeLosProductos((List<Producto>) request.getSession().getAttribute("arrayProductos")));
        model.put("costoEnvio", this.servicioEnvio.obtenerEnvio((Envio) request.getSession().getAttribute("envio")).getCostoEnvio());
        model.put("costoTotal", this.servicioCompra.obtenerCostoTotalDelPedido((Pedido) request.getSession().getAttribute("pedido"), (Envio) request.getSession().getAttribute("envio")));

        model.put("datosPago", new DatosPago());

        return new ModelAndView("/pago", model);
    }

    @RequestMapping(path = "/pagar", method = RequestMethod.POST)
    public ModelAndView pagar(@ModelAttribute("datosPago") DatosPago datosPago, HttpServletRequest request, HttpServletResponse response) {
        ModelMap modelo = new ModelMap();

        modelo.put("datosBuscador", new DatosBuscador());

        Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");
        Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");
        Envio envio = (Envio) request.getSession().getAttribute("envio");

        try {
            this.servicioCompra.pagar(pedido, envio);
            this.servicioCompra.modificarPedido(pedido);
            this.servicioEnvio.modificarEnvio(envio);

        } catch (NoSeConcretoElPagoException e) {
            return registroDePagoFallido(modelo, "No fue posible concretar el pago");
        }

        this.servicioCompra.vaciarCarrito(idUsuario);

        return new ModelAndView("redirect:/pago-confirmado");
    }

    @RequestMapping(path = "/pago-confirmado")
    public ModelAndView pagoConfirmado() {
        ModelMap modelo = new ModelMap();

        modelo.put("datosBuscador", new DatosBuscador());

        return new ModelAndView("/pago-confirmado", modelo);
    }

    private ModelAndView registroDePagoFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        return new ModelAndView("pago", modelo);
    }
}
