package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.envio.RepositorioEnvio;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioEnvio")
public class RepositorioEnvioImpl implements RepositorioEnvio {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEnvioImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


}
