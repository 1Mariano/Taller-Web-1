package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.carrito.ServicioCarrito;
import ar.edu.unlam.tallerweb1.domain.enums.CategoriaProducto;
import ar.edu.unlam.tallerweb1.domain.producto.Producto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ControladorCarritoTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession sesion;
    private ServicioCarrito servicioCarrito;
    private ControladorCarrito controladorCarrito;

    @Before
    public void init(){
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        controladorCarrito = new ControladorCarrito(this.servicioCarrito);
    }

    @Test
    public void queUnUsuarioNoPuedaAgregarProductosAlCarrito(){
        when(request.getSession()).thenReturn(sesion);
        //when(sesion.getAttribute("idUsuario")).thenReturn("1");

        Producto pr = new Producto();
        pr.setId(1L);
        pr.setAlto(8.0);
        pr.setAncho(3.0);
        pr.setCategoria(CategoriaProducto.HIGIENE);
        pr.setDescripcion("algo");
        pr.setFechaVencimiento(LocalDate.now());
        pr.setLargo(10.0);
        pr.setMarca("Pfizer");
        pr.setNombre("Ibupiretas");


        request.setAttribute("producto", pr);
        request.setAttribute("idUsuario", null);
        ResponseEntity resp = controladorCarrito.agregarProducto(pr.getId(), request, response);
        assertThat(resp.getBody()).isEqualTo("Usuario no logueado");
    }

    /*@Test
    public void queUnUsuarioLogueadoPuedaAgregarProductosAlCarrito(){
        when(request.getSession()).thenReturn(sesion);
        //when(sesion.getAttribute("idUsuario")).thenReturn("1L");

        Producto pr = new Producto();
        pr.setId(1L);
        pr.setAlto(8.0);
        pr.setAncho(3.0);
        pr.setCategoria(CategoriaProducto.HIGIENE);
        pr.setDescripcion("algo");
        pr.setFechaVencimiento(LocalDate.now());
        pr.setLargo(10.0);
        pr.setMarca("Pfizer");
        pr.setNombre("Ibupiretas");


        request.setAttribute("producto", pr);
        request.getSession().setAttribute("idUsuario", 1L);
        //request.setAttribute("idUsuario", 1L);
        ResponseEntity resp = controladorCarrito.agregarProducto(pr.getId(), request, response);
        assertThat(resp.getBody()).isEqualTo("El producto se agregó correctamente");
    }*/

}
