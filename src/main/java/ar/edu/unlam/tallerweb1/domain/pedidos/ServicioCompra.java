package ar.edu.unlam.tallerweb1.domain.pedidos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.exceptions.CampoInvalidoException;
import ar.edu.unlam.tallerweb1.exceptions.NoSeConcretoElPagoException;

import java.util.List;


public interface ServicioCompra {

    void cambiarEstadoDePago(Pedido pedido);

    void cambiarEstadoDePedido(Pedido pedido);

    void agregarPedido(Pedido pedidoNuevo);

    void guardarDatosEnvio(Envio envioNuevo) throws CampoInvalidoException;

    void empaquetarProductos(List<Producto> productos, Envio envio);

    Double obtenerPesoTotalDeLosContenedores();

    Double obtenerVolumenTotalDeLosContenedores();

    List<Contenedor> devolverContenedoresConProductos();

    Double obtenerCostoTotalDeLosProductos(List<Producto> listaProductos);

    Double obtenerCostoTotalDelPedido(Pedido pedido, Envio envio);

    void pagar(Pedido pedido) throws NoSeConcretoElPagoException;
}
