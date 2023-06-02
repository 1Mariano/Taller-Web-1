package ar.edu.unlam.tallerweb1.domain.carrito;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;

import java.util.List;

public interface ServicioCarrito{
    Producto obtenerProductoPorId(Long productoId);

    void agregarProductoAlCarrito(Producto producto, Long usuarioId);

    List<Producto> obtenerTodosLosProductosDelCarrito(Long id);
}
