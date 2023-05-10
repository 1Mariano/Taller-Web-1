package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

public interface RepositorioProducto {

    Producto buscarProducto(Long id, Integer peso, Integer volumen, String nombre, String marca);
    void guardar(Producto producto);
    Producto buscar(String nombre);
    void modificar(Producto producto);
}
