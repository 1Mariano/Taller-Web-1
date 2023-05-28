package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.enums.Categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Drogueria{


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreDrogueria;
    private String descripcionDrogueria;
    public Drogueria(Long id, String nombreDrogueria, String descripcionDrogueria) {
        this.id = id;
        this.nombreDrogueria = nombreDrogueria;
        this.descripcionDrogueria = descripcionDrogueria;
    }

    public Drogueria() {

    }

    public Long getId() {
        return id;
    }

    public String getNombreDrogueria() {
        return nombreDrogueria;
    }

    public void setNombreDrogueria(String nombreDrogueria) {
        this.nombreDrogueria = nombreDrogueria;
    }

    public String getDescripcionDrogueria() {
        return descripcionDrogueria;
    }

    public void setDescripcionDrogueria(String descripcionDrogueria) {
        this.descripcionDrogueria = descripcionDrogueria;
    }
    /*
    @Override
    public Categoria getCategoria() {
        return Categoria.DROGUERIA;
    }

    @Override
    public Boolean esCompatible(Producto p) {
        return !Categoria.ALIMENTACION.equals(p.getCategoria()) && !Categoria.MASCOTAS.equals(p.getCategoria()) ;
    }
    */

}
