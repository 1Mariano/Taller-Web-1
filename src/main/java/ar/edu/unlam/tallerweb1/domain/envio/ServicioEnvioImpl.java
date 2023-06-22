package ar.edu.unlam.tallerweb1.domain.envio;


import ar.edu.unlam.tallerweb1.domain.contenedor.Contenedor;
import ar.edu.unlam.tallerweb1.domain.enums.TipoVehiculo;
import ar.edu.unlam.tallerweb1.domain.vehiculos.RepositorioVehiculo;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ServicioEnvio")
@Transactional
public class ServicioEnvioImpl implements ServicioEnvio {

    private final RepositorioEnvio repositorioEnvio;
    private final RepositorioVehiculo repositorioVehiculo;
    private Vehiculo vehiculoAsignado;

    @Autowired
    public ServicioEnvioImpl(RepositorioEnvio repositorioEnvio, RepositorioVehiculo repositorioVehiculo) {

        this.repositorioEnvio = repositorioEnvio;
        this.repositorioVehiculo = repositorioVehiculo;
    }

    @Override
    public Double distanciaEnvio() {
        return Math.random() * 150 + 1;
    }

    @Override
    public Double calcularCostoEnvio(Vehiculo vehiculoAsignado) {
        double costo = 0.0;
        switch (vehiculoAsignado.getTipoVehiculo()) {
            case MOTO:
                costo = distanciaEnvio() * 1000.0;
                break;
            case AUTO:
                costo = distanciaEnvio() * 1500.0;
                break;
            case CAMIONETA:
                costo = distanciaEnvio() * 2000.0;
                break;
        }
        return costo;
    }

    @Override
    public void agregarAlVehiculo(List<Contenedor> listaContenedores, List<Vehiculo> listaVehiculos, Envio envio) {
        listaVehiculos = this.repositorioVehiculo.obtenerVehiculos();

        vehiculosLoop:
        for (Vehiculo vehiculo : listaVehiculos) {
            boolean contenedoresGuardados = false;

            Double volumenTotalOcupado = 0.0;
            Double pesoTotalCargado = 0.0;

            if (vehiculo.getListaContenedores().isEmpty()) {
                if (vehiculo.getTipoVehiculo().equals(TipoVehiculo.MOTO)) {
                    if (obtenerVolumenTotalOcupado(listaContenedores) < 96000.0 && obtenerPesoTotalCargado(listaContenedores) < 35.0) {

                        calcularVolumenOcupadoYPesoCargado(listaContenedores, vehiculo, volumenTotalOcupado, pesoTotalCargado);

                        vehiculo.getListaContenedores().addAll(listaContenedores);
                        contenedoresGuardados = true;

                        vehiculoAsignado = vehiculo;
                        envio.setVehiculo(vehiculoAsignado);
                        asignarVehiculo(envio, vehiculo);

                    }
                } else if (vehiculo.getTipoVehiculo().equals(TipoVehiculo.AUTO)) {
                    if (obtenerVolumenTotalOcupado(listaContenedores) > 96000.0 && obtenerPesoTotalCargado(listaContenedores) > 35.0 &&
                            obtenerVolumenTotalOcupado(listaContenedores) < 360000.0 && obtenerPesoTotalCargado(listaContenedores) < 75.0) {

                        calcularVolumenOcupadoYPesoCargado(listaContenedores, vehiculo, volumenTotalOcupado, pesoTotalCargado);

                        vehiculo.getListaContenedores().addAll(listaContenedores);
                        contenedoresGuardados = true;

                        vehiculoAsignado = vehiculo;
                        envio.setVehiculo(vehiculoAsignado);
                        asignarVehiculo(envio, vehiculo);

                    }
                } else {
                    if (obtenerVolumenTotalOcupado(listaContenedores) > 360000.0 && obtenerPesoTotalCargado(listaContenedores) > 75.0 &&
                            obtenerVolumenTotalOcupado(listaContenedores) < 5100000.0 && obtenerPesoTotalCargado(listaContenedores) < 700.0) {

                        calcularVolumenOcupadoYPesoCargado(listaContenedores, vehiculo, volumenTotalOcupado, pesoTotalCargado);

                        vehiculo.getListaContenedores().addAll(listaContenedores);
                        contenedoresGuardados = true;

                        vehiculoAsignado = vehiculo;
                        envio.setVehiculo(vehiculoAsignado);
                        asignarVehiculo(envio, vehiculo);

                    }
                }
            } else {
                for (Contenedor contenedoresYaGuardados : vehiculo.getListaContenedores()) {
                    volumenTotalOcupado += contenedoresYaGuardados.getVolumenOcupado();
                    pesoTotalCargado += contenedoresYaGuardados.getPesoCargado();

                }
                if (vehiculo.getTipoVehiculo().equals(TipoVehiculo.MOTO)) {
                    if (obtenerVolumenTotalOcupado(listaContenedores) < 96000.0 && obtenerPesoTotalCargado(listaContenedores) < 35.0 &&
                            obtenerVolumenTotalOcupado(listaContenedores) < vehiculo.getVolumenDisponible() && obtenerPesoTotalCargado(listaContenedores) < vehiculo.getPesoDisponible()) {

                        calcularVolumenOcupadoYPesoCargado(listaContenedores, vehiculo, volumenTotalOcupado, pesoTotalCargado);

                        vehiculo.getListaContenedores().addAll(listaContenedores);
                        contenedoresGuardados = true;

                        vehiculoAsignado = vehiculo;
                        envio.setVehiculo(vehiculoAsignado);
                        asignarVehiculo(envio, vehiculo);

                    }
                } else if (vehiculo.getTipoVehiculo().equals(TipoVehiculo.AUTO)) {
                    if (obtenerVolumenTotalOcupado(listaContenedores) > 96000.0 && obtenerPesoTotalCargado(listaContenedores) > 35.0 &&
                            obtenerVolumenTotalOcupado(listaContenedores) < 360000.0 && obtenerPesoTotalCargado(listaContenedores) < 75.0 &&
                            obtenerVolumenTotalOcupado(listaContenedores) < vehiculo.getVolumenDisponible() && obtenerPesoTotalCargado(listaContenedores) < vehiculo.getPesoDisponible()) {

                        calcularVolumenOcupadoYPesoCargado(listaContenedores, vehiculo, volumenTotalOcupado, pesoTotalCargado);

                        vehiculo.getListaContenedores().addAll(listaContenedores);
                        contenedoresGuardados = true;

                        vehiculoAsignado = vehiculo;
                        envio.setVehiculo(vehiculoAsignado);
                        asignarVehiculo(envio, vehiculo);

                    }
                } else {
                    if (obtenerVolumenTotalOcupado(listaContenedores) > 360000.0 && obtenerPesoTotalCargado(listaContenedores) > 75.0 &&
                            obtenerVolumenTotalOcupado(listaContenedores) < 5100000.0 && obtenerPesoTotalCargado(listaContenedores) < 700.0 &&
                            obtenerVolumenTotalOcupado(listaContenedores) < vehiculo.getVolumenDisponible() && obtenerPesoTotalCargado(listaContenedores) < vehiculo.getPesoDisponible()) {
                        calcularVolumenOcupadoYPesoCargado(listaContenedores, vehiculo, volumenTotalOcupado, pesoTotalCargado);

                        vehiculo.getListaContenedores().addAll(listaContenedores);
                        contenedoresGuardados = true;

                        vehiculoAsignado = vehiculo;
                        envio.setVehiculo(vehiculoAsignado);
                        asignarVehiculo(envio, vehiculo);
                    }
                }
            }
            if (!contenedoresGuardados) {
                continue vehiculosLoop;
            }

            this.repositorioEnvio.modificarEnvio(envio);

        }

    }

