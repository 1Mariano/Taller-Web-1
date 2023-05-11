package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

public interface RepositorioProducto {

    Producto buscarProducto(Long id, Integer peso, Integer volumen, String nombre, String marca);
    void guardar(Producto producto);
    List<Producto> buscar(String nombre);
    void modificar(Producto producto);
}
