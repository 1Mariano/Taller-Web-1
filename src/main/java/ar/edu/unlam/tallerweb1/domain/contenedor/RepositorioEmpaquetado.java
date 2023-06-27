package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;

import java.util.List;

public interface RepositorioEmpaquetado {
    void crearContenedor(Contenedor contenedor);

    List<Contenedor> obtenerContenedores();

    void guardarEmpaque(Contenedor_Producto prod);

    void modificarContenedor(Contenedor contenedor);

    void eliminarContenedorVacio(Contenedor contenedorAEliminar);

    List<Contenedor_Producto> obtenerContenedoresConProductos();

    List<Contenedor> obtenerLosContenedoresDeTipoCajaDeUnEnvio(Long envioId);

    List<Contenedor> obtenerLosContenedoresDeTipoBolsaDeUnEnvio(Long numeroPedido);

    List<Producto> obtenerLosProductosDeUnContenedor(Long contId);
}
