package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.envio.ServicioEnvio;
import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.pedidos.ServicioCompra;
import ar.edu.unlam.tallerweb1.exceptions.NoSeConcretoElPagoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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

        model.put("numeroPedido", request.getSession().getAttribute("numeroPedido"));
        Double peso = this.servicioCompra.obtenerPesoTotalDeLosContenedores();
        Double volumen = this.servicioCompra.obtenerVolumenTotalDeLosContenedores();
        model.put("peso", peso);
        model.put("volumen", volumen);
        List<Contenedor> contenedoresConProductos = this.servicioCompra.devolverContenedoresConProductos();
        model.put("contenedores", contenedoresConProductos);
        model.put("distanciaEnvio", this.servicioEnvio.obtenerEnvio((Envio) request.getSession().getAttribute("envio")).getDistanciaEnKilometros());
        model.put("costoEnvio", this.servicioEnvio.obtenerEnvio((Envio) request.getSession().getAttribute("envio")).getCostoEnvio());
        return new ModelAndView("/pago", model);
    }

    @RequestMapping(path = "/pagar", method = RequestMethod.POST)
    public ModelAndView pagar(HttpServletRequest request, HttpServletResponse response) throws NoSeConcretoElPagoException {
        ModelMap modelo = new ModelMap();

        modelo.put("datosBuscador", new DatosBuscador());

        Pedido pedido = new Pedido();
        pedido = (Pedido) request.getSession().getAttribute("pedido");
        Envio envio = new Envio();
        envio = (Envio) request.getSession().getAttribute("envio");

        try {
            this.servicioCompra.pagar(pedido, envio);
            this.servicioCompra.modificarPedido(pedido);
            this.servicioEnvio.modificarEnvio(envio);
        } catch (NoSeConcretoElPagoException e) {
            return registroDePagoFallido(modelo, "No fue posible concretar el pago");
        }
        return registroExitoso(modelo, "Pago confirmado");
    }

    private ModelAndView registroExitoso(ModelMap modelo, String mensaje) {
        modelo.put("exito", mensaje);
        return new ModelAndView("pago", modelo);
    }

    private ModelAndView registroDePagoFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        return new ModelAndView("pago", modelo);
    }
}
