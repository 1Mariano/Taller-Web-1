package ar.edu.unlam.tallerweb1.domain.vehiculos;

import ar.edu.unlam.tallerweb1.domain.enums.TipoVehiculo;

public class Auto extends Vehiculo{
    private Integer volumen;
    private final static Integer pesoMaximoSoportadoAuto = 80;
    private final static Integer distanciaMaximaAuto = 80;

    public Auto(Long id, String patente, String modelo, Integer ancho, Integer alto, Integer largo, Integer distanciaMaxima) {
        super(id, patente, modelo, ancho, alto, largo);
        this.volumen = (alto * ancho * largo);
    }


    @Override
    public Integer getVolumenVehiculo() {
        return this.volumen;
    }


    @Override
    public Integer getPesoSoportadoVehiculo() {
        return pesoMaximoSoportadoAuto;
    }

    @Override
    public Integer getDistanciaMaximaVehiculo() {
        return distanciaMaximaAuto;
    }

    @Override
    public TipoVehiculo getTipo() {
        return TipoVehiculo.AUTO;
    }
}
