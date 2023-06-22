package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor_Producto;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.envio.RepositorioEnvio;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo_Contenedor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
