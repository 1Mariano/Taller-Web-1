package ar.edu.unlam.tallerweb1.domain.pedidos;

import ar.edu.unlam.tallerweb1.domain.enums.EstadoPago;
import ar.edu.unlam.tallerweb1.domain.enums.EstadoPedido;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaPedido;
    private String calle;
    private Integer numero;
    private String pisoODepartamento;
    private String codigoPostal;
    private String localidad;
    private Double costoTotal;
    private EstadoPedido estado;
    private EstadoPago estadoPago;

    public Pedido(Long id, LocalDate fechaPedido, String calle, Integer numero, String pisoODepartamento, String codigoPostal, String localidad, Double costoTotal, EstadoPedido estado, EstadoPago estadoPago) {
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.calle = calle;
        this.numero = numero;
        this.pisoODepartamento = pisoODepartamento;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.costoTotal = costoTotal;
        this.estado = estado;
        this.estadoPago = estadoPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
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

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }
}
