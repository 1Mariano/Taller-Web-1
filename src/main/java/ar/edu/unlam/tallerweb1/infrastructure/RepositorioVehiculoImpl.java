package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor_Producto;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.RepositorioVehiculo;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioVehiculo")
public class RepositorioVehiculoImpl implements RepositorioVehiculo {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioVehiculoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Vehiculo> obtenerVehiculos() {
        return sessionFactory.getCurrentSession().createCriteria(Vehiculo.class)
                .list();
    }

    @Override
    public Vehiculo obtenerVehiculoPorId(Long idVehiculoAsignado) {
        return (Vehiculo) sessionFactory.getCurrentSession().createCriteria(Vehiculo.class)
                .add(Restrictions.eq("id", idVehiculoAsignado))
                .uniqueResult();
    }


    /*
    @Override
    public void eliminarVehiculoVacio(Vehiculo vehiculoAEliminar) {
        sessionFactory.getCurrentSession().delete(vehiculoAEliminar);
    }
    */
}

