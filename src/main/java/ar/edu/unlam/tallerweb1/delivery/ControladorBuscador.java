package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.buscador.ServicioBuscador;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.producto.ServicioListado;
import ar.edu.unlam.tallerweb1.exceptions.NoSeEncontraronCoincidenciasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorBuscador {

    private final ServicioBuscador servicioBuscador;
    private final ServicioListado servicioListado;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    public ControladorBuscador(ServicioBuscador servicioBuscador, ServicioListado servicioListado) {
        this.servicioBuscador = servicioBuscador;
        this.servicioListado = servicioListado;
    }

    @RequestMapping("/header")
    public ModelAndView irALaBuscador() {

        ModelMap model = new ModelMap();
        model.put("datosBuscador", new DatosBuscador());

        return new ModelAndView("/header", model);
    }

    @RequestMapping(path = "/buscar-productos", method = RequestMethod.GET)
    public ModelAndView buscarProductos(@ModelAttribute("datosBuscador") DatosBuscador datosBuscador) throws NoSeEncontraronCoincidenciasException {
        ModelMap model = new ModelMap();

        List<Producto> listaProductos = this.servicioListado.obtenerProductos();

        try {
            List<Producto> productosFiltrados = this.servicioBuscador.obtenerProductosPorNombreMarcaOCategoria(datosBuscador.getBusqueda());
            model.put("productosFiltrados", productosFiltrados);
        } catch (NoSeEncontraronCoincidenciasException e) {
            model.put("error", "No se encontraron coincidencias para la búsqueda especificada");
        }

        return new ModelAndView("/buscador-resultados", model);
    }
}
