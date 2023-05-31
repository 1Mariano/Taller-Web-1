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

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorCarrito {

    private final ServicioCarrito servicioCarrito;
    private Carrito carrito = new Carrito();

    @Autowired
    public ControladorCarrito(ServicioCarrito servicioCarrito){
        this.servicioCarrito = servicioCarrito;
    }

    @RequestMapping("/agregarProducto")
    public ModelAndView agregarProducto(@RequestParam("id") Long productoId){
        Producto producto = this.servicioCarrito.obtenerProductoPorId(productoId);
        this.carrito.agregarProducto(producto);
        //agregarProducto(producto);
        //List<Producto> carritoLleno = this.getProductos();
        //this.carrito.agregarProducto(producto);
        //ModelMap model = new ModelMap();
        //model.put("carrito", this.getProductos());
        //return new ModelAndView("redirect:/carrito",model);
        return new ModelAndView("redirect:/carrito");
    }

    @RequestMapping("/eliminarProducto")
    public ModelAndView eliminarProducto(@RequestParam("id") Long productoId){
        Producto aBorrar = null;
        for (Producto producto : this.carrito.getProductos()) {
            if (producto.getId() == productoId){
                aBorrar = producto;
            }
        }
        if (aBorrar != null){
            this.carrito.eliminarProducto(aBorrar);
            //this.eliminarProducto(aBorrar);
        }

        return new ModelAndView("redirect:/carrito");
    }

    @RequestMapping("/carrito")
    public ModelAndView mostrarCarrito(){
        ModelMap model = new ModelMap();
        model.put("carrito", this.carrito.getProductos());
        model.put("total", this.carrito.calcularTotal());
        /*model.put("carrito", this.getProductos());
        model.put("total", this.calcularTotal());*/
        return new ModelAndView("/carrito", model);
    }

    /*@RequestMapping("/agregarProducto")
    public String agregarProducto(@RequestParam("productoId") int productoId) {
        // Lógica para agregar el producto al carrito
        Producto producto = obtenerProductoPorId(productoId);
        carrito.agregarProducto(producto);
        return "redirect:/carrito";
    }*/
}
