package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.ClavesLongitudException;
import ar.edu.unlam.tallerweb1.exceptions.UsuarioNoEncontradoException;
import ar.edu.unlam.tallerweb1.exceptions.UsuarioYaExistenteException;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioLogin {

	Usuario consultarUsuario(String email, String password) throws UsuarioNoEncontradoException;

	Usuario obtenerUsuarioPorId(Long id);

	Usuario consultarUsuarioPorEmail(String email);
}
