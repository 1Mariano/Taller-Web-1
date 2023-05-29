package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.exceptions.ClavesLongitudException;
import ar.edu.unlam.tallerweb1.exceptions.UsuarioYaExistenteException;
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
    public Usuario guardarUsuario(Usuario usuarioNuevo) throws ClavesLongitudException, UsuarioYaExistenteException {
        if(laclaveTieneLongitudIncorrecta(usuarioNuevo)){
            throw new ClavesLongitudException();
        }
        if (servicioRegistro.buscar(usuarioNuevo.getEmail()) != null){
            throw new UsuarioYaExistenteException();
        }

        servicioRegistro.guardarUsuario(usuarioNuevo);
        return usuarioNuevo;
    }

    private boolean laclaveTieneLongitudIncorrecta(Usuario usuarioNuevo) {
        return usuarioNuevo.getPassword().length() < 8;
    }

    @Override
    public Usuario consultarUsuarioExistente(String email) {
            return servicioRegistro.buscarUsuarioExistente(email);

    }

    // aca pondria como metodo una logica para saber si existe un usuario porque es logica del negocio y no
    // necesita saber como hago las cosas el controlador
}
