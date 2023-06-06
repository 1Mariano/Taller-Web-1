package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.producto.ServicioListado;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorListado{

    private final ServicioListado servicioListado;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    public ControladorListado(ServicioListado servicioListado){

        this.servicioListado = servicioListado;
    }
    /*
    @RequestMapping("/mascotas")
    public ModelAndView irAMascotas(){
        ModelMap modelo = new ModelMap();
        List<Mascota>productosMascota = this.servicioListado.listarProductosMascotas();
        modelo.put("lista", productosMascota);
        return new ModelAndView("/mascotas", modelo);
    }
    */

    @RequestMapping("/higiene")
    public ModelAndView irAHigiene(){
        ModelMap modelo = new ModelMap();
        List<Producto> productosHigiene = this.servicioListado.listarProductosHigiene();
        modelo.put("lista", productosHigiene);
        return new ModelAndView("/higiene", modelo);

    }

    @RequestMapping("/drogueria")
    public ModelAndView irADrogueria(){
        ModelMap modelo = new ModelMap();
        List<Producto> productosDrogueria = this.servicioListado.listarProductosDrogueria();
        modelo.put("lista", productosDrogueria);
        return new ModelAndView("/drogueria", modelo);

    }

    @RequestMapping("/mascota")
    public ModelAndView irAMascota(){
        ModelMap modelo = new ModelMap();
        List<Producto> productosMascota = this.servicioListado.listarProductosMascota();
        modelo.put("lista", productosMascota);
        return new ModelAndView("/mascota", modelo);

    }
    @RequestMapping("/alimentos")
    public ModelAndView irAAlimentos(){
        ModelMap modelo = new ModelMap();
        List<Producto> productosAlimento = this.servicioListado.listarProductosAlimento();
        modelo.put("lista", productosAlimento);
        return new ModelAndView("/alimentos", modelo);

    }


    @RequestMapping("/home")
    public ModelAndView obtenerProductos(/*@RequestParam(value = "id") Long id*/){
        ModelMap modelo = new ModelMap();
        List<Producto> productos = this.servicioListado.obtenerProductos();
        modelo.put("lista", productos);
        // TOdo Mostrar Nombre de usuario y no ID
        Long id = (Long) request.getSession().getAttribute("idUsuario");
        modelo.put("usuario", id);
        /*Usuario usuario = this.servicioListado.obtenerUsuarioPorId(id);
        modelo.put("usuario", usuario);*/
        return new ModelAndView("home", modelo);
    }






    /*@GetMapping("/productos")
    public String obtenerProductos(Model model) {
        // Lógica para obtener la lista de productos
        List<Producto> productos = ServicioProducto.obtenerProductos();

        // Agregar la lista de productos al modelo
        model.addAttribute("productos", productos);

        // Devolver la vista correspondiente para mostrar los productos
        return "listaProductos";
    }*/

}
