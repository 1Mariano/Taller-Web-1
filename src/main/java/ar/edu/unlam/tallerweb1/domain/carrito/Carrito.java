package ar.edu.unlam.tallerweb1.domain.carrito;

import ar.edu.unlam.tallerweb1.domain.producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Producto> productos;

    public Carrito() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Double calcularTotal() {
        Double total = 0.0;
        for (Producto producto : productos) {
            total += producto.getPrecioArs();
        }
        return total;
    }


}
