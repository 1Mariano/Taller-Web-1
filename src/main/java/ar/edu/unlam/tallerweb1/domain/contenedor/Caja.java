package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.IProducto;

public class Caja extends Contenedor {


    private Integer ancho;
    private Integer largo;

    public Caja(Long id, Integer alto, Integer ancho, Integer largo) {
        super(id, alto);
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
    public Boolean meter(IProducto producto) {
        return null;
    }

    @Override
    public Boolean resiste(IProducto producto) {
        return null;
    }
}
