package ar.edu.unlam.tallerweb1.domain.vehiculos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;

import java.util.List;

public interface RepositorioVehiculo {

    List<Vehiculo> obtenerVehiculos();

    Vehiculo obtenerVehiculoPorId(Long idVehiculoAsignado);

    // void eliminarVehiculoVacio(Vehiculo );
}
