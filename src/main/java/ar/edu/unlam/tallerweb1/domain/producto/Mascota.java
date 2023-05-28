package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.enums.Categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mascota {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreMascota;


    private String descripcionMascota;

    /*public Mascota(Long id, Integer peso, Integer volumen, String nombre, String marca, Integer alto, Integer ancho, Integer largo, Integer precio, String descripcion, String img, String nombreMascota, String descripcionMascota) {
        super(id, peso, volumen, nombre, marca, alto, ancho, largo, precio,descripcion,img);

        this.nombreMascota = nombreMascota;
        this.descripcionMascota = descripcionMascota;
    }*/
    public Mascota(Long id, String nombreMascota, String descripcionMascota) {
        this.id = id;
        this.nombreMascota = nombreMascota;
        this.descripcionMascota = descripcionMascota;
    }
    public Long getId() {
        return id;
    }
    public Mascota() {

    }



    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getDescripcionMascota() {
        return descripcionMascota;
    }

    public void setDescripcionMascota(String descripcionMascota) {
        this.descripcionMascota = descripcionMascota;
    }



    /*@Override
    public Categoria getCategoria() {
        return Categoria.MASCOTAS;
    }

    @Override
    public Boolean esCompatible(Producto p) {
        return !Categoria.DROGUERIA.equals(p.getCategoria());
    }
    */

}
