package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.IProducto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;

import java.util.List;

public interface IContenedor {
    Integer getVolumenContenedor();
    Integer getSuperficie();
    Integer volumenDisponibleContenedor();
    Integer getResistencia();

    List<IProducto> getProductos();
    TipoContenedor getTipo();
    Boolean meter(IProducto producto);
    Boolean resiste(IProducto producto);


    Boolean tengoEspacio(Vehiculo vehiculo);

    void guardar(Vehiculo vehiculo);

    Integer pesoTotalContenedor();
}
