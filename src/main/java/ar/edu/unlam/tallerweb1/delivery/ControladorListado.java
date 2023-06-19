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
public class ControladorListado {

    private final ServicioListado servicioListado;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    public ControladorListado(ServicioListado servicioListado) {

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
    public ModelAndView irAHigiene() {
        ModelMap modelo = new ModelMap();

        modelo.put("datosBuscador", new DatosBuscador());

        List<Producto> productosHigiene = this.servicioListado.listarProductosHigiene();
        modelo.put("lista", productosHigiene);
        guardarCorreo(modelo);
        return new ModelAndView("/higiene", modelo);
    }

    @RequestMapping("/drogueria")
    public ModelAndView irADrogueria() {
        ModelMap modelo = new ModelMap();

        modelo.put("datosBuscador", new DatosBuscador());

        List<Producto> productosDrogueria = this.servicioListado.listarProductosDrogueria();
        modelo.put("lista", productosDrogueria);
        guardarCorreo(modelo);
        return new ModelAndView("/drogueria", modelo);
    }

    @RequestMapping("/mascota")
    public ModelAndView irAMascota() {
        ModelMap modelo = new ModelMap();

        modelo.put("datosBuscador", new DatosBuscador());

        List<Producto> productosMascota = this.servicioListado.listarProductosMascota();
        modelo.put("lista", productosMascota);
        guardarCorreo(modelo);
        return new ModelAndView("/mascota", modelo);
    }

    @RequestMapping("/alimentos")
    public ModelAndView irAAlimentos() {
        ModelMap modelo = new ModelMap();

        modelo.put("datosBuscador", new DatosBuscador());

        List<Producto> productosAlimento = this.servicioListado.listarProductosAlimento();
        modelo.put("lista", productosAlimento);
        guardarCorreo(modelo);
        return new ModelAndView("/alimentos", modelo);
    }


    @RequestMapping("/home")
    public ModelAndView obtenerProductos(/*@RequestParam(value = "id") Long id*/) {
        ModelMap modelo = new ModelMap();

        modelo.put("datosBuscador", new DatosBuscador());

        List<Producto> productos = this.servicioListado.obtenerProductos();
        modelo.put("lista", productos);
        // TOdo Mostrar Nombre de usuario y no ID
        guardarCorreo(modelo);

        /*Usuario usuario = this.servicioListado.obtenerUsuarioPorId(id);
        modelo.put("usuario", usuario);*/
        return new ModelAndView("home", modelo);
    }

    private void guardarCorreo(ModelMap modelo) {
        if (request.getSession().getAttribute("idUsuario") != null) {
            String correo = (String) request.getSession().getAttribute("correo");
            //Long id = (Long) request.getSession().getAttribute("idUsuario");
            modelo.put("correo", correo);
        }
    }
}