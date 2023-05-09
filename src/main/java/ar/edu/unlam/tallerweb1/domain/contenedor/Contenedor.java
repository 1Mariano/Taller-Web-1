package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.producto.IProducto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.IVehiculo;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;
// ToDo ver relacion de bdd, ver get y set de id
//@Entity
public abstract class Contenedor  implements IContenedor{



   /// @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer alto;
    private Integer resistencia;

    private List<IProducto> productos;
    private IVehiculo vehiculo;
    private Integer pesoContenedorCargado;

    public Contenedor(Long id, Integer alto, Integer resistencia) {
        this.id = id;
        this.alto = alto;
        this.resistencia = resistencia;
        this.productos = new ArrayList<IProducto>();
    }

    public Contenedor() {

    }


    @Override
    public Integer getVolumenContenedor() {
        return (alto*getSuperficie());
    }

    @Override
    public Integer volumenDisponibleContenedor() {
        return  getVolumenContenedor() - volumenOcupadoContenedor();
    }

    private Integer volumenOcupadoContenedor() {
        int res = 0;
        for (IProducto p : productos) {
            res += p.getVolumen();
        }
        return res;
    }

    @Override
    public Integer getResistencia() {
        return resistencia;
    }

    @Override
    public Boolean meter(IProducto producto) {
        boolean resistenciaOk = resiste(producto);
        boolean volumenOk = producto.tengoEspacio(this);
        boolean compatibilidadOk = true;

        for (IProducto p : productos) {
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
    }

    @Override
    public Boolean resiste(IProducto producto) {
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
    public List<IProducto> getProductos() {
        return productos;
    }
    // ToDo resolver problema override


}
