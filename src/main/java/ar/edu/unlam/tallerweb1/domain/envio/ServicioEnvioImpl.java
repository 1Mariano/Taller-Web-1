package ar.edu.unlam.tallerweb1.domain.envio;


import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("ServicioEnvio")
@Transactional
public class ServicioEnvioImpl implements ServicioEnvio {

    private RepositorioEnvio servicioEnvio;

    @Autowired
    public ServicioEnvioImpl(RepositorioEnvio servicioEnvio){

        this.servicioEnvio = servicioEnvio;
    }
    @Override
    public Double dimensionesDisponibles() {
        return null;
    }
}
