package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.pedidos.ServicioCompra;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
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
import java.util.List;

@Controller
public class ControladorCompra {

    private final ServicioCompra servicioCompra;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession sesion;

    @Autowired
    public ControladorCompra(ServicioCompra servicioCompra) {
        this.servicioCompra = servicioCompra;
    }



    @RequestMapping("/compra")
    public ModelAndView compra(){


        ModelMap model = new ModelMap();
        List<Producto> productos = (List<Producto>)  request.getSession().getAttribute("carritoCompleto");

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
            this.servicioCompra.guardarDatosEnvio(envioNuevo);
        } catch (CampoInvalidoException e) {
            return registroDeEnvioFallido(modelo, "El campo debe tener al menos 2 caracteres");
        }

        List<Producto> productos = (List<Producto>) request.getSession().getAttribute("arrayProductos");
        Long usuario =(Long) this.request.getSession().getAttribute("idUsuario");
        //TODO creacion de contenedores ver y separar responsabilidades
        this.servicioCompra.empaquetarProductos(productos, envioNuevo);
        /*ModelMap model = new ModelMap();
        model.put("numeroPedido", envioNuevo);*/
        request.getSession().setAttribute("numeroPedido", envioNuevo);




        return new ModelAndView("redirect:/pago");
    }

    @RequestMapping("/pago")
    public ModelAndView pago(){
        ModelMap model = new ModelMap();
        model.put("numeroPedido", request.getSession().getAttribute("numeroPedido"));
        return new ModelAndView("/pago", model);
    }


    private ModelAndView registroDeEnvioFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        modelo.put("productos", request.getSession().getAttribute("arrayProductos"));
        return new ModelAndView("compra", modelo);
    }


}
