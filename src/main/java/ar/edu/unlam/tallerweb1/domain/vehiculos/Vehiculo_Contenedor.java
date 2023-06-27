package ar.edu.unlam.tallerweb1.domain.vehiculos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vehiculo_Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vehiculo vehiculo;

    @ManyToOne
    private Contenedor contenedor;

    @ManyToOne
    private Envio envio;

    public Vehiculo_Contenedor(Long id, Vehiculo vehiculo, Contenedor contenedor, Envio envio) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.contenedor = contenedor;
        this.envio = envio;
    }

    public Vehiculo_Contenedor() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
}