package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ControladorRegistroTest {


    private ServicioRegistro servicioRegistro;
    private ControladorRegistro controladorRegistro;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession sesion;

    @Before
    public void init() {
        servicioRegistro = mock(ServicioRegistro.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        controladorRegistro = new ControladorRegistro(this.servicioRegistro);
    }

    @Test
    public void queUnUsuarioNoPuedaRegistrarse() throws UsuarioNoEncontradoException, DniYaRegistradoException, ClavesLongitudException, UsuarioYaExistenteException, ClavesNoCoincidenException {
        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute("idUsuario")).thenReturn("1");

        DatosRegistro usuarioNuevo = new DatosRegistro();
        usuarioNuevo.setEmail("123@123");
        usuarioNuevo.setClave("123456789");
        usuarioNuevo.setRepiteClave("123456789");
        usuarioNuevo.setDni(1020L);

        Usuario usuario = new Usuario();
        usuario.setEmail("123@123");
        usuario.setPassword("123456789");
        usuario.setDni(4050L);


        doThrow(UsuarioYaExistenteException.class).when(servicioRegistro).guardarUsuario(usuario);
        ModelAndView mav = controladorRegistro.validarLogin(usuarioNuevo, request);
        assertThat(mav.getViewName()).isEqualTo("/registro-usuario");
    }

    @Test
    public void queUnUsuarioPuedaRegistrarse() throws UsuarioNoEncontradoException, DniYaRegistradoException, ClavesLongitudException, UsuarioYaExistenteException, ClavesNoCoincidenException {
        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute("idUsuario")).thenReturn("1");

        DatosRegistro usuarioNuevo = new DatosRegistro();
        usuarioNuevo.setEmail("1234@1234");
        usuarioNuevo.setClave("12345678910");
        usuarioNuevo.setRepiteClave("12345678910");
        usuarioNuevo.setDni(102030L);

        Usuario usuario = new Usuario();
        usuario.setEmail("123@123");
        usuario.setPassword("123456789");
        usuario.setDni(4050L);


        doThrow(UsuarioYaExistenteException.class).when(servicioRegistro).guardarUsuario(usuario);
        ModelAndView mav = controladorRegistro.validarLogin(usuarioNuevo, request);

        assertThat(mav.getViewName()).isEqualTo("/registro-usuario");
    }

}
