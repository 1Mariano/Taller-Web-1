package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.enums.Categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mascota extends Producto{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    public Mascota(Long id, Integer peso, Integer volumen, String nombre, String marca, Integer alto, Integer ancho, Integer largo) {
        super(id, peso, volumen, nombre, marca, alto, ancho, largo);
    }

    public Mascota() {

    }

    @Override
    public Categoria getCategoria() {
        return Categoria.MASCOTAS;
    }

    @Override
    public Boolean esCompatible(Producto p) {
        return !Categoria.DROGUERIA.equals(p.getCategoria());
    }


}
