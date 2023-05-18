package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario buscarUsuario(String email, String password);
	Usuario buscarUsuarioExistente(String email);

	Usuario buscarUsuarioPorId(Long id);
	void guardarUsuario(Usuario usuario);
    Usuario buscar(String email);
	void modificar(Usuario usuario);
}
