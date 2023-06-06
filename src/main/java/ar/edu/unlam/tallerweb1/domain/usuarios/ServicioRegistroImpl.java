package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.exceptions.ClavesLongitudException;
import ar.edu.unlam.tallerweb1.exceptions.ClavesNoCoincidenException;
import ar.edu.unlam.tallerweb1.exceptions.DniYaRegistradoException;
import ar.edu.unlam.tallerweb1.exceptions.UsuarioYaExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioRegistro")
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro {

    private RepositorioUsuario repositorioUsuario;


    @Autowired
    public ServicioRegistroImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public Usuario consultarUsuario(String email) {
        return repositorioUsuario.buscarUsuarioExistente(email);
    }

    @Override
    public void guardarUsuario(Usuario usuarioNuevo) throws ClavesLongitudException, UsuarioYaExistenteException, ClavesNoCoincidenException, DniYaRegistradoException {
        if (laclaveTieneLongitudIncorrecta(usuarioNuevo)) {
            throw new ClavesLongitudException();
        }
        if (repositorioUsuario.buscarUsuarioPorEmail(usuarioNuevo.getEmail()) != null) {
            throw new UsuarioYaExistenteException();
        }
        if (lasClavesNoSonIguales(usuarioNuevo)) {
            throw new ClavesNoCoincidenException();
        }
        if (repositorioUsuario.buscarUsuarioPorDni(usuarioNuevo.getDni()) != null) {
            throw new DniYaRegistradoException();
        }
        repositorioUsuario.guardarUsuario(usuarioNuevo);
    }

    private boolean lasClavesNoSonIguales(Usuario usuarioNuevo) {
        return !usuarioNuevo.getPassword().equals(usuarioNuevo.getRepitePassword());
    }

    private boolean laclaveTieneLongitudIncorrecta(Usuario usuarioNuevo) {
        return usuarioNuevo.getPassword().length() < 8;
    }

    @Override
    public Usuario consultarUsuarioExistente(String email) {
        return repositorioUsuario.buscarUsuarioExistente(email);
    }

    public Usuario consultarDniYaRegistrado(Long dni) {
        return repositorioUsuario.buscarUsuarioPorDni(dni);
    }

    // aca pondria como metodo una logica para saber si existe un usuario porque es logica del negocio y no
    // necesita saber como hago las cosas el controlador
}
