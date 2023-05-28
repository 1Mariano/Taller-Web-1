package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.producto.Drogueria;
import ar.edu.unlam.tallerweb1.domain.producto.Higiene;
import ar.edu.unlam.tallerweb1.domain.producto.Mascota;
import ar.edu.unlam.tallerweb1.domain.producto.ServicioListado;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorListado{

    private final ServicioListado servicioListado;

    @Autowired
    public ControladorListado(ServicioListado servicioListado){

        this.servicioListado = servicioListado;
    }
    @RequestMapping("/mascotas")
    public ModelAndView irAMascotas(){
        ModelMap modelo = new ModelMap();
        List<Mascota>productosMascota = this.servicioListado.listarProductosMascotas();
        modelo.put("lista", productosMascota);
        return new ModelAndView("/mascotas", modelo);
    }

    @RequestMapping("/higiene")
    public ModelAndView irAHigiene(){
        ModelMap modelo = new ModelMap();
        List<Higiene> productosHigiene = this.servicioListado.listarProductosHigiene();
        modelo.put("lista", productosHigiene);
        return new ModelAndView("/higiene", modelo);

    }

    @RequestMapping("/drogueria")
    public ModelAndView irADrogueria(){
        ModelMap modelo = new ModelMap();
        List<Drogueria> productosDrogueria = this.servicioListado.listarProductosDrogueria();
        modelo.put("lista", productosDrogueria);
        return new ModelAndView("drogueria", modelo);

    }


}
