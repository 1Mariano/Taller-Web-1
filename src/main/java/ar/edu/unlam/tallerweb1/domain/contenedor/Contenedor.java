package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
// ToDo ver relacion de bdd, ver get y set de id
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public /*abstract*/ class Contenedor  /*implements IContenedor*/{



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer alto;
    private Integer ancho;
    private Integer largo;
    private Integer pesoSoportado;
    @OneToMany
    private List<Producto> productos;
    @ManyToOne
    private Vehiculo vehiculo;
    private TipoContenedor tipoContenedor;

    public Contenedor(Long id, Integer alto, Integer largo, Integer ancho, Integer pesoSoportado, TipoContenedor tipoContenedor, Integer volumenDisponible, Integer volumenOcupado) {
        this.id = id;
        this.alto = alto;
        this.pesoSoportado = pesoSoportado;
        this.productos = new ArrayList<Producto>();
        this.tipoContenedor= tipoContenedor;
        this.ancho =ancho;
        this.largo = largo;

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

    public TipoContenedor getTipoContenedor() {
        return tipoContenedor;
    }

    public void setTipoContenedor(TipoContenedor tipoContenedor) {
        this.tipoContenedor = tipoContenedor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Integer getPesoCargado() {
        Integer pesoCargado = 0;
        for (Producto producto : productos) {
            pesoCargado += producto.getPeso();
        }
        return pesoCargado;
    }

    public boolean pesoSoportado(Integer peso) {
        if (tipoContenedor == TipoContenedor.CAJA) {
            return true; // Cajas soportan cualquier peso
        } else if (tipoContenedor == TipoContenedor.BOLSA) {
            return Producto.peso <= 5; // Bolsas soportan hasta 5 kg de peso
        }
        return false; // Tipo de contenedor no reconocido
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void removerProducto(Producto producto) {
        productos.remove(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }
}




}
