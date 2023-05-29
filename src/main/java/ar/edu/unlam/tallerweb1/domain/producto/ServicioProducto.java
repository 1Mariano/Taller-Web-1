package ar.edu.unlam.tallerweb1.domain.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ServicioProducto {
    private static RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioProducto(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    public static List<Producto> obtenerProductos() {

    }



    public Producto buscarProducto(Long id, Integer peso, String nombre, String marca) {
        return repositorioProducto.buscarProducto(peso,nombre,marca);
    }

    public static Producto obtenerProductoPorId(Long id) {
        return repositorioProducto.obtenerProductoPorId(id);
    }
    public List<Producto>buscarProductoPorNombre(String nombre){
        return repositorioProducto.buscarProductoPorNombre(nombre);
    }

    public void guardarProducto(Producto producto) {
        repositorioProducto.guardarProducto(producto);
    }

    public void modificarProducto(Producto producto) {
        repositorioProducto.modificarProducto(producto);
    }

    public void eliminarProducto(Producto producto){
        repositorioProducto.eliminarProducto(producto);
    }
}
