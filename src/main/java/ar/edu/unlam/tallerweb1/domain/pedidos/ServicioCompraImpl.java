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

import java.nio.DoubleBuffer;
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
    public void empaquetarProductos(List<Producto> productos, Envio envio) {

        creacionContenedores();
        List<Contenedor> contenedores = this.repositorioEmpaquetado.obtenerContenedores();
        List<Contenedor> contenedoresAjustables = contenedores;
        // Todo arreglar esta logica
        for (Producto p : productos) {
            for (Contenedor c : contenedoresAjustables) {

                if (p.getVolumen() < c.getVolumen() && c.getTipoContenedor().equals(TipoContenedor.BOLSA)) {
                    //c.setVolumen(c.getVolumen() - p.getVolumen());



                    Contenedor_Producto bolsa = new Contenedor_Producto();
                    bolsa.setProducto(p);
                    bolsa.setContenedor(c);
                    bolsa.setEnvio(envio);

                    this.repositorioEmpaquetado.guardarEmpaque(bolsa);
                    break;
                } else if (c.getVolumen() > 3750 && p.getVolumen() > 3750) {
                    //c.setVolumen(c.getVolumen() - p.getVolumen());


                    Contenedor_Producto caja = new Contenedor_Producto();
                    caja.setProducto(p);
                    caja.setContenedor(c);
                    caja.setEnvio(envio);
                    this.repositorioEmpaquetado.guardarEmpaque(caja);
                    break;
                }
                }

            }

            //Contenedor(Long id, Double alto, Double largo, Double ancho, Double pesoSoportado, TipoContenedor tipoContenedor)
            // 22 alto, 40 largo, 30 ancho


        }





    private void creacionContenedores() {
        Contenedor caja = new Contenedor();
        caja.setAlto(50.0);
        caja.setAncho(20.0);
        caja.setLargo(45.0);
        caja.setPesoSoportado(30.0);
        caja.setVolumen(50.0 * 20.0 * 45.0);
        caja.setTipoContenedor(TipoContenedor.CAJA);

        Contenedor bolsa = new Contenedor();
        bolsa.setAlto(25.0);
        bolsa.setAncho(10.0);
        bolsa.setLargo(15.0);
        bolsa.setPesoSoportado(7.0);
        bolsa.setVolumen(25.0 * 10.0 * 15.0);
        bolsa.setTipoContenedor(TipoContenedor.BOLSA);

        this.repositorioEmpaquetado.crearContenedor(caja);
        this.repositorioEmpaquetado.crearContenedor(bolsa);
    }


}
