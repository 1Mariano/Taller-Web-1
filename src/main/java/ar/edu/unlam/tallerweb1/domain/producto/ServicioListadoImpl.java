package ar.edu.unlam.tallerweb1.domain.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioListadoMascota")
@Transactional
public class ServicioListadoImpl implements ServicioListado {

    private RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioListadoImpl(RepositorioProducto repositorioProducto){
        this.repositorioProducto = repositorioProducto;
    }

    @Override
    public List<Mascota> listarProductosMascotas() {
        return this.repositorioProducto.listarMascotas();
    }

    @Override
    public List<Higiene> listarProductosHigiene() {
        return this.repositorioProducto.listarHigiene();
    }

    @Override
    public List<Drogueria> listarProductosDrogueria() {
        return this.repositorioProducto.listarDrogueria();
    }
}
