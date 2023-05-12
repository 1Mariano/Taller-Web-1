package ar.edu.unlam.tallerweb1.domain.usuarios;

public interface ServicioRegistro {
    Usuario consultarUsuario(String email);

    void guardarUsuario(Usuario usuario);

    Usuario consultarUsuarioExistente(String email);
}
