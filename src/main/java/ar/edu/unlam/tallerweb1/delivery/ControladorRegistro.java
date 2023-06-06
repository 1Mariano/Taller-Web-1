package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistro;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.exceptions.ClavesLongitudException;
import ar.edu.unlam.tallerweb1.exceptions.DniYaRegistradoException;
import ar.edu.unlam.tallerweb1.exceptions.UsuarioYaExistenteException;
import ar.edu.unlam.tallerweb1.exceptions.ClavesNoCoincidenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorRegistro {

    private final ServicioRegistro servicioRegistro;

    @Autowired
    public ControladorRegistro(ServicioRegistro servicioRegistro) {

        this.servicioRegistro = servicioRegistro;
    }

    @RequestMapping("/registro-usuario")
    public ModelAndView irARegistro() {

        ModelMap modelo = new ModelMap();
        // Se agrega al modelo un objeto con key 'datosLogin' para que el mismo sea asociado
        // al model attribute del form que esta definido en la vista 'login'
        modelo.put("usuario", new DatosRegistro());
        // Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
        // y se envian los datos a la misma  dentro del modelo
        return new ModelAndView("/registro-usuario", modelo);
    }
    /*
    @RequestMapping(value = "/registro-usuario", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(@ModelAttribute("usuario") Usuario usuario) throws ClavesLongitudException, UsuarioYaExistenteException, ClavesNoCoincidenException {
        ModelMap modelo = new ModelMap();
        if (servicioRegistro.consultarUsuarioExistente(usuario.getEmail()) != null) {
            modelo.put("errorEmail", "El email ya está registrado");
            return new ModelAndView("/registro-usuario", modelo);
        }
        if (!usuario.getPassword().equals(usuario.getRepiteContraseña())) {
            modelo.put("errorContraseña", "Las contraseñas no coinciden");
            return new ModelAndView("/registro-usuario", modelo);
        }
        servicioRegistro.guardarUsuario(usuario);
        return new ModelAndView("redirect:/registro-exitoso");
    }
*/

    @RequestMapping(path = "/validar-registro", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("usuario") DatosRegistro datosRegistro, HttpServletRequest request) {


        // invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
        // hace una llamada a otro action a traves de la URL correspondiente a esta
        //Usuario usuarioBuscado = servicioRegistro.consultarUsuario(datosRegistro.getEmail());
        /*if (usuarioBuscado != null) {
            // si el usuario existe agrega un mensaje de error en el modelo.
            model.put("error", "Usuario ya existente");
        } else {*/
        //Usuario usuarioARegistrar = new Usuario();
        ModelMap modelo = new ModelMap();
        try {
            Usuario usuarioARegistrarConfirmado = new Usuario();
            usuarioARegistrarConfirmado.setEmail(datosRegistro.getEmail());
            usuarioARegistrarConfirmado.setPassword(datosRegistro.getClave());
            usuarioARegistrarConfirmado.setRepitePassword(datosRegistro.getRepiteClave());
            usuarioARegistrarConfirmado.setNombre(datosRegistro.getNombre());
            usuarioARegistrarConfirmado.setApellido(datosRegistro.getApellido());
            usuarioARegistrarConfirmado.setDni(datosRegistro.getDni());
            usuarioARegistrarConfirmado.setCalle(datosRegistro.getCalle());
            usuarioARegistrarConfirmado.setNumero(datosRegistro.getNumero());
            usuarioARegistrarConfirmado.setPisoODepartamento(datosRegistro.getPisoODepartamento());
            usuarioARegistrarConfirmado.setCodigoPostal(datosRegistro.getCodigoPostal());
            usuarioARegistrarConfirmado.setLocalidad(datosRegistro.getLocalidad());
            usuarioARegistrarConfirmado.setRol("admin");
            usuarioARegistrarConfirmado.setActivo(true);
            this.servicioRegistro.guardarUsuario(usuarioARegistrarConfirmado);
            //request.getSession().setAttribute("ROL", usuarioARegistrarConfirmado.getRol());
        } catch (ClavesLongitudException e) {
            return registroFallido(modelo, "La clave debe tener al menos 8 caracteres");
        } catch (UsuarioYaExistenteException e) {
            return registroFallidoPorEmail(modelo, "El usuario ya se encuentra registrado");
        } catch (ClavesNoCoincidenException e) {
            return registroFallido(modelo, "Las claves no coinciden");
        } catch (DniYaRegistradoException e) {
            return registroFallidoPorDni(modelo, "Ya existe un usuario con ese DNI");
        }
        return registroExitoso(modelo, "Registro exitoso");


            /*Usuario usuarioARegistrar = servicioRegistro.consultarUsuarioExistente(datosRegistro.getEmail());
            if (usuarioARegistrar == null)
            {
                Usuario usuarioARegistrarConfirmado = new Usuario();
                usuarioARegistrarConfirmado.setEmail(datosRegistro.getEmail());
                usuarioARegistrarConfirmado.setPassword(datosRegistro.getClave());
                usuarioARegistrarConfirmado.setRol("admin");
                usuarioARegistrarConfirmado.setActivo(true);
                this.servicioRegistro.guardarUsuario(usuarioARegistrarConfirmado);
                request.getSession().setAttribute("ROL", usuarioARegistrarConfirmado.getRol());
                //String email = usuarioARegistrarConfirmado.getEmail();
                //modelo.put("email", usuarioARegistrarConfirmado.getEmail());
                return new ModelAndView("redirect:/login");
            } else{
                modelo.put("error", "Usuario ya existente o no valido");
            }*/

        //return new ModelAndView("registro-usuario", modelo);


        //}
        //return new ModelAndView("registro-usuario", model);
    }

    private ModelAndView registroExitoso(ModelMap modelo, String mensaje) {
       // return new ModelAndView("redirect:/registro-exitoso");
        modelo.put("exito", mensaje);
        return new ModelAndView("/registro-usuario", modelo);
    }

    private ModelAndView registroFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        return new ModelAndView("/registro-usuario", modelo);
    }

    private ModelAndView registroFallidoPorEmail(ModelMap modelo, String mensaje) {
        modelo.put("errorEmail", mensaje);
        return new ModelAndView("/registro-usuario", modelo);
    }

    private ModelAndView registroFallidoPorDni(ModelMap modelo, String mensaje) {
        modelo.put("errorDni", mensaje);
        return new ModelAndView("/registro-usuario", modelo);
    }

/*
    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHome() {
        return new ModelAndView("home");
    }

    // Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/login");
    }
*/


}
