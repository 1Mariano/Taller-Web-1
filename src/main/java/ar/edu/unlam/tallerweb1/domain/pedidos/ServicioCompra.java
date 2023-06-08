package ar.edu.unlam.tallerweb1.domain.pedidos;

import ar.edu.unlam.tallerweb1.domain.enums.EstadoPago;
import ar.edu.unlam.tallerweb1.domain.enums.EstadoPedido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface ServicioCompra {

    void cambiarEstadoDePago(EstadoPago estadoPago);
    void cambiarEstadoDePedido(EstadoPedido estadoPedido);
    
}
