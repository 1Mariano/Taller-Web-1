package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.carrito.Carrito;
import ar.edu.unlam.tallerweb1.domain.enums.CategoriaProducto;
import ar.edu.unlam.tallerweb1.domain.producto.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("repositorioProducto")
public class RepositorioProductoImpl implements RepositorioProducto {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioProductoImpl(SessionFactory sessionFactory) {
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
    public void agregarProductoAlCarrito(Long productoId, Long usarioId) {
        final Session session = sessionFactory.getCurrentSession();
        Producto producto = session.get(Producto.class, productoId);
        Usuario usuario = session.get(Usuario.class, usarioId);
        Carrito carrito = new Carrito();
        carrito.setProducto(producto);
        carrito.setUsuario(usuario);
        session.save(carrito);
        //sessionFactory.getCurrentSession().save(carrito);
    }

    @Override
    public void eliminarProductoDelCarrito(Carrito carrito) {
        sessionFactory.getCurrentSession().delete(carrito);
    }

    @Override
    public List<Producto> listarProductosPorNombreMarcaOCategoria(String busqueda) {
        final Session session = sessionFactory.getCurrentSession();
        List<Producto> productosFiltrados = new ArrayList<>();

        if (busqueda.isEmpty() || busqueda.equals(" ")) {
            productosFiltrados = null;
        } else if ("alimentacion".toLowerCase().contains(busqueda.toLowerCase()) || busqueda.toLowerCase().contains("alimentación".toLowerCase()) ||
                "alimentos".toLowerCase().contains(busqueda.toLowerCase())) {
            productosFiltrados = session.createCriteria(Producto.class)
                    .add(Restrictions.or(
                            (Restrictions.like("categoria", CategoriaProducto.ALIMENTOS_NO_PERECEDEROS)),
                            (Restrictions.like("categoria", CategoriaProducto.ALIMENTOS_FRESCOS)),
                            (Restrictions.like("categoria", CategoriaProducto.ALIMENTOS_CONGELADOS))
                    ))
                    .list();
        } else if ("drogueria".toLowerCase().contains(busqueda.toLowerCase()) || busqueda.toLowerCase().contains("droguería".toLowerCase())) {
            productosFiltrados = session.createCriteria(Producto.class)
                    .add(Restrictions.like("categoria", CategoriaProducto.DROGUERIA))
                    .list();
        } else if ("higiene".toLowerCase().contains(busqueda.toLowerCase())) {
            productosFiltrados = session.createCriteria(Producto.class)
                    .add(Restrictions.like("categoria", CategoriaProducto.HIGIENE))
                    .list();
        } else if ("mascotas".toLowerCase().contains(busqueda.toLowerCase())) {
            productosFiltrados = session.createCriteria(Producto.class)
                    .add(Restrictions.like("categoria", CategoriaProducto.MASCOTAS))
                    .list();
        } else {
            productosFiltrados = session.createCriteria(Producto.class)
                    .add(Restrictions.or(
                            Restrictions.ilike("nombre", busqueda, MatchMode.ANYWHERE),
                            Restrictions.ilike("marca", busqueda, MatchMode.ANYWHERE),
                            Restrictions.ilike("descripcion", busqueda, MatchMode.ANYWHERE)
                    ))
                    .list();
        }
        return productosFiltrados;
    }

    @Override
    public List<Carrito> obtenerTodosLosProductosDelCarritoDelUsuario(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Carrito.class)
                .createAlias("producto", "p")
                .createAlias("usuario", "u")
                .add(Restrictions.eq("u.id", id))
                .list();
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