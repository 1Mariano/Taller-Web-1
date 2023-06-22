package ar.edu.unlam.tallerweb1.domain.pedidos;

import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor_Producto;
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
        creacionBolsaNueva(envio);
        creacionCajaNueva(envio);
        List<Contenedor> contenedores = this.repositorioEmpaquetado.obtenerContenedores();
        List<Contenedor> nuevosContenedores = new ArrayList<>();

        productosLoop:
        for (Producto productoAEmpaquetar : productosDelPedido) {
            boolean productoEmpaquetado = false;

            contenedores.addAll(nuevosContenedores);

            Double volumenTotalOcupado = 0.0;
            Double pesoTotalCargado= 0.0;

            for (Contenedor contenedor : contenedores) {
                if (contenedor.getEnvio().getId() == envio.getId()) {
                    if (contenedor.getListaProductos().isEmpty()) {
                        if (contenedor.getTipoContenedor().equals(TipoContenedor.BOLSA)) {
                            if (productoAEmpaquetar.getVolumen() < 3750 && productoAEmpaquetar.getPeso() < 7) {
                                calcularVolumenYPesoDisponible(productoAEmpaquetar, volumenTotalOcupado, pesoTotalCargado, contenedor);

                                Contenedor_Producto con = new Contenedor_Producto();
                                con.setEnvio(envio);
                                con.setProducto(productoAEmpaquetar);
                                con.setContenedor(contenedor);
                                this.repositorioEmpaquetado.guardarEmpaque(con);

                                contenedor.getListaProductos().add(productoAEmpaquetar);
                                productoEmpaquetado = true;

                                this.repositorioEmpaquetado.modificarContenedor(contenedor);
                            } else {
                                Double volumenTotalOcupadoCajaNueva = 0.0;
                                Double pesoTotalCargadoCajaNueva = 0.0;
                                Contenedor cajaNueva = creacionCajaNueva(envio);

                                nuevosContenedores.add(cajaNueva);

                                calcularVolumenYPesoDisponible(productoAEmpaquetar, volumenTotalOcupadoCajaNueva, pesoTotalCargadoCajaNueva, cajaNueva);

                                Contenedor_Producto con1 = new Contenedor_Producto();
                                con1.setEnvio(envio);
                                con1.setProducto(productoAEmpaquetar);
                                con1.setContenedor(cajaNueva);
                                this.repositorioEmpaquetado.guardarEmpaque(con1);

                                cajaNueva.getListaProductos().add(productoAEmpaquetar);
                                productoEmpaquetado = true;

                                this.repositorioEmpaquetado.crearContenedor(cajaNueva);
                            }
                            break;
                        } else {
                            if ((productoAEmpaquetar.getVolumen() > 3750 && productoAEmpaquetar.getVolumen() < 45000) && (productoAEmpaquetar.getPeso() > 7 && productoAEmpaquetar.getPeso() < 30)) {
                                calcularVolumenYPesoDisponible(productoAEmpaquetar, volumenTotalOcupado, pesoTotalCargado, contenedor);

                                Contenedor_Producto con2 = new Contenedor_Producto();
                                con2.setEnvio(envio);
                                con2.setProducto(productoAEmpaquetar);
                                con2.setContenedor(contenedor);
                                this.repositorioEmpaquetado.guardarEmpaque(con2);

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
                                    Double volumenTotalOcupadoBolsaNueva = 0.0;
                                    Double pesoTotalCargadoBolsaNueva = 0.0;

                                    Contenedor bolsaNueva = creacionBolsaNueva(envio);
                                    nuevosContenedores.add(bolsaNueva);

                                    calcularVolumenYPesoDisponible(productoAEmpaquetar, volumenTotalOcupadoBolsaNueva, pesoTotalCargadoBolsaNueva, bolsaNueva);

                                    Contenedor_Producto con3 = new Contenedor_Producto();
                                    con3.setEnvio(envio);
                                    con3.setProducto(productoAEmpaquetar);
                                    con3.setContenedor(bolsaNueva);
                                    this.repositorioEmpaquetado.guardarEmpaque(con3);

                                    bolsaNueva.getListaProductos().add(productoAEmpaquetar);
                                    productoEmpaquetado = true;

                                    this.repositorioEmpaquetado.crearContenedor(bolsaNueva);
                                    break;
                                } else {
                                    if ((productoAEmpaquetar.getVolumen() > 3750 && productoAEmpaquetar.getVolumen() < 45000) && (productoAEmpaquetar.getPeso() > 7 && productoAEmpaquetar.getPeso() < 30)) {
                                        Double volumenTotalOcupadoCajaNueva = 0.0;
                                        Double pesoTotalCargadoCajaNueva = 0.0;

                                        Contenedor cajaNueva = creacionCajaNueva(envio);
                                        nuevosContenedores.add(cajaNueva);

                                        calcularVolumenYPesoDisponible(productoAEmpaquetar, volumenTotalOcupadoCajaNueva, pesoTotalCargadoCajaNueva, cajaNueva);

                                        Contenedor_Producto con4 = new Contenedor_Producto();
                                        con4.setEnvio(envio);
                                        con4.setProducto(productoAEmpaquetar);
                                        con4.setContenedor(cajaNueva);
                                        this.repositorioEmpaquetado.guardarEmpaque(con4);

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
                                        calcularVolumenYPesoDisponible(productoAEmpaquetar, volumenTotalOcupado, pesoTotalCargado, contenedor);

                                        Contenedor_Producto con5 = new Contenedor_Producto();
                                        con5.setEnvio(envio);
                                        con5.setProducto(productoAEmpaquetar);
                                        con5.setContenedor(contenedor);
                                        this.repositorioEmpaquetado.guardarEmpaque(con5);

                                        contenedor.getListaProductos().add(productoAEmpaquetar);
                                        productoEmpaquetado = true;

                                        this.repositorioEmpaquetado.modificarContenedor(contenedor);
                                        break;
                                    } else if ((productoAEmpaquetar.getVolumen() < 3750 && productoAEmpaquetar.getPeso() < 7) &&
                                            !(productoAEmpaquetar.getVolumen() < contenedor.getVolumenDisponible() && productoAEmpaquetar.getPeso() < contenedor.getPesoDisponible())) {
                                        Double volumenTotalOcupadoBolsaNueva = 0.0;
                                        Double pesoTotalCargadoBolsaNueva = 0.0;

                                        Contenedor bolsaNueva = creacionBolsaNueva(envio);


                                        nuevosContenedores.add(bolsaNueva);

                                        calcularVolumenYPesoDisponible(productoAEmpaquetar, volumenTotalOcupadoBolsaNueva, pesoTotalCargadoBolsaNueva, bolsaNueva);

                                        Contenedor_Producto con6 = new Contenedor_Producto();
                                        con6.setEnvio(envio);
                                        con6.setProducto(productoAEmpaquetar);
                                        con6.setContenedor(bolsaNueva);
                                        this.repositorioEmpaquetado.guardarEmpaque(con6);

                                        bolsaNueva.getListaProductos().add(productoAEmpaquetar);
                                        productoEmpaquetado = true;

                                        this.repositorioEmpaquetado.crearContenedor(bolsaNueva);
                                        break;
                                    } else {
                                        Double volumenTotalOcupadoCajaNueva = 0.0;
                                        Double pesoTotalCargadoCajaNueva = 0.0;

                                        Contenedor cajaNueva = creacionCajaNueva(envio);
                                        nuevosContenedores.add(cajaNueva);

                                        calcularVolumenYPesoDisponible(productoAEmpaquetar, volumenTotalOcupadoCajaNueva, pesoTotalCargadoCajaNueva, cajaNueva);

                                        Contenedor_Producto con7 = new Contenedor_Producto();
                                        con7.setEnvio(envio);
                                        con7.setProducto(productoAEmpaquetar);
                                        con7.setContenedor(cajaNueva);
                                        this.repositorioEmpaquetado.guardarEmpaque(con7);

                                        cajaNueva.getListaProductos().add(productoAEmpaquetar);
                                        productoEmpaquetado = true;

                                        this.repositorioEmpaquetado.crearContenedor(cajaNueva);
                                        break;
                                    }
                                } else {
                                    if ((productoAEmpaquetar.getVolumen() > 3750 && productoAEmpaquetar.getVolumen() < 45000) && (productoAEmpaquetar.getPeso() > 7 && productoAEmpaquetar.getPeso() < 30)) {
                                        calcularVolumenYPesoDisponible(productoAEmpaquetar, volumenTotalOcupado, pesoTotalCargado, contenedor);

                                        Contenedor_Producto con8 = new Contenedor_Producto();
                                        con8.setEnvio(envio);
                                        con8.setProducto(productoAEmpaquetar);
                                        con8.setContenedor(contenedor);
                                        this.repositorioEmpaquetado.guardarEmpaque(con8);

                                        contenedor.getListaProductos().add(productoAEmpaquetar);
                                        productoEmpaquetado = true;

                                        this.repositorioEmpaquetado.modificarContenedor(contenedor);
                                    } else {
                                        Double volumenTotalOcupadoCajaNueva = 0.0;
                                        Double pesoTotalCargadoCajaNueva = 0.0;

                                        Contenedor cajaNueva = creacionCajaNueva(envio);
                                        nuevosContenedores.add(cajaNueva);

                                        calcularVolumenYPesoDisponible(productoAEmpaquetar, volumenTotalOcupadoCajaNueva, pesoTotalCargadoCajaNueva, cajaNueva);

                                        Contenedor_Producto con9 = new Contenedor_Producto();
                                        con9.setEnvio(envio);
                                        con9.setProducto(productoAEmpaquetar);
                                        con9.setContenedor(cajaNueva);
                                        this.repositorioEmpaquetado.guardarEmpaque(con9);

                                        cajaNueva.getListaProductos().add(productoAEmpaquetar);
                                        productoEmpaquetado = true;

                                        this.repositorioEmpaquetado.crearContenedor(cajaNueva);
                                    }
                                    break;
                                }
                            }
                        }
                        if (productoEmpaquetado) {
                            continue productosLoop;
                        }
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

    @Override
    public Double obtenerPesoTotalDeLosContenedores() {

        List<Contenedor> contenedores = this.repositorioEmpaquetado.obtenerContenedores();
        Double suma = 0.0;
        for (Contenedor c : contenedores){
            suma += c.getPesoCargado();
        }
        return suma;
    }


    @Override
    public Double obtenerVolumenTotalDeLosContenedores() {
        List<Contenedor> contenedores = this.repositorioEmpaquetado.obtenerContenedores();
        Double volumen = 0.0;
        for (Contenedor c : contenedores){
            volumen += c.getVolumenOcupado();
        }
        return volumen;
    }

    @Override
    public List<Contenedor> devolverContenedoresConProductos() {
        List<Contenedor> contenedores = this.repositorioEmpaquetado.obtenerContenedores();

        return contenedores;
    }


    private void calcularVolumenYPesoDisponible(Producto productoAEmpaquetar, Double volumenTotalOcupado, Double pesoTotalCargado, Contenedor contenedor) {
        Double volumenProducto = productoAEmpaquetar.getVolumen();
        Double pesoProducto = productoAEmpaquetar.getPeso();

        contenedor.setVolumenDisponible(contenedor.getVolumenDisponible() - volumenProducto);
        contenedor.setPesoDisponible(contenedor.getPesoDisponible() - pesoProducto);

        contenedor.setVolumenOcupado(volumenTotalOcupado + volumenProducto);
        contenedor.setPesoCargado(pesoTotalCargado + pesoProducto);
    }

    private Contenedor creacionCajaNueva(Envio envio) {
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
        cajaNueva.setEnvio(envio);
        this.repositorioEmpaquetado.crearContenedor(cajaNueva);
        return cajaNueva;
    }

    private Contenedor creacionBolsaNueva(Envio envio) {
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
        bolsaNueva.setEnvio(envio);
        this.repositorioEmpaquetado.crearContenedor(bolsaNueva);
        return bolsaNueva;
    }

}