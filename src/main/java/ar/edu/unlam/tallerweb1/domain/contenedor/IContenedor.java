package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.IProducto;

import java.util.List;

public interface IContenedor {
    Integer getVolumen();
    Integer getSuperficie();
    Integer volumenDisponible();
    Integer getPesoSoportado();

    List<IProducto> getProductos();
    TipoContenedor getTipo();
    Boolean meter(IProducto producto);
    Boolean resiste(IProducto producto);
}
