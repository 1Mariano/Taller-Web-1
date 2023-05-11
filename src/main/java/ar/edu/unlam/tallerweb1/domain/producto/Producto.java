package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.IContenedor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Producto implements IProducto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer peso;
    private Integer volumen;
    private String nombre;
    private String marca;

    private Integer alto;
    private Integer ancho;
    private Integer largo;


    @ManyToOne
    private Contenedor contenedor;

    public Producto(Long id, Integer peso, Integer volumen, String nombre, String marca, Integer alto, Integer ancho, Integer largo) {
        this.id = id;
        this.peso = peso;
        this.volumen = volumen;
        this.marca = marca;
        this.nombre = nombre;
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
    }

    public Integer getAlto() {
        return alto;
    }

    public void setAlto(Integer alto) {
        this.alto = alto;
    }

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public Integer getLargo() {
        return largo;
    }

    public void setLargo(Integer largo) {
        this.largo = largo;
    }

    public Producto() {

    }
    @Override
    public  String getNombre(){
        return this.nombre;
    }

    @Override
    public  String getMarca(){
        return this.marca;
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
    public Boolean tengoEspacio(Contenedor contenedor) {
        return contenedor.volumenDisponibleContenedor() > volumen;
    }
    @Override
    public void meter(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public void setVolumen(Integer volumen) {
        this.volumen = volumen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
//toDo
    //@Override
    //	public String toString() {
    //		return "Producto [categoria=" + getCategoria() + ", referencia=" + referencia + ", peso=" + peso + ", volumen="
    //				+ volumen + ", contenedor=" + contenedor.getReferencia() + "]";
    //	}
}