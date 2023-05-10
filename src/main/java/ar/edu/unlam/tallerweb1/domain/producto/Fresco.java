package ar.edu.unlam.tallerweb1.domain.producto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class Fresco extends Alimentacion implements Caducable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate caducidad;

    public Fresco(Long id, Integer peso, Integer volumen, LocalDate caducidad, String nombre, String marca, Integer alto, Integer ancho, Integer largo) {
        super(id, peso, volumen, nombre, marca, alto, ancho, largo);
        this.caducidad = caducidad;
    }

    @Override
    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(LocalDate caducidad) {
        this.caducidad = caducidad;
    }

    public Fresco() {

    }


    @Override
    public boolean estaCaducado() {
        return caducidad.isBefore(LocalDate.now());
    }
}
