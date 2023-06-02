/*package ar.edu.unlam.tallerweb1.domain.contenedor;

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
    @OneToMany
    private List<Producto> productos;

    @ManyToOne
    private Vehiculo vehiculo;

    private TipoContenedor tipoContenedor;

    public Contenedor(Long id, Double alto, Double largo, Double ancho, Double pesoSoportado, List<Producto> productos, Vehiculo vehiculo, TipoContenedor tipoContenedor) {
        this.id = id;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.pesoSoportado = pesoSoportado;
        this.productos = productos;
        this.vehiculo = vehiculo;
        this.tipoContenedor = tipoContenedor;
    }

    public Contenedor() {

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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public TipoContenedor getTipoContenedor() {
        return tipoContenedor;
    }

    public void setTipoContenedor(TipoContenedor tipoContenedor) {
        this.tipoContenedor = tipoContenedor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
*/