package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor_Producto;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.envio.RepositorioEnvio;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo_Contenedor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        return session.createCriteria(Contenedor_Producto.class)
                .add(Restrictions.eq("envio", envioId))
                .list();

    }
}
