/*package ar.edu.unlam.tallerweb1.domain.vehiculos;

import ar.edu.unlam.tallerweb1.domain.enums.TipoVehiculo;

public class Camioneta extends Vehiculo{
    private Integer volumen;
    private final static Integer PESO_MAXIMO_SOPORTADO_CAMIONETA = 300;
    private final static Integer DISTANCIA_MAXIMA_SOPORTADA_CAMIONETA = 150;

    public Camioneta(Long id, String patente, String modelo, Integer ancho, Integer alto, Integer largo) {
        super(id, patente, modelo, ancho, alto, largo);
        this.volumen = (alto * ancho * largo);
    }


    @Override
    public Integer getVolumenVehiculo() {
        return this.volumen;
    }


    @Override
    public Integer getPesoSoportadoVehiculo() {
        return PESO_MAXIMO_SOPORTADO_CAMIONETA;
    }

    @Override
    public Integer getDistanciaMaximaVehiculo() {
        return DISTANCIA_MAXIMA_SOPORTADA_CAMIONETA;
    }

    @Override
    public TipoVehiculo getTipo() {
        return TipoVehiculo.CAMIONETA;
    }
}*/
