package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor_Producto;
import ar.edu.unlam.tallerweb1.domain.contenedor.RepositorioEmpaquetado;
import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        /*Producto producto = session.get(Producto.class, prod.getProducto().getId());
        Envio envio = session.get(Envio.class, prod.getEnvio().getId());
        Contenedor contenedor = session.get(Contenedor.class, prod.getContenedor().getId());
        //sessionFactory.getCurrentSession().save(prod);
        Contenedor_Producto c = new Contenedor_Producto();
        c.setContenedor(contenedor);
        c.setEnvio(envio);
        c.setProducto(producto);*/
        session.save(prod);
    }

    @Override
    public void modificarContenedor(Contenedor contenedor) {
        sessionFactory.getCurrentSession().update(contenedor);
    }

    @Override
    public void eliminarContenedorVacio(Contenedor contenedorAEliminar) {
        sessionFactory.getCurrentSession().delete(contenedorAEliminar);
    }

    @Override
    public List<Contenedor_Producto> obtenerContenedoresConProductos() {
        final Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Contenedor_Producto.class)
                .list();
    }


    @Override
    public List<Contenedor> obtenerLosContenedoresDeTipoCajaDeUnEnvio(Long envioId) {
        final Session session = sessionFactory.getCurrentSession();
        Envio envio = session.get(Envio.class, envioId); // Obtener el objeto Envio según el ID
        List<Contenedor_Producto> contenedorProductos = session.createCriteria(Contenedor_Producto.class)
                .add(Restrictions.eq("envio", envio)) // Pasar el objeto Envio en lugar del ID
                .list();

        List<Contenedor> contenedoresCaja = new ArrayList<>();
        for (Contenedor_Producto contenedorProducto : contenedorProductos) {
            if (contenedorProducto.getContenedor().getTipoContenedor().equals(TipoContenedor.CAJA)){
                contenedoresCaja.add(contenedorProducto.getContenedor());
            }
        }
        return contenedoresCaja;
    }

    @Override
    public List<Contenedor> obtenerLosContenedoresDeTipoBolsaDeUnEnvio(Long numeroEnvio) {
        final Session session = sessionFactory.getCurrentSession();
        Envio envio = session.get(Envio.class, numeroEnvio); // Obtener el objeto Envio según el ID
        List<Contenedor_Producto> contenedorProductos = session.createCriteria(Contenedor_Producto.class)
                .add(Restrictions.eq("envio", envio)) // Pasar el objeto Envio en lugar del ID
                .list();

        List<Contenedor> contenedoresBolsa = new ArrayList<>();
        for (Contenedor_Producto contenedorProducto : contenedorProductos) {
            if (contenedorProducto.getContenedor().getTipoContenedor().equals(TipoContenedor.BOLSA)){
                contenedoresBolsa.add(contenedorProducto.getContenedor());
            }
        }
        return contenedoresBolsa;
    }

    @Override
    public List<Producto> obtenerLosProductosDeUnContenedor(Long contId) {
        final Session session = sessionFactory.getCurrentSession();
        Contenedor contenedor = session.get(Contenedor.class, contId); // Obtener el objeto Envio según el ID
        List<Contenedor_Producto> contenedorProductos = session.createCriteria(Contenedor_Producto.class)
                .add(Restrictions.eq("contenedor", contenedor)) // Pasar el objeto Envio en lugar del ID
                .list();

        List<Producto> productos = new ArrayList<>();
        for (Contenedor_Producto contenedorProducto : contenedorProductos) {
            productos.add(contenedorProducto.getProducto()); // Obtener el Producto
        }
        return productos;
    }
}
