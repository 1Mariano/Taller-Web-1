package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.envio.ServicioEnvio;
import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.pedidos.ServicioCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
public class ControladorEstadoPedido {

    private final ServicioCompra servicioCompra;
    private final ServicioEnvio servicioEnvio;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession sesion;

    @Autowired
    public ControladorEstadoPedido(ServicioCompra servicioCompra, ServicioEnvio servicioEnvio) {
        this.servicioCompra = servicioCompra;
        this.servicioEnvio = servicioEnvio;
    }

    @RequestMapping("/estado-pedido")
    public ModelAndView estadoPedido() {
        ModelMap model = new ModelMap();

        model.put("datosBuscador", new DatosBuscador());

        Long usuario = (Long) this.request.getSession().getAttribute("idUsuario");
        List<Pedido> pedidos = this.servicioCompra.obtenerPedidosDeUnUsuario(usuario);




        for (Pedido p : pedidos) {
            if (p.getFechadeEnvio() == null && p.getVehiculo() == null) {
                p.setFechaPedido(p.getFechaPedido().plusDays(1));
                p.setFechadeEnvio(p.getFechaPedido().plusDays(1));
                p.setVehiculo(this.servicioEnvio.obtenerVehiculoDePedido(p.getEnvio()));
                this.servicioEnvio.actualizarPedido(p);
            }
        }

        model.put("pedidos", pedidos);
        //model.put("fechaEnvio", );


        return new ModelAndView("/estado-pedido", model);
    }
}