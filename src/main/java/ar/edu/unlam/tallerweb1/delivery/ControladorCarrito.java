package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.carrito.Carrito;
import ar.edu.unlam.tallerweb1.domain.carrito.ServicioCarrito;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.producto.ServicioListado;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorCarrito {

    private final ServicioCarrito servicioCarrito;
    private Carrito carrito = new Carrito();

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession sesion;
    @Autowired
    public ControladorCarrito(ServicioCarrito servicioCarrito){
        this.servicioCarrito = servicioCarrito;
    }

    @RequestMapping("/agregarProducto")
    public ModelAndView agregarProducto(@RequestParam("id") Long productoId){

        if (request.getSession().getAttribute("idUsuario") == null){
            return new ModelAndView("redirect:/login");
        }
        Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");
        Producto producto = this.servicioCarrito.obtenerProductoPorId(productoId);
        if(producto != null){
            this.servicioCarrito.agregarProductoAlCarrito(producto, idUsuario);
        }


        return new ModelAndView("redirect:/carrito");
    }



    @RequestMapping("/eliminarProducto")
    public ModelAndView eliminarProducto(@RequestParam("id") Long productoId){
        Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");
        Producto producto = this.servicioCarrito.obtenerProductoPorId(productoId);
        if(producto != null){
            this.servicioCarrito.BorrarProductoDelCarrito(producto, idUsuario);
        }


        return new ModelAndView("redirect:/carrito");
    }

    @RequestMapping("/carrito")
    public ModelAndView mostrarCarrito(){
        if (request.getSession().getAttribute("idUsuario") == null){
            return new ModelAndView("redirect:/login");
        }
        ModelMap model = new ModelMap();
        Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");
        List<Producto> carrito= this.servicioCarrito.obtenerTodosLosProductosDelCarrito(idUsuario);
        request.getSession().setAttribute("carritoCompleto", carrito);
        model.put("carrito", carrito);
        model.put("total", calcularTotal(carrito));
        model.put("tamano", carrito.size());
        return new ModelAndView("/carrito", model);
    }

    private Double calcularTotal(List<Producto> carrito) {
        Double total = 0.0;
        for (Producto producto : carrito) {
            total += producto.getPrecioArs();
        }
        return total;
    }

    /*@RequestMapping("/agregarProducto")
    public String agregarProducto(@RequestParam("productoId") int productoId) {
        // Lógica para agregar el producto al carrito
        Producto producto = obtenerProductoPorId(productoId);
        carrito.agregarProducto(producto);
        return "redirect:/carrito";
    }*/
}
