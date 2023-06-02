package ar.edu.unlam.tallerweb1.domain.pedidos;

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

    private LocalDate fechaDePedido;
    /*TODO cambiar o pensar tipo estado y entrega*/
    private String estado;
    private String direccionDeEntrega;
    private String franjaHoraria;
    @OneToMany
    private List<Producto> productos;
    private Double precioTotalPedido;

    public Pedido(Long id, LocalDate fechaDePedido, String estado, String direccionDeEntrega, String franjaHoraria, List<Producto> productos, Double precioTotalPedido) {
        this.id = id;
        this.fechaDePedido = fechaDePedido;
        this.estado = estado;
        this.direccionDeEntrega = direccionDeEntrega;
        this.franjaHoraria = franjaHoraria;
        this.productos = productos;
        this.precioTotalPedido = precioTotalPedido;
    }

    public Pedido() {

    }

    public LocalDate getFechaDePedido() {
        return fechaDePedido;
    }

    public void setFechaDePedido(LocalDate fechaDePedido) {
        this.fechaDePedido = fechaDePedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccionDeEntrega() {
        return direccionDeEntrega;
    }

    public void setDireccionDeEntrega(String direccionDeEntrega) {
        this.direccionDeEntrega = direccionDeEntrega;
    }

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(String franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Double getPrecioTotalPedido() {
        return precioTotalPedido;
    }

    public void setPrecioTotalPedido(Double precioTotalPedido) {
        this.precioTotalPedido = precioTotalPedido;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
