package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;

import javax.persistence.*;

@Entity
public class Contenedor_Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Contenedor contenedor;

    /*@ManyToOne
    private Pedido pedido;*/

    @ManyToOne
    private Envio envio;

    public Contenedor_Producto(Long id, Producto producto, Contenedor contenedor, Envio envio) {
        this.id = id;
        this.producto = producto;
        this.contenedor = contenedor;
        this.envio = envio;
    }

    public Contenedor_Producto() {

    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public Producto getProducto() {
        return producto;
    }

    /*public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }*/

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
