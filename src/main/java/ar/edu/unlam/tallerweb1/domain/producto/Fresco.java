package ar.edu.unlam.tallerweb1.domain.producto;

import java.time.LocalDate;

public class Fresco extends Alimentacion implements Caducable{

    private LocalDate caducidad;

    public Fresco(Long id, Integer peso, Integer volumen, LocalDate caducidad) {
        super(id, peso, volumen);
        this.caducidad = caducidad;
    }


    @Override
    public boolean estaCaducado() {
        return caducidad.isBefore(LocalDate.now());
    }
}
