package ar.edu.unlam.tallerweb1.domain.pedidos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//@Entity
public class Pedido implements IPedido{

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    //private Usuario usuario;

    //@Lob
    //@Column(name = "photo", columnDefinition="BLOB")
    //private byte[] photo;
    //@OneToMany
    private List<Contenedor> contenedores;

    public Pedido() {
        this.contenedores = new ArrayList<Contenedor>();
    }


    @Override
    public List<Producto> getProductos() {
        List<Producto> productos = null;
        for (Contenedor c : contenedores) {
            if (productos == null) {
                productos = c.getProductos();
            } else {
                productos.addAll(c.getProductos());
            }
        }
        return productos;

    }

    @Override
    public List<Contenedor> getContenedores() {
        return this.contenedores;
    }

    @Override
    public void addContenedor(Contenedor contenedor) {
        contenedores.add(contenedor);
    }

    @Override
    public Contenedor addProducto(Producto producto) {
        for (Contenedor contenedor : contenedores) {
            if (contenedor.meter(producto)) {
                return contenedor;
            }
        }
        //ToDo
        //System.err.println(producto.getId() + " rechazado del pedido");
        return null;

    }








    /*public Pedido(Long id, Date fecha, Usuario usuario, Double dimension, Double peso, String direccionDeEntrega, List<Contenedor> listaContenedor) {
        this.id = id;
        this.fecha = fecha;
        this.usuario = usuario;
        this.dimension = dimension;
        this.peso = peso;
        this.direccionDeEntrega = direccionDeEntrega;
        this.listaContenedor = listaContenedor;
    }*/


    /*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getDimension() {
        return dimension;
    }

    public void setDimension(Double dimension) {
        this.dimension = dimension;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getDireccionDeEntrega() {
        return direccionDeEntrega;
    }

    public void setDireccionDeEntrega(String direccionDeEntrega) {
        this.direccionDeEntrega = direccionDeEntrega;
    }

    public List<Contenedor> getListaContenedor() {
        return listaContenedor;
    }

    public void setListaContenedor(List<Contenedor> listaContenedor) {
        this.listaContenedor = listaContenedor;
    }
     */
}
