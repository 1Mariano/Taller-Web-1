package ar.edu.unlam.tallerweb1.domain.carrito;

import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Usuario usuario;


    public Carrito(Long id, Producto producto, Usuario usuario) {
        this.id = id;
        this.producto = producto;
        this.usuario = usuario;
    }

    public Carrito() {

    }


    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }






/*    public Carrito() {
        this.productos = new ArrayList<>();
    }*/



    /*public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Double calcularTotal() {
        Double total = 0.0;
        for (Producto producto : productos) {
            total += producto.getPrecioArs();
        }
        return total;
    }
*/

}
