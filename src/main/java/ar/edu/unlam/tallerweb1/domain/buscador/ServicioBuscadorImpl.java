package ar.edu.unlam.tallerweb1.domain.buscador;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.producto.RepositorioProducto;
import ar.edu.unlam.tallerweb1.exceptions.NoSeEncontraronCoincidenciasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioBuscador")
@Transactional
public class ServicioBuscadorImpl implements ServicioBuscador {

    @Autowired
    private RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioBuscadorImpl(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    @Override
    public List<Producto> obtenerProductosPorNombreMarcaOCategoria(String busqueda) throws NoSeEncontraronCoincidenciasException {
        List<Producto> productosBuscados = this.repositorioProducto.listarProductosPorNombreMarcaOCategoria(busqueda);
        if (productosBuscados == null || productosBuscados.isEmpty()) {
            throw new NoSeEncontraronCoincidenciasException();
        } else {
            return productosBuscados;
        }
    }
}
