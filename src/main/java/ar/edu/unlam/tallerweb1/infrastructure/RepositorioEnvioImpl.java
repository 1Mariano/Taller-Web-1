package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor_Producto;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.envio.RepositorioEnvio;
import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo_Contenedor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("repositorioEnvio")
public class RepositorioEnvioImpl implements RepositorioEnvio {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEnvioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void guardarEnvio(Envio envioNuevo) {
        sessionFactory.getCurrentSession().save(envioNuevo);
    }

    @Override
    public void modificarEnvio(Envio envioNuevo) {
        sessionFactory.getCurrentSession().update(envioNuevo);
    }

    @Override
    public void guardarVehiculoContenedor(Vehiculo_Contenedor vc) {
        final Session session = sessionFactory.getCurrentSession();
        session.save(vc);
    }

    @Override
    public Envio obtenerEnvioDelPedido(Long envioId) {
        final Session session = sessionFactory.getCurrentSession();
        return (Envio) session.createCriteria(Envio.class)
                .add(Restrictions.eq("id", envioId))
                .uniqueResult();
    }

    @Override
    public List<Producto> obtenerLosProductosDeUnEnvio(Long envioId) {
        final Session session = sessionFactory.getCurrentSession();
        Envio envio = session.get(Envio.class, envioId); // Obtener el objeto Envio según el ID
        List<Contenedor_Producto> contenedorProductos = session.createCriteria(Contenedor_Producto.class)
                .add(Restrictions.eq("envio", envio)) // Pasar el objeto Envio en lugar del ID
                .list();

        List<Producto> idsProductos = new ArrayList<>();
        for (Contenedor_Producto contenedorProducto : contenedorProductos) {
            idsProductos.add(contenedorProducto.getProducto()); // Obtener la ID del Producto
        }
        return idsProductos;
    }

    @Override
    public void actualizarPedido(Pedido p) {
        sessionFactory.getCurrentSession().update(p);
    }
}
