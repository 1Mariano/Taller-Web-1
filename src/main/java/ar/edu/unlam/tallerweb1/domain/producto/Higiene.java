package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.enums.Categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Higiene{


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreHigiene;
    private String descripcionHigiene;

    public Higiene(Long id,String nombreHigiene, String descripcionHigiene) {
        this.id = id;
        this.nombreHigiene = nombreHigiene;
        this.descripcionHigiene = descripcionHigiene;
    }

    public Long getId() {
        return id;
    }

    public String getNombreHigiene() {
        return nombreHigiene;
    }

    public void setNombreHigiene(String nombreHigiene) {
        this.nombreHigiene = nombreHigiene;
    }

    public String getDescripcionHigiene() {
        return descripcionHigiene;
    }

    public void setDescripcionHigiene(String descripcionHigiene) {
        this.descripcionHigiene = descripcionHigiene;
    }

    public Higiene() {
    }
    /*
    @Override
    public Categoria getCategoria() {
        return Categoria.HIGIENE;
    }

    @Override
    public Boolean esCompatible(Producto p) {
        return !Categoria.ALIMENTACION.equals(p.getCategoria());
    }
    */
}
