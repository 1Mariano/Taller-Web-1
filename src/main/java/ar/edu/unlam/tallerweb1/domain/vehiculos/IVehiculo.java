package ar.edu.unlam.tallerweb1.domain.vehiculos;

import ar.edu.unlam.tallerweb1.domain.contenedor.IContenedor;
import ar.edu.unlam.tallerweb1.domain.enums.TipoVehiculo;

import java.util.List;

public interface IVehiculo {


    Integer getVolumenVehiculo();
    Integer volumenDisponibleVehiculo();
    Integer getPesoSoportadoVehiculo();
    Integer getDistanciaMaximaVehiculo();

    List<IContenedor> getContenedores();
    TipoVehiculo getTipo();

    Boolean guardar(IContenedor contenedor);
    Boolean soporta(IContenedor contenedor);

}
