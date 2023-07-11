package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.pedidos.Pedido;
import ar.edu.unlam.tallerweb1.domain.pedidos.RepositorioPedido;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioPedido")
public class RepositorioPedidoImpl implements RepositorioPedido {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioPedidoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void agregarPedido(Pedido pedido) {
        sessionFactory.getCurrentSession().save(pedido);
    }

    @Override
    public List<Pedido> buscarPedidoPorUsuarioDni(Long usuarioId) {
        final Session session = sessionFactory.getCurrentSession();
        Usuario usuario = session.get(Usuario.class, usuarioId);
        return  session.createCriteria(Pedido.class)
                .add(Restrictions.eq("usuario", usuario))
                .list();
    }

    @Override
    public List<Pedido> buscarPedidosDeUnUsuario(Long usuarioId) {
        final Session session = sessionFactory.getCurrentSession();
        Usuario usuario = session.get(Usuario.class, usuarioId);
        return session.createCriteria(Pedido.class)
                .add(Restrictions.eq("usuario", usuario))
                .list();
    }

    @Override
    public void modificarPedido(Pedido pedido) {
        sessionFactory.getCurrentSession().update(pedido);
    }
}
