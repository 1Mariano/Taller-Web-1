package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor_Producto;
import ar.edu.unlam.tallerweb1.domain.contenedor.RepositorioEmpaquetado;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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


    @Override
    public void guardarEmpaque(Contenedor_Producto prod) {
        final Session session = sessionFactory.getCurrentSession();
        Producto producto = session.get(Producto.class, prod.getProducto().getId());
        Envio envio = session.get(Envio.class, prod.getEnvio().getId());
        Contenedor contenedor = session.get(Contenedor.class, prod.getContenedor().getId());
        //sessionFactory.getCurrentSession().save(prod);
        Contenedor_Producto c = new Contenedor_Producto();
        c.setContenedor(contenedor);
        c.setEnvio(envio);
        c.setProducto(producto);
        session.save(c);
    }

    @Override
    public void modificarContenedor(Contenedor contenedor) {
        sessionFactory.getCurrentSession().update(contenedor);
    }
}
