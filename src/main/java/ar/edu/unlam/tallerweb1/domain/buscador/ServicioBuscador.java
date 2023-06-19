package ar.edu.unlam.tallerweb1.domain.buscador;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.exceptions.NoSeEncontraronCoincidenciasException;

import java.util.List;

public interface ServicioBuscador {

    List<Producto> obtenerProductosPorNombreMarcaOCategoria(String busqueda) throws NoSeEncontraronCoincidenciasException;
}
