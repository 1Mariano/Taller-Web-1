package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;

import javax.persistence.*;
import java.util.List;


@Entity
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double alto;
    private Double largo;
    private Double ancho;
    private Double pesoSoportado;
    private Double volumen;

    @ManyToOne
    private Vehiculo vehiculo;


    private TipoContenedor tipoContenedor;

    public Contenedor(Long id, Double alto, Double largo, Double ancho, Double pesoSoportado, TipoContenedor tipoContenedor) {
        this.id = id;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.pesoSoportado = pesoSoportado;
        this.tipoContenedor = tipoContenedor;
    }

    public Contenedor() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getVolumen() {
        return this.alto*this.ancho*this.largo;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Double getAlto() {
        return alto;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getPesoSoportado() {
        return pesoSoportado;
    }

    public void setPesoSoportado(Double pesoSoportado) {
        this.pesoSoportado = pesoSoportado;
    }




    public TipoContenedor getTipoContenedor() {
        return tipoContenedor;
    }

    public void setTipoContenedor(TipoContenedor tipoContenedor) {
        this.tipoContenedor = tipoContenedor;
    }

    /*public void setId(Long id) {
        this.id = id;
    }*/

    public Long getId() {
        return id;
    }
}
