package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.enums.Categoria;

import javax.persistence.*;

@Entity
public abstract class Alimentacion extends Producto{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;


    public Alimentacion(Long id, Integer peso, Integer volumen, String nombre, String marca, Integer alto, Integer ancho, Integer largo) {
        super(id, peso, volumen, nombre, marca, alto, ancho, largo);
    }

    public Alimentacion() {

    }

    @Override
    public Categoria getCategoria() {
        return Categoria.ALIMENTACION;
    }

    @Override
    public Boolean esCompatible(Producto p) {
        return Categoria.ALIMENTACION.equals(p.getCategoria());
    }



    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
