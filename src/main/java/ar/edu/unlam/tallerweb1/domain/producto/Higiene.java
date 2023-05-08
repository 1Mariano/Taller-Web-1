package ar.edu.unlam.tallerweb1.domain.producto;

import ar.edu.unlam.tallerweb1.domain.enums.Categoria;

public class Higiene extends Producto{

    public Higiene(Long id, Integer peso, Integer volumen) {
        super(id, peso, volumen);
    }

    public Higiene() {
    }

    @Override
    public Categoria getCategoria() {
        return Categoria.HIGIENE;
    }

    @Override
    public Boolean esCompatible(IProducto p) {
        return null;
    }
}
