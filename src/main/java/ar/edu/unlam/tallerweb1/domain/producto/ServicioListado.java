package ar.edu.unlam.tallerweb1.domain.producto;

import java.util.List;

public interface ServicioListado {

    List<Mascota> listarProductosMascotas();

    List<Higiene> listarProductosHigiene();

    List<Drogueria> listarProductosDrogueria();
}
