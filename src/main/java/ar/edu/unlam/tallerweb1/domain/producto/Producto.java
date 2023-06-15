package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.carrito.Carrito;
import ar.edu.unlam.tallerweb1.domain.enums.CategoriaProducto;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

//TODO BORRAR LOS COMENTARIOS UNA VEZ QUE ESTE VERIFICADO EL FUNCIONAMIENTO DEL CODIGO
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@OneToMany(mappedBy = "producto")
    private List<Carrito> carritos;*/

    private Double peso;
    private Double volumen;
    private String nombre;
    private String marca;
    private Double alto;
    private Double ancho;
    private Double largo;
    private Integer precioArs;
    private String descripcion;
    private String img;
    private LocalDate fechaVencimiento;
    private CategoriaProducto categoria;
    @ManyToOne
    private Contenedor contenedor;

    public Producto(Long id, Double peso, Double volumen, String nombre, String marca, Double alto, Double ancho, Double largo, Integer precioArs, String descripcion, String img, LocalDate fechaVencimiento, CategoriaProducto categoria) {
        this.id = id;
        this.peso = peso;
        this.volumen = volumen;
        this.marca = marca;
        this.nombre = nombre;
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
        this.precioArs = precioArs;
        this.descripcion = descripcion;
        this.img = img;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public Double getAlto() {
        return alto;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double alto, Double ancho, Double largo) {
        this.volumen = this.alto * this.ancho * this.largo;
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    public Producto() {

    }
    //@Override
    public  String getNombre(){
        return this.nombre;
    }

    //@Override
    public  String getMarca(){
        return this.marca;
    }

    //@Override
    public Long getId() {
        return this.id;
    }

    //@Override
    public Double getPeso() {
        return this.peso;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    //@Override
   /* public Boolean tengoEspacio(Contenedor contenedor) {
        return contenedor.volumenDisponibleContenedor() > volumen;
    }
    // @Override
    public void meter(Contenedor contenedor) {
        this.contenedor = contenedor;
    }*/

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getPrecioArs() {
        return this.precioArs;
    }

    public void setPrecioArs(Integer precioArs) {
        this.precioArs = precioArs;
    }
    //@Override
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //@Override
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean sePuedeEmpaquetar() {
        // Verificar si el producto se puede empaquetar con otros según su categoría
        return categoria != CategoriaProducto.HIGIENE;
    }
    public boolean estaVencido() {
        LocalDate fechaActual = LocalDate.now();
        if (categoria == CategoriaProducto.ALIMENTOS_FRESCOS || categoria == CategoriaProducto.ALIMENTOS_CONGELADOS || categoria == CategoriaProducto.HIGIENE || categoria == CategoriaProducto.MASCOTAS) {
            return fechaActual.isAfter(fechaVencimiento);
        } else if (categoria == CategoriaProducto.ALIMENTOS_NO_PERECEDEROS) {
            LocalDate fechaVencimientoLimite = fechaVencimiento.minusMonths(3);
            return fechaActual.isAfter(fechaVencimientoLimite);
        }
        return false;
    }

//toDo
    //@Override
    //	public String toString() {
    //		return "Producto [categoria=" + getCategoria() + ", referencia=" + referencia + ", peso=" + peso + ", volumen="
    //				+ volumen + ", contenedor=" + contenedor.getReferencia() + "]";
    //	}
}

