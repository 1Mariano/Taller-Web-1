package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.contenedor.IContenedor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Producto implements IProducto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer peso;
    private Integer volumen;
    private IContenedor contenedor;

    public Producto(Long id, Integer peso, Integer volumen) {
        this.id = id;
        this.peso = peso;
        this.volumen = volumen;
    }

    public Producto() {

    }


    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Integer getPeso() {
        return this.peso;
    }

    @Override
    public Integer getVolumen() {
        return this.volumen;
    }

    @Override
    public Boolean tengoEspacio(IContenedor contenedor) {
        return contenedor.volumenDisponibleContenedor() > volumen;
    }
    @Override
    public void meter(IContenedor contenedor) {
        this.contenedor = contenedor;
    }

    //toDo
    //@Override
    //	public String toString() {
    //		return "Producto [categoria=" + getCategoria() + ", referencia=" + referencia + ", peso=" + peso + ", volumen="
    //				+ volumen + ", contenedor=" + contenedor.getReferencia() + "]";
    //	}
}
