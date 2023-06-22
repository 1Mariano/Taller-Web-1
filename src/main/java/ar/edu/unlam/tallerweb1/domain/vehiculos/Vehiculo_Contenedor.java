/*package ar.edu.unlam.tallerweb1.domain.vehiculos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vehiculo_Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vehiculo vehiculo;

    @OneToMany
    private List<Contenedor> listaContenedores;

    @OneToOne
    private Envio envio;

    public Vehiculo_Contenedor(Long id, Vehiculo vehiculo, List<Contenedor> listaContenedores, Envio envio) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.listaContenedores = listaContenedores;
        this.envio = envio;
    }

    public Vehiculo_Contenedor() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Contenedor> getListaContenedores() {
        return listaContenedores;
    }

    public void setListaContenedores(List<Contenedor> listaContenedores) {
        this.listaContenedores = listaContenedores;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
}
*/