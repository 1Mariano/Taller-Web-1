package ar.edu.unlam.tallerweb1.domain.contenedor;

import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.producto.Caducable;
import ar.edu.unlam.tallerweb1.domain.producto.IProducto;

import javax.persistence.*;
import java.time.LocalDate;
/*@Entity
public class Bolsa extends Contenedor implements Caducable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ancho;


    private static final int ANYOS_CADUCIDAD = 5;

    private LocalDate fechaFabricacion;


    public Bolsa(Long id, Integer alto, Integer ancho, Integer resistencia, LocalDate fechaFabricacion) {
        super(id, alto, resistencia);
        this.ancho = ancho;
        this.fechaFabricacion = fechaFabricacion;
    }

    public Bolsa() {

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
*/