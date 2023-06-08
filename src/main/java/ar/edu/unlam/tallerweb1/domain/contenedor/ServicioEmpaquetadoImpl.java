package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.producto.ServicioListadoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioEmpaquetado")
@Transactional
public class ServicioEmpaquetadoImpl implements ServicioEmpaquetado{

    private RepositorioEmpaquetado repositorioEmpaquetado;

    @Autowired
    public ServicioEmpaquetadoImpl(RepositorioEmpaquetado repositorioEmpaquetado){
        this.repositorioEmpaquetado = repositorioEmpaquetado;
    }
}