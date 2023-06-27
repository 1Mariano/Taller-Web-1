package ar.edu.unlam.tallerweb1.domain.envio;


/*import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;*/

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;

import java.util.List;

public interface ServicioEnvio {

    //List<Contenedor> getContenedores();

    Integer distanciaEnvio();

    Double calcularCostoEnvio(Vehiculo vehiculoAsignado);

    // Double obtenerDistanciaEnKm();

    Vehiculo obtenerVehiculoDePedido(Envio envio);

    void agregarAlVehiculo(Envio envio);

    void asignarVehiculo(Envio envio, Vehiculo vehiculo);

    Envio obtenerEnvio(Envio envio);

    void modificarEnvio(Envio envioNuevo);
}
