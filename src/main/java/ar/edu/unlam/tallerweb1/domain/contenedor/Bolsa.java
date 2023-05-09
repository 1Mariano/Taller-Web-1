package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.Caducable;
import ar.edu.unlam.tallerweb1.domain.producto.IProducto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;

import java.time.LocalDate;
import java.util.List;

public class Bolsa extends Contenedor implements Caducable {

    private Integer ancho;
    private static final int ANYOS_CADUCIDAD = 5;
    private LocalDate fechaFabricacion;


    public Bolsa(Long id, Integer alto, Integer ancho, Integer resistencia, LocalDate fechaFabricacion) {
        super(id, alto, resistencia);
        this.ancho = ancho;
        this.fechaFabricacion = fechaFabricacion;
    }

    @Override
    public TipoContenedor getTipo() {
        return TipoContenedor.BOLSA;
    }



    @Override
    public Integer getSuperficie() {
        Integer radio = getDiametro()/2;
        return (int) (Math.PI * radio * radio);
    }


    private Integer getDiametro() {
        return (int) ((2*ancho) / Math.PI);
    }

    @Override
    public boolean estaCaducado() {
        return LocalDate.now().isBefore(fechaFabricacion.plusYears(ANYOS_CADUCIDAD));
    }

}
