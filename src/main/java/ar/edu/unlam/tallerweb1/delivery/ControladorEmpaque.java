package ar.edu.unlam.tallerweb1.delivery;


import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.ServicioEmpaquetado;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    @RequestMapping("/empaquetar")
    public class ControladorEmpaque {

        @Autowired
        private ServicioEmpaquetado servicioEmpaquetado;

        @PostMapping
        public Contenedor empaquetarProductos(@RequestBody List<Producto> productos) {
            return servicioEmpaquetado.empaquetarProductos(productos);
        }
    }


