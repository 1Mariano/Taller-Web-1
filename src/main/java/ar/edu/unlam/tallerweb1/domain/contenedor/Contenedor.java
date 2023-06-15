package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;

import javax.persistence.*;
import java.util.List;


@Entity
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double alto;
    private Double largo;
    private Double ancho;
    private Double pesoSoportado;
    private Double volumenMaximo;
    private Double pesoCargado;
    private Double volumenOcupado;
    private Double volumenDisponible;
    private Double pesoDisponible;
    @ManyToOne
    private Vehiculo vehiculo;
    @OneToMany(mappedBy = "contenedor", cascade = CascadeType.ALL)
    private List<Producto> listaProductos;

    private TipoContenedor tipoContenedor;

    public Contenedor(Long id, Double alto, Double largo, Double ancho, Double pesoSoportado, TipoContenedor tipoContenedor) {
        this.id = id;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.pesoSoportado = pesoSoportado;
        this.tipoContenedor = tipoContenedor;
    }

    public Contenedor() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getVolumenMaximo() {
        return volumenMaximo;
    }

    public void setVolumenMaximo(Double alto, Double ancho, Double largo) {
        this.volumenMaximo = this.alto * this.ancho * this.largo;;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Double getAlto() {
        return alto;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getPesoSoportado() {
        return pesoSoportado;
    }

    public void setPesoSoportado(Double pesoSoportado) {
        this.pesoSoportado = pesoSoportado;
    }

    public TipoContenedor getTipoContenedor() {
        return tipoContenedor;
    }

    public void setTipoContenedor(TipoContenedor tipoContenedor) {
        this.tipoContenedor = tipoContenedor;
    }

    /*public void setId(Long id) {
        this.id = id;
    }*/

    public Long getId() {
        return id;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Double getPesoCargado() {
        return pesoCargado;
    }

    public void setPesoCargado(Double pesoCargado) {
        this.pesoCargado = pesoCargado;
    }

    public Double getVolumenOcupado() {
        return volumenOcupado;
    }

    public void setVolumenOcupado(Double volumenOcupado) {
        this.volumenOcupado = volumenOcupado;
    }

    public Double getVolumenDisponible() {
        return volumenDisponible;
    }

    public void setVolumenDisponible(Double volumenDisponible) {
        this.volumenDisponible = volumenDisponible;
    }

    public Double getPesoDisponible() {
        return pesoDisponible;
    }

    public void setPesoDisponible(Double pesoDisponible) {
        this.pesoDisponible = pesoDisponible;
    }
}
