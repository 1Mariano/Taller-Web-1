package ar.edu.unlam.tallerweb1.domain.pedidos;

import ar.edu.unlam.tallerweb1.domain.enums.EstadoPago;
import ar.edu.unlam.tallerweb1.domain.enums.EstadoPedido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioCompra")
@Transactional
public class ServicioCompraImpl implements ServicioCompra {
    @Override
    public void cambiarEstadoDePago(EstadoPago estadoPago) {
        estadoPago = EstadoPago.PAGADO;
    }

    @Override
    public void cambiarEstadoDePedido(EstadoPedido estadoPedido) {
        estadoPedido = EstadoPedido.EN_PREPARACION;
    }
}
