package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.enums.Categoria;

public class Mascota extends Producto{

    public Mascota(Long id, Integer peso, Integer volumen) {
        super(id, peso, volumen);
    }

    @Override
    public Categoria getCategoria() {
        return Categoria.MASCOTA;
    }

    @Override
    public Boolean esCompatible(IProducto p) {
        return null;
    }
}
