package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.envio.ServicioEnvio;
import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.pedidos.ServicioCompra;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.exceptions.NoSeConcretoElPagoException;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
        Long numeroPedido = (Long) request.getSession().getAttribute("numeroPedido");

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

        model.put("numeroPedido", numeroPedido);
        model.put("bolsas", addBolsas);
        model.put("cajas", addCajas);

        model.put("costoProductos", this.servicioCompra.obtenerCostoTotalDeLosProductos((List<Producto>) request.getSession().getAttribute("arrayProductos")));
        model.put("costoEnvio", this.servicioEnvio.obtenerEnvio((Envio) request.getSession().getAttribute("envio")).getCostoEnvio());
        model.put("distancia", this.servicioEnvio.obtenerEnvio((Envio) request.getSession().getAttribute("envio")).getDistanciaEnKilometros());
        model.put("costoTotal", this.servicioCompra.obtenerCostoTotalDelPedido((Pedido) request.getSession().getAttribute("pedido"), (Envio) request.getSession().getAttribute("envio")));

        model.put("datosPago", new DatosPago());

        return new ModelAndView("/pago", model);
    }

    @RequestMapping(path = "/pagar", method = RequestMethod.POST)
    public ModelAndView pagar(@ModelAttribute("datosPago") DatosPago datosPago, HttpServletRequest request, HttpServletResponse responseServer) throws NoSeConcretoElPagoException {
        ModelMap modelo = new ModelMap();

        modelo.put("datosBuscador", new DatosBuscador());

        //return registroExitoso(modelo, "Pago confirmado");

        Double precioTotal = (Double) request.getSession().getAttribute("precioTotal");

        // iniciar el pago
        MercadoPagoConfig.setAccessToken("TEST-272116395368831-070419-c63b9de14d78e46ea5b2a5bb27fbfb3d-253329177");

        PreferenceClient client = new PreferenceClient();
        // Crea un ítem en la preferencia
        PreferenceItemRequest item = PreferenceItemRequest.builder().title("Pedido").quantity(1)
                .unitPrice(new BigDecimal(precioTotal)).build();
        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(item);
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("http://localhost:8080/proyecto_limpio_spring/pagoConfirmado")
                .failure("http://localhost:8080/proyecto_limpio_spring/pago")
                .build();
        PreferenceRequest prefRequest = PreferenceRequest.builder().autoReturn("approved").binaryMode(true).items(items)
                .backUrls(backUrls).build();
        Preference response = null;

        try {
            response = client.create(prefRequest);
        } catch (MPException | MPApiException e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:" + response.getInitPoint());
    }

    @RequestMapping(path = "/pagoConfirmado")
    public ModelAndView pagoConfirmado(@RequestParam Long payment_id) {
        ModelMap modelo = new ModelMap();

        /*if (payment_id == null) {
            return new ModelAndView("redirect:/home");
        }*/
        modelo.put("datosBuscador", new DatosBuscador());
        modelo.put("numeroPago", payment_id);

        Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");
        Envio envio = (Envio) request.getSession().getAttribute("envio");

        try {
            this.servicioCompra.pagar(pedido, envio);
            this.servicioCompra.modificarPedido(pedido);
            this.servicioEnvio.modificarEnvio(envio);
        } catch (NoSeConcretoElPagoException e) {
            return registroDePagoFallido(modelo, "No fue posible concretar el pago");
        }

        try {
            Payment payment = this.servicioCompra.verificarPago(payment_id);
            //this.servicioCompra.guardarComprobante(payment, (Long) request.getSession().getAttribute("idUsuario"));
        } catch (MPException | MPApiException e) {
            return new ModelAndView("redirect:/pago");
        }

        Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");
        this.servicioCompra.vaciarCarrito(idUsuario);

        return new ModelAndView("/pagoConfirmado", modelo);

        //return new ModelAndView("pagoConfirmado");
    }

    private ModelAndView registroDePagoFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        return new ModelAndView("pago", modelo);
    }
}
