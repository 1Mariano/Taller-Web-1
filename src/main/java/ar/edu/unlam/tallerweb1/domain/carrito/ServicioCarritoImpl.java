package ar.edu.unlam.tallerweb1.domain.carrito;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.producto.RepositorioProducto;
import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("servicioCarrito")
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito{


    @Autowired
    private RepositorioProducto repositorioProducto;
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired ServicioCarritoImpl(RepositorioProducto repositorioProducto){
        this.repositorioProducto = repositorioProducto;
    }



    @Override
    public Producto obtenerProductoPorId(Long productoId) {
        return this.repositorioProducto.buscarProductoPorId(productoId);
    }

    @Override
    public void agregarProductoAlCarrito(Producto producto, Long usuarioId) {
        Usuario usuario = repositorioUsuario.buscarUsuarioPorId(usuarioId);
        if (usuario != null){

            this.repositorioProducto.agregarProductoAlCarrito(producto.getId(), usuario.getId());
        }
    }



    @Override
    public void BorrarProductoDelCarrito(Producto producto, Long idUsuario) {
        List<Carrito> array = this.repositorioProducto.obtenerTodosLosProductosDelCarritoDelUsuario(idUsuario);
        for (Carrito carrito : array){
            if (carrito.getUsuario().getId() == idUsuario && carrito.getProducto().getId() == producto.getId()){
                this.repositorioProducto.eliminarProductoDelCarrito(carrito);
                return;
            }
        }

    }


    @Override
    public List<Producto> obtenerTodosLosProductosDelCarrito(Long id) {
        List<Carrito> array = this.repositorioProducto.obtenerTodosLosProductosDelCarritoDelUsuario(id);
        List<Producto> nuevo = new ArrayList<Producto>();
        for (Carrito carrito : array){
            nuevo.add(carrito.getProducto());
        }
        return nuevo;

    }
}
