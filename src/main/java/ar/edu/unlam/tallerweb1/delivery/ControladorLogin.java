package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.exceptions.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;






@Controller
public class ControladorLogin {

	// La anotacion @Autowired indica a Spring que se debe utilizar el contructor como mecanismo de inyección de dependencias,
	// es decir, qeue lo parametros del mismo deben ser un bean de spring y el framewrok automaticamente pasa como parametro
	// el bean correspondiente, en este caso, un objeto de una clase que implemente la interface ServicioLogin,
	// dicha clase debe estar anotada como @Service o @Repository y debe estar en un paquete de los indicados en
	// applicationContext.xml
	private final ServicioLogin servicioLogin;


	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin){
		this.servicioLogin = servicioLogin;
	}

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession sesion;


	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto con key 'datosLogin' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		modelo.put("datosLogin", new DatosLogin());
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelo
		if (request.getSession().getAttribute("idUsuario") != null){
			return new ModelAndView("redirect:/home");
		}

		return new ModelAndView("login", modelo);
	}
	@RequestMapping(path = "/logout")
	public ModelAndView irALoginLogout() {

		request.getSession().invalidate();
		return new ModelAndView("redirect:/home");
	}

	//registro usuario


	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El metodo recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin,
									 HttpServletRequest request, HttpServletResponse response) {
		ModelMap model = new ModelMap();

		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a traves de la URL correspondiente a esta
		/*Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());*/
		try {
			this.servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
		} catch (UsuarioNoEncontradoException e){
			model.put("error", "Usuario o clave incorrecta");
			return logueoFallido(model, "Usuario o Contraseña incorrectos");
		}

            /*if (usuarioBuscado != null) {
				request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
				model.put("usuario", usuarioBuscado);
				model.put("id", usuarioBuscado.getId());
				return new ModelAndView("redirect:/home", model);
			} else {
				// si el usuario no existe agrega un mensaje de error en el modelo.
				model.put("error", "Usuario o clave incorrecta");
			}*/
		//return new ModelAndView("login", model);
		Usuario usuarioBuscado = this.servicioLogin.consultarUsuarioPorEmail(datosLogin.getEmail());
		//model.put("usuario", usuarioBuscado);
		//model.put("id", usuarioBuscado.getId());
		request.getSession().setAttribute("idUsuario", usuarioBuscado.getId());
		request.getSession().setAttribute("correo", usuarioBuscado.getEmail());

		// Guardar las cookies si el checkbox está seleccionado
		if (request.getParameter("recordarDatos") != null) {
			Cookie emailCookie = new Cookie("email", datosLogin.getEmail());
			Cookie passwordCookie = new Cookie("password", datosLogin.getPassword());
			emailCookie.setMaxAge(3600); // Establece la expiración de la cookie en 1 hora (puedes ajustarla según tus necesidades)
			passwordCookie.setMaxAge(3600);
			response.addCookie(emailCookie);
			response.addCookie(passwordCookie);
		} else {
			// Eliminar las cookies si el checkbox no está seleccionado
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("email") || cookie.getName().equals("password")) {
						cookie.setValue("");
						cookie.setMaxAge(0); // Establece la expiración de la cookie en 0 para eliminarla
						response.addCookie(cookie);
					}
				}
			}
		}

		return new ModelAndView("redirect:/home", model);
	}

	private ModelAndView logueoFallido(ModelMap model, String usuarioOContraseñaIncorrectos) {
		model.put("error", usuarioOContraseñaIncorrectos);
		return new ModelAndView("login", model);
	}

	// Escucha la URL /home por GET, y redirige a una vista.
    /*@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome(@RequestParam (value = "id") Long id) {
		Usuario usuario = this.servicioLogin.obtenerUsuarioPorId(id);
		ModelMap model = new ModelMap();
		model.put("usuario", usuario);

		return new ModelAndView("home", model);
	}*/

	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {

		return new ModelAndView("redirect:/home");
	}
}
