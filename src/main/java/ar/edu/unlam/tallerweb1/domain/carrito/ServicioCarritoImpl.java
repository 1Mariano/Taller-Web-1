package ar.edu.unlam.tallerweb1.domain.carrito;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.producto.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioCarrito")
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito{


    private RepositorioProducto repositorioProducto;

    @Autowired ServicioCarritoImpl(RepositorioProducto repositorioProducto){
        this.repositorioProducto = repositorioProducto;
    }
    @Override
    public Producto obtenerProductoPorId(Long productoId) {
        return this.repositorioProducto.buscarProductoPorId(productoId);
    }
}
