package ar.edu.unlam.tallerweb1.domain.vehiculos;

import ar.edu.unlam.tallerweb1.domain.contenedor.IContenedor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Vehiculo implements IVehiculo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patente;
    private String modelo;
    private Integer ancho;
    private Integer alto;
    private Integer largo;
    private Integer volumen;

    //ToDo lista de contenedores y pedido
    private List<IContenedor> contenedores;

    public Vehiculo(Long id, String patente, String modelo, Integer ancho, Integer alto, Integer largo) {
        this.id = id;
        this.patente = patente;
        this.modelo = modelo;
        this.ancho = ancho;
        this.volumen = (alto*ancho*largo);
        this.alto = alto;
        this.largo = largo;
        this.contenedores = new ArrayList<IContenedor>();

    }

    public Vehiculo() {

    }


    @Override
    public Integer getVolumenVehiculo() {
        return this.volumen;
    }

    @Override
    public Integer volumenDisponibleVehiculo() {
        return getVolumenVehiculo() - volumenOcupadoVehiculo();
    }

    private Integer volumenOcupadoVehiculo() {
        int res = 0;
        for (IContenedor c : contenedores) {
            res += c.getVolumenContenedor();
        }
        return res;
    }



    @Override
    public Boolean guardar(IContenedor contenedor) {
        Boolean resistenciaOk = soporta(contenedor);
        Boolean volumenOk = contenedor.tengoEspacio(this);

        Boolean acepta = resistenciaOk && volumenOk;

        if (acepta){
            contenedores.add(contenedor);
            contenedor.guardar(this);
        }
        return acepta;

    }




    @Override
    public Boolean soporta(IContenedor contenedor) {
        return getPesoSoportadoVehiculo() > contenedor.pesoTotalContenedor();
    }

    @Override
    public List<IContenedor> getContenedores() {
        return contenedores;
    }
}
