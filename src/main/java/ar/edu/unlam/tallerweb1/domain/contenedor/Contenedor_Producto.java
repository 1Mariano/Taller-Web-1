package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contenedor_Producto {

    @Id
    private Long id;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Contenedor contenedor;

    @ManyToOne
    private Pedido pedido;

    public Contenedor_Producto(Long id, Producto producto, Contenedor contenedor) {
        this.id = id;
        this.producto = producto;
        this.contenedor = contenedor;
    }

    public Contenedor_Producto() {

    }

    public Producto getProducto() {
        return producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
