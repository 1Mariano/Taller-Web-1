package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

public interface ServicioListado {
    List<Producto> obtenerProductos();

    Usuario obtenerUsuarioPorId(Long id);
    /*
    List<Producto> listarProductosMascotas();

    List<Higiene> listarProductosHigiene();
    */
    List<Producto> listarProductosDrogueria();

    List<Producto> listarProductosHigiene();
}
