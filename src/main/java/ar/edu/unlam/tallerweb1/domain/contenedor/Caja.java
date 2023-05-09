package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.IProducto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;

public class Caja extends Contenedor {


    private Integer ancho;
    private Integer largo;

    public Caja(Long id, Integer alto, Integer ancho, Integer largo) {
        super(id, alto, 0);
        this.ancho = ancho;
        this.largo = largo;
    }

    @Override
    public Integer getSuperficie() {
        return (ancho*largo);
    }

    @Override
    public TipoContenedor getTipo() {
        return TipoContenedor.CAJA;
    }


    @Override
    public Boolean resiste(IProducto producto) {
        return true;
    }


}
