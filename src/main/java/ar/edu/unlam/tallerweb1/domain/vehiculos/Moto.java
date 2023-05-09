package ar.edu.unlam.tallerweb1.domain.vehiculos;

import ar.edu.unlam.tallerweb1.domain.enums.TipoVehiculo;


public class Moto extends Vehiculo {

    private Integer volumen;
    private final static Integer PESO_MAXIMO_SOPORTADO_MOTO = 35;
    private final static Integer DISTANCIA_MAXIMA_SOPORTADO_MOTO = 20;

    public Moto(Long id, String patente, String modelo, Integer ancho, Integer alto, Integer largo, Integer distanciaMaxima) {
        super(id, patente, modelo, ancho, alto, largo);
        this.volumen = (alto * ancho * largo);

    }


    @Override
    public Integer getVolumenVehiculo() {
        return this.volumen;
    }


    @Override
    public Integer getPesoSoportadoVehiculo() {
        return PESO_MAXIMO_SOPORTADO_MOTO;
    }

    @Override
    public Integer getDistanciaMaximaVehiculo() {
        return DISTANCIA_MAXIMA_SOPORTADO_MOTO;
    }

    @Override
    public TipoVehiculo getTipo() {
        return TipoVehiculo.MOTO;
    }
}




