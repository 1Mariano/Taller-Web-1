package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.enums.CategoriaProducto;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service("servicioListadoMascota")
@Transactional
public class ServicioListadoImpl implements ServicioListado {

    private RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioListadoImpl(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    @Override
    public List<Producto> obtenerProductos() {
        return this.repositorioProducto.listarTodosLosProductos();
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return repositorioProducto.buscarUsuarioPorId(id);
    }

    /*
    @Override
    public List<Mascota> listarProductosMascotas() {
        return this.repositorioProducto.listarMascotas();
    }

    */
    @Override
    public List<Producto> listarProductosDrogueria() {
        List<Producto> objetos = this.repositorioProducto.listarTodosLosProductos();
        List<Producto> lista = new ArrayList<>();
        for (Producto objeto : objetos) {
            if (objeto.getCategoria().equals(CategoriaProducto.DROGUERIA)) {
                lista.add(objeto);
            }
        }
        return lista;
    }

    @Override
    public List<Producto> listarProductosHigiene() {
        List<Producto> objetos = this.repositorioProducto.listarTodosLosProductos();
        List<Producto> lista = new ArrayList<>();
        for (Producto objeto : objetos) {
            if (objeto.getCategoria().equals(CategoriaProducto.HIGIENE)) {
                lista.add(objeto);
            }
        }
        return lista;
    }

    @Override
    public List<Producto> listarProductosMascota() {
        List<Producto> objetos = this.repositorioProducto.listarTodosLosProductos();
        List<Producto> lista = new ArrayList<>();
        for (Producto objeto : objetos) {
            if (objeto.getCategoria().equals(CategoriaProducto.MASCOTAS)) {
                lista.add(objeto);
            }
        }
        return lista;
    }

    @Override
    public List<Producto> listarProductosAlimento() {
        List<Producto> objetos = this.repositorioProducto.listarTodosLosProductos();
        List<Producto> lista = new ArrayList<>();
        for (Producto objeto : objetos) {
            if (objeto.getCategoria().equals(CategoriaProducto.ALIMENTOS_FRESCOS) || objeto.getCategoria().equals(CategoriaProducto.ALIMENTOS_CONGELADOS) || objeto.getCategoria().equals(CategoriaProducto.ALIMENTOS_NO_PERECEDEROS)) {
                lista.add(objeto);
            }
        }
        return lista;
    }
}
