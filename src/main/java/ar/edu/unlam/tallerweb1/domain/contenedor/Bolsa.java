package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.IProducto;

public class Bolsa extends Contenedor{

    private Integer ancho;

    public Bolsa(Long id, Integer alto, Integer ancho) {
        super(id, alto);
        this.ancho = ancho;
    }

    @Override
    public Integer getSuperficie() {
        Integer radio = getDiametro()/2;
        return (int) (Math.PI * radio * radio);
    }

    private Integer getDiametro() {
        return (int) ((2*ancho) / Math.PI);
    }

    @Override
    public TipoContenedor getTipo() {
        return TipoContenedor.BOLSA;
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
