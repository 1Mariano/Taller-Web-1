package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;

import java.util.List;

public interface RepositorioEmpaquetado {
    void crearContenedor(Contenedor contenedor);


    List<Contenedor> obtenerContenedores();



    void guardarEmpaque(Contenedor_Producto prod);

    void modificarContenedor(Contenedor contenedor);

    void eliminarContenedorVacio(Contenedor contenedorAEliminar);
}
