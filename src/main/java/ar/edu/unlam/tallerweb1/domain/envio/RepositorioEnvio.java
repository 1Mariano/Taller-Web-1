package ar.edu.unlam.tallerweb1.domain.envio;

import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo_Contenedor;

import java.util.List;

public interface RepositorioEnvio {

    void guardarEnvio(Envio envioNuevo);

    void modificarEnvio(Envio envioNuevo);

    void guardarVehiculoContenedor(Vehiculo_Contenedor vc);

    Envio obtenerEnvioDelPedido(Long envioId);

    List<Producto> obtenerLosProductosDeUnEnvio(Long envioId);

    void actualizarPedido(Pedido p);
}
