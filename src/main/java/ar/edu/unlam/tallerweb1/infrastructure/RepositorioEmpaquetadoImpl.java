package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.contenedor.RepositorioEmpaquetado;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioEmpaquetado")
public class RepositorioEmpaquetadoImpl implements RepositorioEmpaquetado {

    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioEmpaquetadoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
