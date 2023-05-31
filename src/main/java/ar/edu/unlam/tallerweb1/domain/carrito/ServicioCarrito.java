package ar.edu.unlam.tallerweb1.domain.carrito;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;

public interface ServicioCarrito{
    Producto obtenerProductoPorId(Long productoId);
}
