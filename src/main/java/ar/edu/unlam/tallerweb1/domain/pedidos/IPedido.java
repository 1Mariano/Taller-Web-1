package ar.edu.unlam.tallerweb1.domain.pedidos;

import ar.edu.unlam.tallerweb1.domain.contenedor.IContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.IProducto;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;

import java.util.List;

public interface IPedido {
    List<IProducto> getProductos();
    List<IContenedor> getContenedores();
    void addContenedor(IContenedor contenedor);
    IContenedor addProducto(IProducto producto);
}
