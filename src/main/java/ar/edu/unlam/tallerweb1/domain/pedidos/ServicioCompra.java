package ar.edu.unlam.tallerweb1.domain.pedidos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.exceptions.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;

import java.util.List;


public interface ServicioCompra {

    void cambiarEstadoDePagoAPagado(Pedido pedido);

    void cambiarEstadoDePedidoAEnPreparacion(Pedido pedido);

    void cambiarEstadoDeEnvioAEnPreparacion(Envio envio);

    void agregarPedido(Pedido pedidoNuevo);

    void guardarDatosEnvio(Envio envioNuevo) throws CampoCalleInvalidoException, CampoLocalidadInvalidoException, CampoCpInvalidoException, CampoNumeroInvalidoException;

    void empaquetarProductos(List<Producto> productos, Envio envio);

    Double obtenerPesoTotalDeLosContenedores();

    Double obtenerVolumenTotalDeLosContenedores();

    List<Contenedor> devolverContenedoresConProductos();

    Double obtenerCostoTotalDeLosProductos(List<Producto> listaProductos);

    Double obtenerCostoTotalDelPedido(Pedido pedido, Envio envio);

    void pagar(Pedido pedido, Envio envio) throws NoSeConcretoElPagoException;

    void modificarPedido(Pedido pedido);

    void modificarEnvio(Envio envio);


    List<Contenedor> obtenerCajasPorEnvio(Long envio);

    List<Contenedor> obtenerBolsasPorEnvio(Long numeroPedido);

    List<Producto> obtenerProductosDeUnContenedor(Long contId);

    void vaciarCarrito(Long idUsuario);

    Payment verificarPago(Long paymentId) throws MPException, MPApiException;

    boolean verificarSiExistePedidoActivo(Long usuario);

    List<Pedido> obtenerPedidosDeUnUsuario(Long usuarioId);
}
