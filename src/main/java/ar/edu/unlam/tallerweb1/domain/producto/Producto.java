package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.enums.CategoriaProducto;

import javax.persistence.*;
import java.time.LocalDate;

//TODO BORRAR LOS COMENTARIOS UNA VEZ QUE ESTE VERIFICADO EL FUNCIONAMIENTO DEL CODIGO
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public /*abstract*/ class Producto /*implements IProducto*/{
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
    private Integer precioArs;

    private String descripcion;
    private String img;
    private LocalDate fechaVencimiento;
    private CategoriaProducto categoria;

    @ManyToOne
    private Contenedor contenedor;

    public Producto(Long id, Integer peso, Integer volumen, String nombre, String marca, Integer alto, Integer ancho, Integer largo, Integer precioArs, String descripcion, String img, LocalDate fechaVencimiento, CategoriaProducto categoria) {
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
    public Integer getPeso() {
        return this.peso;
    }

    //@Override
    public Integer getVolumen() {
        return this.volumen;
    }
    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    //@Override
    public Boolean tengoEspacio(Contenedor contenedor) {
        return contenedor.volumenDisponibleContenedor() > volumen;
    }
   // @Override
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
       if (categoria == CategoriaProducto.ALIMENTOS_FRESCOS || categoria == CategoriaProducto.CONGELADOS || categoria == CategoriaProducto.HIGIENE || categoria == CategoriaProducto.MASCOTAS) {
           return fechaActual.isAfter(fechaVencimiento);
       } else if (categoria == CategoriaProducto.NO_PERECEDEROS) {
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
