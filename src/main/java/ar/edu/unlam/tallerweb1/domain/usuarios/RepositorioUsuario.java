package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.ClavesLongitudException;
import ar.edu.unlam.tallerweb1.exceptions.UsuarioYaExistenteException;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario buscarUsuario(String email, String password);
	Usuario buscarUsuarioExistente(String email);
	Usuario buscarUsuarioPorId(Long id);
	void guardarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorDni(Long dni);
	void modificar(Usuario usuario);
	Usuario buscarUsuarioPorEmail(String email);
}
