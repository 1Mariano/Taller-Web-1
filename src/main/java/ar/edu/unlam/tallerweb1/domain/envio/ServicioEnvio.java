package ar.edu.unlam.tallerweb1.domain.envio;


/*import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;*/
import ar.edu.unlam.tallerweb1.domain.contenedor.IContenedor;

import java.util.List;

public interface ServicioEnvio {


    //List<Contenedor> getContenedores();
    Double espacioDisponible();
    Double espacioOcupado();
    Double distanciaEnvio();



}
