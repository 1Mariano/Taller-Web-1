package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.enums.Categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Higiene extends Producto{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    public Higiene(Long id, Integer peso, Integer volumen, String nombre, String marca, Integer alto, Integer ancho, Integer largo, Integer precioArs,String descripcion,String img) {
        super(id, peso, volumen, nombre, marca, alto, ancho, largo, precioArs,descripcion,img);
    }

    public Higiene() {
    }

    @Override
    public Categoria getCategoria() {
        return Categoria.HIGIENE;
    }

    @Override
    public Boolean esCompatible(Producto p) {
        return !Categoria.ALIMENTACION.equals(p.getCategoria());
    }
}
