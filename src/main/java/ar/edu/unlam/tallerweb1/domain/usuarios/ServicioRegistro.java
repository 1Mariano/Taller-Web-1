package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.exceptions.ClavesLongitudException;
import ar.edu.unlam.tallerweb1.exceptions.UsuarioYaExistenteException;

public interface ServicioRegistro {
    Usuario consultarUsuario(String email);

    Usuario guardarUsuario(Usuario usuario) throws ClavesLongitudException, UsuarioYaExistenteException;

    Usuario consultarUsuarioExistente(String email);
}
