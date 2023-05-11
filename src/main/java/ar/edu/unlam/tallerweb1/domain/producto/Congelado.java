package ar.edu.unlam.tallerweb1.domain.producto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class Congelado extends Alimentacion implements Caducable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    private LocalDate caducidad;

    public Congelado(Long id, Integer peso, Integer volumen, LocalDate caducidad, String nombre, String marca, Integer alto, Integer ancho, Integer largo, Integer precioArs,String descripcion,String img) {
        super(id, peso, volumen, nombre, marca, alto, ancho, largo, precioArs,descripcion, img);
        this.caducidad = caducidad;
    }

    public Congelado() {
        super();
    }

    @Override
    public boolean estaCaducado() {
        return caducidad.isBefore(LocalDate.now());
    }

    public LocalDate getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(LocalDate caducidad) {
        this.caducidad = caducidad;
    }
}
