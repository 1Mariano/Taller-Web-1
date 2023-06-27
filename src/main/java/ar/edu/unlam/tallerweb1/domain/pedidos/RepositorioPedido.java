package ar.edu.unlam.tallerweb1.domain.pedidos;

public interface RepositorioPedido {

    void agregarPedido(Pedido pedido);

    Pedido buscarPedidoPorUsuarioDni();

    void modificarPedido(Pedido pedido);
}
