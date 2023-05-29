package ar.edu.unlam.tallerweb1.domain.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioControlDeVencimiento {

    private ServicioProducto servicioProducto;

    @Autowired
    public VerificacionCaducidadService(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }
//TODO ver los comentarios y modificar
    public void verificarCaducidadProductos() {
        List<Producto> productos = ServicioProducto.obtenerProductos();

        for (Producto producto : productos) {
            if (producto.estaVencido()) {
                // Realizar la acción correspondiente para productos vencidos
                // eliminar el producto del stock y eliminar el system out
                System.out.println("El producto " + producto.getNombre() + " está vencido.");
            } else {
                // Realizar la acción correspondiente para productos no vencidos
                // agregar al contenedor y eliminar el systemo ut
                System.out.println("El producto " + producto.getNombre() + " no está vencido.");
            }
        }
    }
}

}
