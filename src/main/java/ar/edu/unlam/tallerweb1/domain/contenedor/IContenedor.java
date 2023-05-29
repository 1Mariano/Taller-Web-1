package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;

import java.util.List;

public interface IContenedor {
    Integer getVolumenContenedor();
    Integer getSuperficie();
    Integer volumenDisponibleContenedor();
    Integer getResistencia();

    List<Producto> getProductos();
    TipoContenedor getTipo();
    Boolean meter(Producto producto);
    Boolean resiste(Producto producto);


    Boolean tengoEspacio(Vehiculo vehiculo);

    void guardar(Vehiculo vehiculo);

    Integer pesoTotalContenedor();
}
