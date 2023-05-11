package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.IContenedor;
import ar.edu.unlam.tallerweb1.domain.enums.Categoria;

public interface IProducto {
    Long getId();
    Integer getPeso();
    Integer getVolumen();
    Categoria getCategoria();
    Boolean esCompatible(Producto p);
    Boolean tengoEspacio(Contenedor contenedor);
    void meter(Contenedor contenedor);

    String getNombre();
    String getMarca();
    Integer getPrecioArs();

    String getDescripcion();
    String getImg();
}
