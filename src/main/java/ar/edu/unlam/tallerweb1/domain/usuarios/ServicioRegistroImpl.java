package ar.edu.unlam.tallerweb1.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioRegistro")
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro{

    private RepositorioUsuario servicioRegistro;


    @Autowired
    public ServicioRegistroImpl(RepositorioUsuario servicioRegistro){
        this.servicioRegistro = servicioRegistro;
    }

    @Override
    public Usuario consultarUsuario(String email) {
        return servicioRegistro.buscarUsuarioExistente(email);
    }

    @Override
    public void guardarUsuario(Usuario usuarioNuevo) {

        servicioRegistro.guardarUsuario(usuarioNuevo);
    }

    @Override
    public Usuario consultarUsuarioExistente(String email) {
            return servicioRegistro.buscarUsuarioExistente(email);

    }

    // aca pondria como metodo una logica para saber si existe un usuario porque es logica del negocio y no
    // necesita saber como hago las cosas el controlador
}
