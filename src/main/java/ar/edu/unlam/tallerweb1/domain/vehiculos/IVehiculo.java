package ar.edu.unlam.tallerweb1.domain.vehiculos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.IContenedor;
import ar.edu.unlam.tallerweb1.domain.enums.TipoVehiculo;

import java.util.List;

public interface IVehiculo {


    Integer getVolumenVehiculo();
    Integer volumenDisponibleVehiculo();
    Integer getPesoSoportadoVehiculo();
    Integer getDistanciaMaximaVehiculo();

    List<Contenedor> getContenedores();
    TipoVehiculo getTipo();

    Boolean guardar(Contenedor contenedor);
    Boolean soporta(Contenedor contenedor);

}
