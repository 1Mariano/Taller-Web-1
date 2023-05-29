package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

public interface RepositorioProducto {
//todo ver que propiedades hacen a mi producto unico para buscarlo
    Producto buscarProducto(Integer peso, String nombre, String marca);
    void guardarProducto(Producto producto);
    List<Producto> buscarProductoPorNombre(String nombre);
    void modificarProducto(Producto producto);



    void eliminarProducto(Producto producto);

    Producto obtenerProductoPorId(Long id);


}
