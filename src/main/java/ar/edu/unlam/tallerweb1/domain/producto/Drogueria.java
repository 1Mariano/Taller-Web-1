package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.enums.Categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Drogueria extends Producto{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    public Drogueria(Long id, Integer peso, Integer volumen, String nombre, String marca, Integer alto, Integer ancho, Integer largo) {
        super(id, peso, volumen, nombre, marca, alto, ancho, largo);
    }

    public Drogueria() {

    }

    @Override
    public Categoria getCategoria() {
        return Categoria.DROGUERIA;
    }

    @Override
    public Boolean esCompatible(Producto p) {
        return !Categoria.ALIMENTACION.equals(p.getCategoria()) && !Categoria.MASCOTAS.equals(p.getCategoria()) ;
    }
}