    @Override
    public void asignarVehiculo(Envio envio, Vehiculo vehiculo) {
        envio.setVehiculo(vehiculo);
    }

    private Vehiculo obtenerVehiculoPorId(Long idVehiculoAsignado) {
        return this.repositorioVehiculo.obtenerVehiculoPorId(idVehiculoAsignado);
    }

    private void calcularVolumenOcupadoYPesoCargado(List<Contenedor> listaContenedores, Vehiculo vehiculo, Double volumenTotalOcupado, Double pesoTotalCargado) {
        Double volumenContenedores = obtenerVolumenTotalOcupado(listaContenedores);
        Double pesoContenedores = obtenerPesoTotalCargado(listaContenedores);

        vehiculo.setVolumenDisponible(vehiculo.getVolumenDisponible() - volumenContenedores);
        vehiculo.setPesoDisponible(vehiculo.getPesoDisponible() - pesoContenedores);

        vehiculo.setVolumenOcupado(volumenTotalOcupado + volumenContenedores);
        vehiculo.setPesoCargado(pesoTotalCargado + pesoContenedores);
    }

    private Double obtenerVolumenTotalOcupado(List<Contenedor> listaContenedores) {
        Double volumenTotalOcupadoContenedor = 0.0;
        for (Contenedor contenedoresAGuardar : listaContenedores) {
            volumenTotalOcupadoContenedor += contenedoresAGuardar.getVolumenOcupado();
        }
        return volumenTotalOcupadoContenedor;
    }

    private Double obtenerPesoTotalCargado(List<Contenedor> listaContenedores) {
        Double pesoTotalCargadoContenedor = 0.0;
        for (Contenedor contenedoresAGuardar : listaContenedores) {
            pesoTotalCargadoContenedor += contenedoresAGuardar.getPesoCargado();
        }
        return pesoTotalCargadoContenedor;
    }

    /*@Override
    public Double dimensionesDisponibles() {
        return null;
    }*/
    @Override
    public Vehiculo obtenerVehiculoDePedido(Envio envio) {
        return envio.getVehiculo();
    }
}
