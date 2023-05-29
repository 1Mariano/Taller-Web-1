package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.producto.ServicioProducto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
    public class ControladorProducto {
        // Dependencia al servicio ProductoService

        @GetMapping("/productos/{id}")
        public String verProducto(@PathVariable Long id, Model model) {
            // Lógica para obtener el producto con el id especificado
            Producto producto = ServicioProducto.obtenerProductoPorId(id);

            // Verificar si el producto está caducado
            boolean estaVencido = producto.estaVencido();

            model.addAttribute("producto", producto);
            model.addAttribute("estaVencido", estaVencido);

            //TODO Devolver la vista correspondiente para mostrar los detalles del producto
            return "detalleProducto";
        }
    @GetMapping("/productos")
    public String obtenerProductos(Model model) {
        // Lógica para obtener la lista de productos
        List<Producto> productos = ServicioProducto.obtenerProductos();

        // Agregar la lista de productos al modelo
        model.addAttribute("productos", productos);

        // Devolver la vista correspondiente para mostrar los productos
        return "listaProductos";
    }
    }

}
