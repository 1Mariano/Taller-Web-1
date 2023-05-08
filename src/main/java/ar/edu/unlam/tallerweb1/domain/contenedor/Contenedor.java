package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.producto.IProducto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public abstract class Contenedor  implements IContenedor{



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer alto;


    public Contenedor(Long id, Integer alto) {
        this.id = id;
        this.alto = alto;
    }

    public Contenedor() {

    }


    @Override
    public Integer getVolumen() {
        return (alto*getSuperficie());
    }

    @Override
    public Integer volumenDisponible() {
        return null;
    }

    @Override
    public Integer getPesoSoportado() {
        return null;
    }

    @Override
    public List<IProducto> getProductos() {
        return null;
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
