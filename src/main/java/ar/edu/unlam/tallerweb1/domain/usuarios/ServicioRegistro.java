package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.exceptions.*;

public interface ServicioRegistro {
    Usuario consultarUsuario(String email) throws UsuarioNoEncontradoException;

    void guardarUsuario(Usuario usuario) throws ClavesLongitudException, UsuarioYaExistenteException, ClavesNoCoincidenException, DniYaRegistradoException;

    Usuario consultarUsuarioExistente(String email);
}
