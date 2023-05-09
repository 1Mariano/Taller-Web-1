package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.contenedor.IContenedor;
import ar.edu.unlam.tallerweb1.domain.enums.Categoria;

public interface IProducto {
    Long getId();
    Integer getPeso();
    Integer getVolumen();
    Categoria getCategoria();
    Boolean esCompatible(IProducto p);
    Boolean tengoEspacio(IContenedor contenedor);
    void meter(IContenedor contenedor);
}
