package ar.edu.unlam.tallerweb1.domain.envio;

import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;

public interface RepositorioEnvio {

    void guardarEnvio(Envio envioNuevo);

    void modificarEnvio(Envio envioNuevo);

}
