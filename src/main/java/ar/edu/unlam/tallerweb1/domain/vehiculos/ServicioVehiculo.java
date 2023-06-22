package ar.edu.unlam.tallerweb1.domain.vehiculos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;

import java.util.List;

public interface ServicioVehiculo {

    void guardarContenedoresEnVehiculo(Vehiculo vehiculo, List<Contenedor> listaContenedores);
}
