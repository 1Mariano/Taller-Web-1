package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.RepositorioEmpaquetado;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioEmpaquetado")
public class RepositorioEmpaquetadoImpl implements RepositorioEmpaquetado {

    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioEmpaquetadoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void crearContenedor(Contenedor contenedor) {
        sessionFactory.getCurrentSession().save(contenedor);
    }

    @Override
    public List<Contenedor> obtenerContenedores() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Contenedor.class)
                .list();
    }


}
