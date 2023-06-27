package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.carrito.Carrito;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

public interface RepositorioProducto {
    //todo ver que propiedades hacen a mi producto unico para buscarlo
    Producto buscarProducto(Long id, Integer peso, Integer volumen, String nombre, String marca, Integer precioArs);

    void guardar(Producto producto);

    List<Producto> buscar(String nombre);

    void modificar(Producto producto);

    List<Producto> listarTodosLosProductos();

    Usuario buscarUsuarioPorId(Long id);

    Producto buscarProductoPorId(Long productoId);

    void agregarProductoAlCarrito(Long productoId, Long usuarioId);

    List<Carrito> obtenerTodosLosProductosDelCarritoDelUsuario(Long id);

    void eliminarProductoDelCarrito(Carrito carrito);

    List<Producto> listarProductosPorNombreMarcaOCategoria(String busqueda);

    void vaciarCarrito(Long idUsuario);

    //List<Producto> listarDrogueria();
    /*
    List<Mascota> listarMascotas();

    List<Higiene> listarHigiene();
     */
}
