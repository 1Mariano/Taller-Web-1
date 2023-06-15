package ar.edu.unlam.tallerweb1.domain.pedidos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.RepositorioEmpaquetado;
import ar.edu.unlam.tallerweb1.domain.enums.CategoriaProducto;
import ar.edu.unlam.tallerweb1.domain.enums.EstadoPago;
import ar.edu.unlam.tallerweb1.domain.enums.EstadoPedido;
import ar.edu.unlam.tallerweb1.domain.enums.TipoContenedor;
import ar.edu.unlam.tallerweb1.domain.envio.Envio;
import ar.edu.unlam.tallerweb1.domain.envio.RepositorioEnvio;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
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
    public void empaquetarProductos(List<Producto> productosDelPedido, Envio envio) {
        creacionBolsa();
        crecacionCaja();
        List<Contenedor> contenedores = this.repositorioEmpaquetado.obtenerContenedores();
        List<Contenedor> nuevosContenedores = new ArrayList<>();

        productosLoop:
        for (Producto productoAEmpaquetar : productosDelPedido) {
            boolean productoEmpaquetado = false;

            contenedores.addAll(nuevosContenedores);

            Double volumenOcupado = 0.0;
            Double pesoCargado = 0.0;

            Double volumenTotalOcupado = 0.0;
            Double pesoTotalCargado= 0.0;

            for (Contenedor contenedor : contenedores) {

                if (contenedor.getListaProductos().isEmpty()) {
                    if (contenedor.getTipoContenedor().equals(TipoContenedor.BOLSA)) {
                        if (productoAEmpaquetar.getVolumen() < 3750 && productoAEmpaquetar.getPeso() < 7) {

                            Double volumenProducto = productoAEmpaquetar.getVolumen();
                            Double pesoProducto = productoAEmpaquetar.getPeso();
                            volumenOcupado += volumenProducto;
                            pesoCargado += pesoProducto;
                            contenedor.setVolumenOcupado(volumenOcupado);
                            contenedor.setPesoCargado(pesoCargado);
                            contenedor.setVolumenDisponible(contenedor.getVolumenDisponible() - contenedor.getVolumenOcupado());
                            contenedor.setPesoDisponible(contenedor.getPesoDisponible() - contenedor.getPesoCargado());

                            contenedor.getListaProductos().add(productoAEmpaquetar);
                            productoEmpaquetado = true;

                            this.repositorioEmpaquetado.modificarContenedor(contenedor);
                            break;
                        } else {
                            Contenedor cajaNueva = new Contenedor();
                            List<Producto> nuevaListaDeProductos = new ArrayList<>();
                            cajaNueva.setListaProductos(nuevaListaDeProductos);
                            cajaNueva.setAlto(50.0);
                            Double alto = cajaNueva.getAlto();
                            cajaNueva.setAncho(20.0);
                            Double ancho = cajaNueva.getAncho();
                            cajaNueva.setLargo(45.0);
                            Double largo = cajaNueva.getLargo();
                            cajaNueva.setPesoSoportado(30.0);
                            cajaNueva.setVolumenMaximo(alto, ancho, largo);
                            cajaNueva.setVolumenDisponible(cajaNueva.getVolumenMaximo());
                            cajaNueva.setPesoDisponible(cajaNueva.getPesoSoportado());
                            cajaNueva.setTipoContenedor(TipoContenedor.CAJA);
                            nuevosContenedores.add(cajaNueva);

                            Double volumenProducto = productoAEmpaquetar.getVolumen();
                            Double pesoProducto = productoAEmpaquetar.getPeso();

                            cajaNueva.setVolumenDisponible(cajaNueva.getVolumenDisponible() - volumenProducto);
                            cajaNueva.setPesoDisponible(cajaNueva.getPesoDisponible() - pesoProducto);

                            cajaNueva.setVolumenOcupado(volumenTotalOcupado + volumenProducto);
                            cajaNueva.setPesoCargado(pesoTotalCargado + pesoProducto);

                            cajaNueva.getListaProductos().add(productoAEmpaquetar);
                            productoEmpaquetado = true;

                            this.repositorioEmpaquetado.crearContenedor(cajaNueva);
                            break;
                        }
                    } else {
                        if ((productoAEmpaquetar.getVolumen() > 3750 && productoAEmpaquetar.getVolumen() < 45000) && (productoAEmpaquetar.getPeso() > 7 && productoAEmpaquetar.getPeso() < 30)) {

                            Double volumenProducto = productoAEmpaquetar.getVolumen();
                            Double pesoProducto = productoAEmpaquetar.getPeso();
                            volumenOcupado += volumenProducto;
                            pesoCargado += pesoProducto;
                            contenedor.setVolumenOcupado(volumenOcupado);
                            contenedor.setPesoCargado(pesoCargado);
                            contenedor.setVolumenDisponible(contenedor.getVolumenDisponible() - contenedor.getVolumenOcupado());
                            contenedor.setPesoDisponible(contenedor.getPesoDisponible() - contenedor.getPesoCargado());

                            contenedor.getListaProductos().add(productoAEmpaquetar);
                            productoEmpaquetado = true;

                            this.repositorioEmpaquetado.modificarContenedor(contenedor);
                            break;
                        }
                    }

                } else {
                    for (Producto productosEmpaquetados : contenedor.getListaProductos()) {
                        volumenTotalOcupado += productosEmpaquetados.getVolumen();
                        pesoTotalCargado += productosEmpaquetados.getPeso();
                    }

                    for (Producto productoYaEmpaquetado : contenedor.getListaProductos()) {


                        boolean tieneProductoHigiene = productoYaEmpaquetado.getCategoria().equals(CategoriaProducto.HIGIENE);
                        if (tieneProductoHigiene && (productoAEmpaquetar.getCategoria().equals(CategoriaProducto.ALIMENTOS_CONGELADOS) ||
                                productoAEmpaquetar.getCategoria().equals(CategoriaProducto.ALIMENTOS_FRESCOS) ||
                                productoAEmpaquetar.getCategoria().equals(CategoriaProducto.ALIMENTOS_NO_PERECEDEROS))) {

                            if ((productoAEmpaquetar.getVolumen() < 3750 && productoAEmpaquetar.getPeso() < 7)) {
                                Contenedor bolsaNueva = new Contenedor();
                                List<Producto> nuevaListaDeProductos = new ArrayList<>();
                                bolsaNueva.setListaProductos(nuevaListaDeProductos);
                                bolsaNueva.setAlto(25.0);
                                Double alto = bolsaNueva.getAlto();
                                bolsaNueva.setAncho(10.0);
                                Double ancho = bolsaNueva.getAncho();
                                bolsaNueva.setLargo(15.0);
                                Double largo = bolsaNueva.getLargo();
                                bolsaNueva.setPesoSoportado(7.0);
                                bolsaNueva.setVolumenMaximo(alto, ancho, largo);
                                bolsaNueva.setVolumenDisponible(bolsaNueva.getVolumenMaximo());
                                bolsaNueva.setPesoDisponible(bolsaNueva.getPesoSoportado());
                                bolsaNueva.setTipoContenedor(TipoContenedor.BOLSA);
                                nuevosContenedores.add(bolsaNueva);

                                Double volumenProducto = productoAEmpaquetar.getVolumen();
                                Double pesoProducto = productoAEmpaquetar.getPeso();

                                bolsaNueva.setVolumenDisponible(bolsaNueva.getVolumenDisponible() - volumenProducto);
                                bolsaNueva.setPesoDisponible(bolsaNueva.getPesoDisponible() - pesoProducto);

                                bolsaNueva.setVolumenOcupado(volumenTotalOcupado + volumenProducto);
                                bolsaNueva.setPesoCargado(pesoTotalCargado + pesoProducto);

                                bolsaNueva.getListaProductos().add(productoAEmpaquetar);
                                productoEmpaquetado = true;

                                this.repositorioEmpaquetado.crearContenedor(bolsaNueva);
                                break;
                            } else {
                                if ((productoAEmpaquetar.getVolumen() > 3750 && productoAEmpaquetar.getVolumen() < 45000) && (productoAEmpaquetar.getPeso() > 7 && productoAEmpaquetar.getPeso() < 30)) {
                                    Contenedor cajaNueva = new Contenedor();
                                    List<Producto> nuevaListaDeProductos = new ArrayList<>();
                                    cajaNueva.setListaProductos(nuevaListaDeProductos);
                                    cajaNueva.setAlto(50.0);
                                    Double alto = cajaNueva.getAlto();
                                    cajaNueva.setAncho(20.0);
                                    Double ancho = cajaNueva.getAncho();
                                    cajaNueva.setLargo(45.0);
                                    Double largo = cajaNueva.getLargo();
                                    cajaNueva.setPesoSoportado(30.0);
                                    cajaNueva.setVolumenMaximo(alto, ancho, largo);
                                    cajaNueva.setVolumenDisponible(cajaNueva.getVolumenMaximo());
                                    cajaNueva.setPesoDisponible(cajaNueva.getPesoSoportado());
                                    cajaNueva.setTipoContenedor(TipoContenedor.CAJA);
                                    nuevosContenedores.add(cajaNueva);

                                    Double volumenProducto = productoAEmpaquetar.getVolumen();
                                    Double pesoProducto = productoAEmpaquetar.getPeso();

                                    cajaNueva.setVolumenDisponible(cajaNueva.getVolumenDisponible() - volumenProducto);
                                    cajaNueva.setPesoDisponible(cajaNueva.getPesoDisponible() - pesoProducto);

                                    cajaNueva.setVolumenOcupado(volumenTotalOcupado + volumenProducto);
                                    cajaNueva.setPesoCargado(pesoTotalCargado + pesoProducto);

                                    cajaNueva.getListaProductos().add(productoAEmpaquetar);
                                    productoEmpaquetado = true;

                                    this.repositorioEmpaquetado.crearContenedor(cajaNueva);
                                    break;
                                }
                            }

                        } else {
                            if (contenedor.getTipoContenedor().equals(TipoContenedor.BOLSA)) {
                                if ((productoAEmpaquetar.getVolumen() < 3750 && productoAEmpaquetar.getPeso() < 7) &&
                                        (productoAEmpaquetar.getVolumen() < contenedor.getVolumenDisponible() && productoAEmpaquetar.getPeso() < contenedor.getPesoDisponible())) {

                                    Double volumenProducto = productoAEmpaquetar.getVolumen();
                                    Double pesoProducto = productoAEmpaquetar.getPeso();

                                    contenedor.setVolumenDisponible(contenedor.getVolumenDisponible() - volumenProducto);
                                    contenedor.setPesoDisponible(contenedor.getPesoDisponible() - pesoProducto);

                                    //PRUEBA
                                    contenedor.setVolumenOcupado(volumenTotalOcupado + volumenProducto);
                                    contenedor.setPesoCargado(pesoTotalCargado + pesoProducto);

                                    contenedor.getListaProductos().add(productoAEmpaquetar);
                                    productoEmpaquetado = true;

                                    this.repositorioEmpaquetado.modificarContenedor(contenedor);
                                    break;
                                } else if ((productoAEmpaquetar.getVolumen() < 3750 && productoAEmpaquetar.getPeso() < 7) &&
                                        !(productoAEmpaquetar.getVolumen() < contenedor.getVolumenDisponible() && productoAEmpaquetar.getPeso() < contenedor.getPesoDisponible())) {
                                    Contenedor bolsaNueva = new Contenedor();
                                    List<Producto> nuevaListaDeProductos = new ArrayList<>();
                                    bolsaNueva.setListaProductos(nuevaListaDeProductos);
                                    bolsaNueva.setAlto(25.0);
                                    Double alto = bolsaNueva.getAlto();
                                    bolsaNueva.setAncho(10.0);
                                    Double ancho = bolsaNueva.getAncho();
                                    bolsaNueva.setLargo(15.0);
                                    Double largo = bolsaNueva.getLargo();
                                    bolsaNueva.setPesoSoportado(7.0);
                                    bolsaNueva.setVolumenMaximo(alto, ancho, largo);
                                    bolsaNueva.setVolumenDisponible(bolsaNueva.getVolumenMaximo());
                                    bolsaNueva.setPesoDisponible(bolsaNueva.getPesoSoportado());
                                    bolsaNueva.setTipoContenedor(TipoContenedor.BOLSA);
                                    nuevosContenedores.add(bolsaNueva);

                                    Double volumenProducto = productoAEmpaquetar.getVolumen();
                                    Double pesoProducto = productoAEmpaquetar.getPeso();

                                    bolsaNueva.setVolumenOcupado(volumenTotalOcupado + volumenProducto);
                                    bolsaNueva.setPesoCargado(pesoTotalCargado + pesoProducto);

                                    bolsaNueva.setVolumenOcupado(bolsaNueva.getVolumenMaximo() - bolsaNueva.getVolumenDisponible() - productoYaEmpaquetado.getVolumen() + volumenProducto);
                                    bolsaNueva.setPesoCargado(bolsaNueva.getPesoSoportado() - bolsaNueva.getPesoDisponible() - productoYaEmpaquetado.getPeso() + pesoProducto);

                                    bolsaNueva.getListaProductos().add(productoAEmpaquetar);
                                    productoEmpaquetado = true;

                                    this.repositorioEmpaquetado.crearContenedor(bolsaNueva);
                                    break;
                                } else {
                                    Contenedor cajaNueva = new Contenedor();
                                    List<Producto> nuevaListaDeProductos = new ArrayList<>();
                                    cajaNueva.setListaProductos(nuevaListaDeProductos);
                                    cajaNueva.setAlto(50.0);
                                    Double alto = cajaNueva.getAlto();
                                    cajaNueva.setAncho(20.0);
                                    Double ancho = cajaNueva.getAncho();
                                    cajaNueva.setLargo(45.0);
                                    Double largo = cajaNueva.getLargo();
                                    cajaNueva.setPesoSoportado(30.0);
                                    cajaNueva.setVolumenMaximo(alto, ancho, largo);
                                    cajaNueva.setVolumenDisponible(cajaNueva.getVolumenMaximo());
                                    cajaNueva.setPesoDisponible(cajaNueva.getPesoSoportado());
                                    cajaNueva.setTipoContenedor(TipoContenedor.CAJA);
                                    nuevosContenedores.add(cajaNueva);

                                    Double volumenProducto = productoAEmpaquetar.getVolumen();
                                    Double pesoProducto = productoAEmpaquetar.getPeso();

                                    cajaNueva.setVolumenDisponible(cajaNueva.getVolumenDisponible() - volumenProducto);
                                    cajaNueva.setPesoDisponible(cajaNueva.getPesoDisponible() - pesoProducto);

                                    cajaNueva.setVolumenOcupado(volumenTotalOcupado + volumenProducto);
                                    cajaNueva.setPesoCargado(pesoTotalCargado + pesoProducto);

                                    cajaNueva.getListaProductos().add(productoAEmpaquetar);
                                    productoEmpaquetado = true;

                                    this.repositorioEmpaquetado.crearContenedor(cajaNueva);
                                    break;
                                }
                            } else {
                                if ((productoAEmpaquetar.getVolumen() > 3750 && productoAEmpaquetar.getVolumen() < 45000) && (productoAEmpaquetar.getPeso() > 7 && productoAEmpaquetar.getPeso() < 30)) {

                                    Double volumenProducto = productoAEmpaquetar.getVolumen();
                                    Double pesoProducto = productoAEmpaquetar.getPeso();

                                    contenedor.setVolumenDisponible(contenedor.getVolumenDisponible() - volumenProducto);
                                    contenedor.setPesoDisponible(contenedor.getPesoDisponible() - pesoProducto);

                                    contenedor.setVolumenOcupado(volumenTotalOcupado + volumenProducto);
                                    contenedor.setPesoCargado(pesoTotalCargado + pesoProducto);

                                    contenedor.getListaProductos().add(productoAEmpaquetar);
                                    productoEmpaquetado = true;

                                    this.repositorioEmpaquetado.modificarContenedor(contenedor);
                                    break;
                                } else {
                                    Contenedor cajaNueva = new Contenedor();
                                    List<Producto> nuevaListaDeProductos = new ArrayList<>();
                                    cajaNueva.setListaProductos(nuevaListaDeProductos);
                                    cajaNueva.setAlto(50.0);
                                    Double alto = cajaNueva.getAlto();
                                    cajaNueva.setAncho(20.0);
                                    Double ancho = cajaNueva.getAncho();
                                    cajaNueva.setLargo(45.0);
                                    Double largo = cajaNueva.getLargo();
                                    cajaNueva.setPesoSoportado(30.0);
                                    cajaNueva.setVolumenMaximo(alto, ancho, largo);
                                    cajaNueva.setVolumenDisponible(cajaNueva.getVolumenMaximo());
                                    cajaNueva.setPesoDisponible(cajaNueva.getPesoSoportado());
                                    cajaNueva.setTipoContenedor(TipoContenedor.CAJA);
                                    nuevosContenedores.add(cajaNueva);

                                    Double volumenProducto = productoAEmpaquetar.getVolumen();
                                    Double pesoProducto = productoAEmpaquetar.getPeso();

                                    cajaNueva.setVolumenDisponible(cajaNueva.getVolumenDisponible() - volumenProducto);
                                    cajaNueva.setPesoDisponible(cajaNueva.getPesoDisponible() - pesoProducto);

                                    cajaNueva.setVolumenOcupado(volumenTotalOcupado + volumenProducto);
                                    cajaNueva.setPesoCargado(pesoTotalCargado + pesoProducto);

                                    cajaNueva.getListaProductos().add(productoAEmpaquetar);
                                    productoEmpaquetado = true;

                                    this.repositorioEmpaquetado.crearContenedor(cajaNueva);
                                    break;
                                }
                            }
                        }
                    }
                    if (productoEmpaquetado) {
                        continue productosLoop;  // Saltar al siguiente producto en productosDelPedido
                    }
                }
            }
        }
        for (Contenedor contenedorAEliminar: contenedores) {
            if (contenedorAEliminar.getListaProductos().isEmpty()) {
                this.repositorioEmpaquetado.eliminarContenedorVacio(contenedorAEliminar);
            }
        }
    }

    private void crecacionCaja() {
        Contenedor cajaNueva = new Contenedor();
        List<Producto> nuevaListaDeProductos = new ArrayList<>();
        cajaNueva.setListaProductos(nuevaListaDeProductos);
        cajaNueva.setAlto(50.0);
        Double alto = cajaNueva.getAlto();
        cajaNueva.setAncho(20.0);
        Double ancho = cajaNueva.getAncho();
        cajaNueva.setLargo(45.0);
        Double largo = cajaNueva.getLargo();
        cajaNueva.setPesoSoportado(30.0);
        cajaNueva.setVolumenMaximo(alto, ancho, largo);
        cajaNueva.setVolumenDisponible(cajaNueva.getVolumenMaximo());
        cajaNueva.setPesoDisponible(cajaNueva.getPesoSoportado());
        cajaNueva.setTipoContenedor(TipoContenedor.CAJA);
        this.repositorioEmpaquetado.crearContenedor(cajaNueva);
    }

    private void creacionBolsa() {
        Contenedor bolsaNueva = new Contenedor();
        List<Producto> nuevaListaDeProductos = new ArrayList<>();
        bolsaNueva.setListaProductos(nuevaListaDeProductos);
        bolsaNueva.setAlto(25.0);
        Double alto = bolsaNueva.getAlto();
        bolsaNueva.setAncho(10.0);
        Double ancho = bolsaNueva.getAncho();
        bolsaNueva.setLargo(15.0);
        Double largo = bolsaNueva.getLargo();
        bolsaNueva.setPesoSoportado(7.0);
        bolsaNueva.setVolumenMaximo(alto, ancho, largo);
        bolsaNueva.setVolumenDisponible(bolsaNueva.getVolumenMaximo());
        bolsaNueva.setPesoDisponible(bolsaNueva.getPesoSoportado());
        bolsaNueva.setTipoContenedor(TipoContenedor.BOLSA);
        this.repositorioEmpaquetado.crearContenedor(bolsaNueva);
    }

}