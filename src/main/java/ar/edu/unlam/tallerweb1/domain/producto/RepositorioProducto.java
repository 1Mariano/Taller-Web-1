package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

public interface RepositorioProducto {
//todo ver que propiedades hacen a mi producto unico para buscarlo
    Producto buscarProducto(Long id, Integer peso, Integer volumen, String nombre, String marca, Integer precioArs);
    void guardar(Producto producto);
    List<Producto> buscar(String nombre);
    void modificar(Producto producto);

    List<Mascota> listarMascotas();

    List<Higiene> listarHigiene();

    List<Drogueria> listarDrogueria();
}
