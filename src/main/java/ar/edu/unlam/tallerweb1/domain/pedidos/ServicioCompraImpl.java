package ar.edu.unlam.tallerweb1.domain.pedidos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor_Producto;
import ar.edu.unlam.tallerweb1.domain.contenedor.RepositorioEmpaquetado;
import ar.edu.unlam.tallerweb1.domain.contenedor.ServicioEmpaquetado;
import ar.edu.unlam.tallerweb1.domain.enums.CategoriaProducto;
import ar.edu.unlam.tallerweb1.domain.enums.EstadoPago;
import ar.edu.unlam.tallerweb1.domain.enums.EstadoPedido;
import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.envio.RepositorioEnvio;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.CampoInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("ServicioCompra")
@Transactional
public class ServicioCompraImpl implements ServicioCompra {

    private RepositorioEnvio repositorioEnvio;
    private RepositorioEmpaquetado repositorioEmpaquetado;


    @Autowired
    public ServicioCompraImpl(RepositorioEnvio repositorioEnvio, RepositorioEmpaquetado repositorioEmpaquetado) {

        this.repositorioEnvio = repositorioEnvio;
        this.repositorioEmpaquetado = repositorioEmpaquetado;
    }

    @Override
    public void cambiarEstadoDePago(EstadoPago estadoPago) {
        estadoPago = EstadoPago.PAGADO;
    }

    @Override
    public void cambiarEstadoDePedido(EstadoPedido estadoPedido) {
        estadoPedido = EstadoPedido.EN_PREPARACION;
    }

    @Override
    public void guardarDatosEnvio(Envio envioNuevo) throws CampoInvalidoException {
        if (envioNuevo.getCalle().length() < 2) {
            throw new CampoInvalidoException();
        }
        if (envioNuevo.getLocalidad().length() < 2) {
            throw new CampoInvalidoException();
        }
        if (envioNuevo.getCodigoPostal().length() < 2) {
            throw new CampoInvalidoException();
        }
        if (envioNuevo.getNumero() == null) {
            throw new CampoInvalidoException();
        }
        if (envioNuevo.getPisoODepartamento() == null) {
            throw new CampoInvalidoException();
        }
        this.repositorioEnvio.guardarEnvio(envioNuevo);
    }

    @Override
    public void empaquetarProductos(List<Producto> productos) {

        creacionContenedores();
        List<Contenedor> contenedores = this.repositorioEmpaquetado.obtenerContenedores();
        List<Contenedor> contenedores1 = new ArrayList<Contenedor>();

        if (contenedores.isEmpty()) {
            //crearContenedor();

        }
        for (Producto p : productos) {
            crearContenedor(p);
            //this.repositorioEmpaquetado.agregarAlContenedor(empaques);
        }

        /*for ( Producto p : productos) {
            if (p.getVolumen() < 3750 && (p.getPeso()/1000) < 7 ){
                //Contenedor bolsa = new Contenedor();
                List<Contenedor> bolsa = new ArrayList<Contenedor>();
                bolsa.add(p.add);
            }

            //this.repositorioEmpaquetado.empaquetarProducto(p.getId(), );
        }*/


        //Contenedor(Long id, Double alto, Double largo, Double ancho, Double pesoSoportado, TipoContenedor tipoContenedor)
        // 22 alto, 40 largo, 30 ancho

    }

    private static void crearContenedor(Producto p) {
        if (p.getVolumen() < 3750 && (p.getPeso() / 1000) < 7) {
            Contenedor contenedor = new Contenedor();
            contenedor.setTipoContenedor(TipoContenedor.BOLSA);
            contenedor.setAlto(25.0);
            contenedor.setAncho(10.0);
            contenedor.setLargo(15.0);
            contenedor.setPesoSoportado(7.0);
        } else {
            Contenedor contenedor = new Contenedor();
            contenedor.setTipoContenedor(TipoContenedor.CAJA);
            contenedor.setAlto(50.0);
            contenedor.setAncho(20.0);
            contenedor.setLargo(45.0);
            contenedor.setPesoSoportado(30.0);
        }
    }


    private void creacionContenedores() {
        Contenedor caja = new Contenedor();
        caja.setAlto(50.0);
        caja.setAncho(20.0);
        caja.setLargo(45.0);
        caja.setPesoSoportado(30.0);
        caja.setTipoContenedor(TipoContenedor.CAJA);

        Contenedor bolsa = new Contenedor();
        bolsa.setAlto(25.0);
        bolsa.setAncho(10.0);
        bolsa.setLargo(15.0);
        bolsa.setPesoSoportado(7.0);
        bolsa.setTipoContenedor(TipoContenedor.BOLSA);

        this.repositorioEmpaquetado.crearContenedor(caja);
        this.repositorioEmpaquetado.crearContenedor(bolsa);
    }


}
