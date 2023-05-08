package ar.edu.unlam.tallerweb1.domain.envio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;
@Entity
public class Bici implements ServicioEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private Time franjaHoraria;
    private String direccion;



    @Override
    public Double dimensionesDisponibles() {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(Time franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
