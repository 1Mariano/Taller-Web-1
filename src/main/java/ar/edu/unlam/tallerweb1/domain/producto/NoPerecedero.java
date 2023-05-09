package ar.edu.unlam.tallerweb1.domain.producto;

import java.time.LocalDate;

public class NoPerecedero extends Alimentacion implements Caducable{

    private LocalDate fechaFabricacion;
    private static final int ANYOS_CADUCIDAD = 5;

    public NoPerecedero(Long id, Integer peso, Integer volumen, LocalDate fechaFabricacion) {
        super(id, peso, volumen);
        this.fechaFabricacion = fechaFabricacion;
    }

    @Override
    public boolean estaCaducado() {
        return LocalDate.now().isBefore(fechaFabricacion.plusYears(ANYOS_CADUCIDAD));
    }
}
