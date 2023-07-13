package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ControladorLoginTest {
    private ServicioLogin servicioLogin;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession sesion;
    private ControladorLogin controladorLogin;
    private ServicioRegistro servicioRegistro;
    private RepositorioUsuario repositorioUsuario;

    private ControladorRegistro controladorRegistro;

    @Before
    public void init() {
        servicioLogin = mock(ServicioLogin.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        controladorLogin = new ControladorLogin(this.servicioLogin);
        servicioRegistro = mock(ServicioRegistro.class);
        repositorioUsuario = mock(RepositorioUsuario.class);
        controladorRegistro = new ControladorRegistro(this.servicioRegistro);
    }

    @Test
    public void queUnUsuarioNoEsteRegistradoNoSePuedaLoguear() throws UsuarioNoEncontradoException {
        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute("idUsuario")).thenReturn("1");

        DatosLogin usuario = new DatosLogin();
        usuario.setEmail("123@123");
        usuario.setPassword("12345678");

        doThrow(UsuarioNoEncontradoException.class).when(servicioLogin).consultarUsuario(usuario.getEmail(), usuario.getPassword());
        ModelAndView mav = controladorLogin.validarLogin(usuario, request, response);
        assertThat(mav.getViewName()).isEqualTo("login");
    }

    @Test
    public void queUnUsuarioNoSePuedaLoguearSiNoCoincidenLasContrasenas() throws UsuarioNoEncontradoException, DniYaRegistradoException, ClavesLongitudException, UsuarioYaExistenteException, ClavesNoCoincidenException {
        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute("idUsuario")).thenReturn("1");



        Usuario usuario = new Usuario();
        usuario.setEmail("123@123");
        usuario.setPassword("12345678");
        usuario.setRepitePassword("12345678");
        //usuario.setDni(4050L);


        DatosLogin usuarioRegistrado = new DatosLogin();
        usuarioRegistrado.setEmail("123@123");
        usuarioRegistrado.setPassword("123456");
        usuarioRegistrado.setRecordarDatos(true);

        DatosRegistro usuarioNuevo = new DatosRegistro();
        usuarioNuevo.setEmail("123@1234");
        usuarioNuevo.setClave("12345678");
        usuarioNuevo.setRepiteClave("12345678");
        //usuarioNuevo.setDni(102030L);


        controladorRegistro.validarLogin(usuarioNuevo, request);
        doThrow(UsuarioYaExistenteException.class).when(servicioRegistro).guardarUsuario(usuario);
        doThrow(UsuarioNoEncontradoException.class).when(servicioLogin).consultarUsuario(usuarioRegistrado.getEmail(), usuarioRegistrado.getPassword());

        ModelAndView mav = controladorLogin.validarLogin(usuarioRegistrado, request, response);
        assertThat(mav.getViewName()).isEqualTo("login");
    }


}
