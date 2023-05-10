package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.IProducto;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Caja extends Contenedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ancho;
    private Integer largo;

    public Caja(Long id, Integer alto, Integer ancho, Integer largo) {
        super(id, alto, 0);
        this.ancho = ancho;
        this.largo = largo;
    }

    public Caja() {

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
    public Boolean resiste(Producto producto) {
        return true;
    }


}
