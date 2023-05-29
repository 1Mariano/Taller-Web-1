package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
// ToDo ver relacion de bdd, ver get y set de id
/*@Entity
@Inheritance(strategy = InheritanceType.JOINED)*/
public abstract class Contenedor  implements IContenedor{



    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Long id;
    private Integer alto;
    private Integer resistencia;
    //@OneToMany
    private List<Producto> productos;
    //@ManyToOne
    private Vehiculo vehiculo;
    private Integer pesoContenedorCargado;
    //@ManyToOne
    private Pedido pedido;

    public Contenedor(Long id, Integer alto, Integer resistencia) {
        this.id = id;
        this.alto = alto;
        this.resistencia = resistencia;
        this.productos = new ArrayList<Producto>();
    }

    public Contenedor() {

    }


    @Override
    public Integer getVolumenContenedor() {
        return (alto*getSuperficie());
    }

   /* @Override
    public Integer volumenDisponibleContenedor() {
        return  getVolumenContenedor() - volumenOcupadoContenedor();
    }*/

    /*private Integer volumenOcupadoContenedor() {
        int res = 0;
        for (IProducto p : productos) {
            res += p.getVolumen();
        }
        return res;
    }*/

    @Override
    public Integer getResistencia() {
        return resistencia;
    }

    /*@Override
    public Boolean meter(Producto producto) {
        boolean resistenciaOk = resiste(producto);
        boolean volumenOk = producto.tengoEspacio(this);
        boolean compatibilidadOk = true;

        for (Producto p : productos) {
            boolean compatibleOk = producto.esCompatible(p);
            compatibilidadOk &= compatibleOk;
        }

        boolean acepta = resistenciaOk && volumenOk && compatibilidadOk;
        if (acepta) {
            productos.add(producto);
            producto.meter(this);
            pesoContenedorCargado += producto.getPeso();
        }
        return acepta;
    }*/

    @Override
    public Boolean resiste(Producto producto) {
        return resistencia > producto.getPeso();
    }

    @Override
    public Boolean tengoEspacio(Vehiculo vehiculo) {
        return vehiculo.volumenDisponibleVehiculo() > getVolumenContenedor();
    }

    @Override
    public void guardar(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public Integer pesoTotalContenedor() {
        return  this.pesoContenedorCargado;
    }

    @Override
    public List<Producto> getProductos() {
        return productos;
    }
    // ToDo resolver problema override


}
