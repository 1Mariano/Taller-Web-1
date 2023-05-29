package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.producto.RepositorioProducto;
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
    public Producto buscarProducto(Integer peso, String nombre, String marca) {
        final Session session = sessionFactory.getCurrentSession();
        return (Producto) session.createCriteria(Producto.class)
                .add(Restrictions.eq("nombre", nombre))
                .add(Restrictions.eq("marca", marca))
                .add(Restrictions.eq("peso", peso))
                .uniqueResult();
    }

   /* @Override
    public void guardarProducto(Producto producto) {

    }*/

   /* @Override
    public List<Producto> buscarProductoPorNombre(String nombre) {
        return null;
    }*/

    /*@Override
    public void modificarProducto(Producto producto) {

    }*/

    @Override
    public void eliminarProducto(Producto producto) {

    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return null;
    }

    @Override
    public void guardarProducto(Producto producto) {
        sessionFactory.getCurrentSession().save(producto);
    }

    @Override
    public List<Producto> buscarProductoPorNombre(String nombre) {
        return sessionFactory.getCurrentSession().createCriteria(Producto.class)
                .add(Restrictions.like("nombre", nombre, MatchMode.ANYWHERE)).list();
    }


    //ToDo logica?
    @Override
    public void modificarProducto(Producto producto) {
        sessionFactory.getCurrentSession().update(producto);
    }
}
