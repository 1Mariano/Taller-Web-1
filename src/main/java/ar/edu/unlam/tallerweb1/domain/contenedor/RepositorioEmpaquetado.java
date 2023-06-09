package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;

import java.util.List;

public interface RepositorioEmpaquetado {
    void crearContenedor(Contenedor caja);


    List<Contenedor> obtenerContenedores();
}
