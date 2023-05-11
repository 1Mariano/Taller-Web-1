package ar.edu.unlam.tallerweb1.domain.producto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class NoPerecedero extends Alimentacion implements Caducable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaFabricacion;
    private static final int ANYOS_CADUCIDAD = 5;

    public NoPerecedero(Long id, Integer peso, Integer volumen, LocalDate fechaFabricacion, String nombre, String marca, Integer alto, Integer ancho, Integer largo, Integer precioArs,String descripcion,String img) {
        super(id, peso, volumen, nombre, marca, alto, ancho, largo, precioArs,descripcion,img);
        this.fechaFabricacion = fechaFabricacion;
    }

    public NoPerecedero() {

    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    @Override
    public boolean estaCaducado() {
        return LocalDate.now().isBefore(fechaFabricacion.plusYears(ANYOS_CADUCIDAD));
    }
}
