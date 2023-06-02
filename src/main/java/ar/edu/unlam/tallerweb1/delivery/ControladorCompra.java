package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.pedidos.ServicioCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControladorCompra {

    private final ServicioCompra servicioCompra;

    @Autowired
    public ControladorCompra(ServicioCompra servicioCompra) {
        this.servicioCompra = servicioCompra;
    }


}
