package ar.edu.unlam.tallerweb1.domain.vehiculos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.IContenedor;
import ar.edu.unlam.tallerweb1.domain.enums.TipoVehiculo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public /*abstract*/ class Vehiculo /*implements IVehiculo*/ {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Long id;
    private String patente;
    private String modelo;
    private Integer ancho;
    private Integer alto;
    private Integer largo;
    private Integer volumen;
    private Integer pesoMaximoSoportado;
    private Integer distanciaMaxima;
    private TipoVehiculo tipoVehiculo;
    //ToDo lista de contenedores y pedido
    //@OneToMany
    private List<Contenedor> contenedores;

    public Vehiculo(Long id, String patente, String modelo, Integer ancho, Integer alto, Integer largo, Integer pesoMaximoSoportado, Integer distanciaMaxima, TipoVehiculo tipoVehiculo) {
        this.id = id;
        this.patente = patente;
        this.modelo = modelo;
        this.ancho = ancho;
        this.volumen = (alto * ancho * largo);
        this.alto = alto;
        this.largo = largo;
        this.pesoMaximoSoportado = pesoMaximoSoportado;
        this.distanciaMaxima = distanciaMaxima;
        this.tipoVehiculo = tipoVehiculo;
        this.contenedores = new ArrayList<Contenedor>();
    }

    public Vehiculo() {

    }

    public Integer obtenerPesoMaximoSoportado() {
        if (tipoVehiculo == TipoVehiculo.AUTO) {
            pesoMaximoSoportado = 80;
        } else if (tipoVehiculo == TipoVehiculo.MOTO) {
            pesoMaximoSoportado = 35;
        } else if (tipoVehiculo == TipoVehiculo.CAMIONETA) {
            pesoMaximoSoportado = 300;
        }
        return pesoMaximoSoportado;
    }

    public Integer obtenerDistanciaMaxima() {
        if (tipoVehiculo == TipoVehiculo.AUTO) {
            distanciaMaxima = 80;
        } else if (tipoVehiculo == TipoVehiculo.MOTO) {
            distanciaMaxima = 20;
        } else if (tipoVehiculo == TipoVehiculo.CAMIONETA) {
            distanciaMaxima = 150;
        }
        return distanciaMaxima;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    //@Override
    public Integer getVolumenVehiculo() {
        return this.volumen;
    }

    // @Override
    public List<Contenedor> getContenedores() {
        return contenedores;
    }

    //@Override
    public Integer volumenDisponibleVehiculo() {
        return getVolumenVehiculo() - volumenOcupadoVehiculo();
    }

    private Integer volumenOcupadoVehiculo() {
        int res = 0;
        for (Contenedor c : contenedores) {
            res += c.getVolumenContenedor();
        }
        return res;
    }

    //ToDo métodos guardar y soporta
/*
    //@Override
    public Boolean guardar(Contenedor contenedor) {
        Boolean resistenciaOk = soporta(contenedor);
        Boolean volumenOk = contenedor.tengoEspacio(this);

        Boolean acepta = resistenciaOk && volumenOk;

        if (acepta){
            contenedores.add(contenedor);
            contenedor.guardar(this);
        }
        return acepta;
    }

    @Override
    public Boolean soporta(Contenedor contenedor) {
        return getPesoSoportadoVehiculo() > contenedor.pesoTotalContenedor();
    }*/

}
