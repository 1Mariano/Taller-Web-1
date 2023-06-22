package ar.edu.unlam.tallerweb1.domain.vehiculos;

//import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.enums.TipoVehiculo;
import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vehiculo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patente;
    private String modelo;
    private Double ancho;
    private Double alto;
    private Double largo;
    private Double volumenMaximo;
    private Double pesoMaximoSoportado;
    private Double distanciaMaxima;
    private Double pesoDisponible;
    private Double volumenDisponible;
    private Double pesoCargado;
    private Double volumenOcupado;
    private TipoVehiculo tipoVehiculo;
    @OneToOne
    private Pedido pedido;
    @OneToMany(mappedBy = "vehiculo")
    private List<Contenedor> listaContenedores;

    public Vehiculo(Long id, String patente, String modelo, Double ancho, Double alto, Double largo, Double volumenMaximo, Double pesoMaximoSoportado, Double distanciaMaxima, TipoVehiculo tipoVehiculo) {
        this.id = id;
        this.patente = patente;
        this.modelo = modelo;
        this.ancho = ancho;
        this.alto = alto;
        this.largo = largo;
        this.volumenMaximo = volumenMaximo;
        this.pesoMaximoSoportado = pesoMaximoSoportado;
        this.distanciaMaxima = distanciaMaxima;
        this.tipoVehiculo = tipoVehiculo;
        //this.contenedores = new ArrayList<Contenedor>();
    }

    public Vehiculo() {

    }

    public Double obtenerPesoMaximoSoportado() {
        if (tipoVehiculo == TipoVehiculo.MOTO) {
            pesoMaximoSoportado = 35.0;
        } else if (tipoVehiculo == TipoVehiculo.AUTO) {
            pesoMaximoSoportado = 75.0;
        } else if (tipoVehiculo == TipoVehiculo.CAMIONETA) {
            pesoMaximoSoportado = 700.0;
        }
        return pesoMaximoSoportado;
    }

    public Double obtenerDistanciaMaxima() {
        if (tipoVehiculo == TipoVehiculo.MOTO) {
            distanciaMaxima = 20.0;
        } else if (tipoVehiculo == TipoVehiculo.AUTO) {
            distanciaMaxima = 80.0;
        } else if (tipoVehiculo == TipoVehiculo.CAMIONETA) {
            distanciaMaxima = 150.0;
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
    public Double getVolumenVehiculo() {
        return this.volumenMaximo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
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

    public Double getDistanciaMaxima() {
        return distanciaMaxima;
    }

    public void setDistanciaMaxima(Double distanciaMaxima) {
        this.distanciaMaxima = distanciaMaxima;
    }

    public Double getVolumenMaximo() {
        return volumenMaximo;
    }

    public void setVolumenMaximo(Double volumenMaximo) {
        this.volumenMaximo = volumenMaximo;
    }

    public Double getPesoMaximoSoportado() {
        return pesoMaximoSoportado;
    }

    public void setPesoMaximoSoportado(Double pesoMaximoSoportado) {
        this.pesoMaximoSoportado = pesoMaximoSoportado;
    }

    public Double getPesoDisponible() {
        return pesoDisponible;
    }

    public void setPesoDisponible(Double pesoCargado) {
        this.pesoDisponible = getPesoMaximoSoportado();
    }

    public Double getVolumenDisponible() {
        return volumenDisponible;
    }

    public void setVolumenDisponible(Double volumenOcupado) {
        this.volumenDisponible = getVolumenMaximo();
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

    public List<Contenedor> getListaContenedores() {
        return listaContenedores;
    }

    public void setListaContenedores(List<Contenedor> listaContenedores) {
        this.listaContenedores = listaContenedores;
    }



    /*
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
    }*/

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