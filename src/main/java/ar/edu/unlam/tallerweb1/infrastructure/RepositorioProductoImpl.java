package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.producto.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioProducto")
public class RepositorioProductoImpl implements RepositorioProducto {


    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioProductoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Producto buscarProducto(Long id, Integer peso, Integer volumen, String nombre, String marca, Integer precioArs) {
        final Session session = sessionFactory.getCurrentSession();
        return (Producto) session.createCriteria(Producto.class)
                .add(Restrictions.eq("nombre", nombre))
                .add(Restrictions.eq("marca", marca))
                .add(Restrictions.eq("peso", peso))
                .add(Restrictions.eq("precioArs", precioArs))
                .uniqueResult();
    }
    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public Producto buscarProductoPorId(Long productoId) {
        final Session session = sessionFactory.getCurrentSession();
        return (Producto) session.createCriteria(Producto.class)
                .add(Restrictions.eq("id", productoId))
                .uniqueResult();
    }


    @Override
    public void guardar(Producto producto) {
        sessionFactory.getCurrentSession().save(producto);
    }

    @Override
    public List<Producto> buscar(String nombre) {
        return sessionFactory.getCurrentSession().createCriteria(Producto.class)
                .add(Restrictions.like("nombre", nombre, MatchMode.ANYWHERE)).list();
    }


    //ToDo logica?
    @Override
    public void modificar(Producto producto) {
        sessionFactory.getCurrentSession().update(producto);
    }

    @Override
    public List<Producto> listarTodosLosProductos() {
        return sessionFactory.getCurrentSession().createCriteria(Producto.class)
                .list();
    }

}
