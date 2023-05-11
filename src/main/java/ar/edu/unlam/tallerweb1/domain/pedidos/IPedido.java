package ar.edu.unlam.tallerweb1.domain.pedidos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.IContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.IProducto;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;

import java.util.List;

public interface IPedido {
    List<Producto> getProductos();
    List<Contenedor> getContenedores();
    //ToDo ver devolucion
    void addContenedor(Contenedor contenedor);
    Contenedor addProducto(Producto producto);
}
