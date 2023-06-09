package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.contenedor.ServicioEmpaquetado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorEmpaquetado {

    private final ServicioEmpaquetado servicioEmpaquetado;

    @Autowired
    public ControladorEmpaquetado(ServicioEmpaquetado servicioEmpaquetado){
        this.servicioEmpaquetado = servicioEmpaquetado;
    }



}


