package ar.edu.unlam.tallerweb1.domain.pedidos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.enums.EstadoPago;
import ar.edu.unlam.tallerweb1.domain.enums.EstadoPedido;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.exceptions.CampoInvalidoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ServicioCompra {

    void cambiarEstadoDePago(EstadoPago estadoPago);
    void cambiarEstadoDePedido(EstadoPedido estadoPedido);
    void guardarDatosEnvio(Envio envioNuevo) throws CampoInvalidoException;
    void empaquetarProductos(List<Producto> productos, Envio envio);


    Double obtenerPesoTotalDeLosContenedores();

    Double obtenerVolumenTotalDeLosContenedores();

    List<Contenedor> devolverContenedoresConProductos();
}
