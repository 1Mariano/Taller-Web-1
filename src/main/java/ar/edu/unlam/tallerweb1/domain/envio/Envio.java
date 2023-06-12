package ar.edu.unlam.tallerweb1.domain.envio;

import ar.edu.unlam.tallerweb1.domain.enums.EstadoEnvio;
import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;

import javax.persistence.*;

@Entity
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double costoEnvio;
    private Double distanciaEnKilometros;
    @OneToOne
    private Pedido pedido;
    @OneToOne
    private Vehiculo vehiculo;
    private Integer peso;
    private Integer volumen;
    private EstadoEnvio estadoEnvio;
    private String calle;
    private Integer numero;
    private String pisoODepartamento;
    private String codigoPostal;
    private String localidad;

    public Envio(Long id, Double costoEnvio, Double distanciaEnKilometros, Integer peso, Integer volumen, EstadoEnvio estadoEnvio, String calle, Integer numero, String pisoODepartamento, String codigoPostal, String localidad) {
        this.id = id;
        this.costoEnvio = costoEnvio;
        this.distanciaEnKilometros = distanciaEnKilometros;
        this.peso = peso;
        this.volumen = volumen;
        this.estadoEnvio = estadoEnvio;
        this.calle = calle;
        this.numero = numero;
        this.pisoODepartamento = pisoODepartamento;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
    }

    public Envio() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(Double costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public Double getDistanciaEnKilometros() {
        return distanciaEnKilometros;
    }

    public void setDistanciaEnKilometros(Double distanciaEnKilometros) {
        this.distanciaEnKilometros = distanciaEnKilometros;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getVolumen() {
        return volumen;
    }

    public void setVolumen(Integer volumen) {
        this.volumen = volumen;
    }

    public EstadoEnvio getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPisoODepartamento() {
        return pisoODepartamento;
    }

    public void setPisoODepartamento(String pisoODepartamento) {
        this.pisoODepartamento = pisoODepartamento;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
