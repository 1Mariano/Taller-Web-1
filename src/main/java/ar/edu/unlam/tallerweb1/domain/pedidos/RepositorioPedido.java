package ar.edu.unlam.tallerweb1.domain.pedidos;

import java.util.List;

public interface RepositorioPedido {

    void agregarPedido(Pedido pedido);

    List<Pedido> buscarPedidoPorUsuarioDni(Long usuario);

    void modificarPedido(Pedido pedido);

    List<Pedido> buscarPedidosDeUnUsuario(Long usuarioId);
}