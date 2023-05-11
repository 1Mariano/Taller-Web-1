package ar.edu.unlam.tallerweb1;





import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import ar.edu.unlam.tallerweb1.domain.vehiculos.Auto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Moto;
import ar.edu.unlam.tallerweb1.domain.vehiculos.Vehiculo;
import org.junit.Test;

public class CajaFuerteTest {
	//cuando creo una caja fuerte tiene que estar abierta
	//no puedo cerrar la caja fuerte si no ingreso un codigo
	//puedo abrir la caja fuerte con el codigo con el que la cerre
	
	//puedoAbrirLaCajaConElCodigoConElQueLaCerre
	//noPuedoAbrirLaCajaFuerteSinIngresrElCodigoConElQueLaCerre
	private final String CODIGO = "1234";
	private final String CODIGO_VACIO = "";
	
	@Test
	public void cuandoCreoUnaCajaFuerteTieneQueEstarAbierta() {
		//preparacion --> given
		givenNoExisteCajaFuerte();
		// ejecucion  --> when
		CajaFuerte cajaFuerte = whenCreoLaCajaFuerte();
		//comprobacion -> then
		thenLaCajaFuerteEstaAbierta(cajaFuerte);
	}

	@Test
	public void siCierroLaCajaFuerteConCodigoDeberiaEstarCerrada() {
		CajaFuerte caja = givenExisteCajaFuerte();
		whenCierroLaCajaConCodigo(caja, CODIGO);
		thenLaCajaEstaCerrada(caja);
	}
	
	@Test
	public void siCierroLaCajaFuerteSinCodigoDeberiaEstarAbierta() {
		CajaFuerte caja = givenExisteCajaFuerte();
		whenCierroLaCajaConCodigo(caja, CODIGO_VACIO);
		thenLaCajaFuerteEstaAbierta(caja);
	}
	
	@Test
	public void puedoAbrirLaCajaConElCodigoConElQueLaCerre() {
		//creo la caja
		CajaFuerte caja = givenExisteCajaFuerte();
		//compruebo que la caja esta abierta
		thenLaCajaFuerteEstaAbierta(caja);
		//cierro la caja con mi codigo
		whenCierroLaCajaConCodigo(caja, CODIGO);
		//compruebo que la caja este cerrada
		thenLaCajaEstaCerrada(caja);
		//abro la caja con mi codigo
		whenAbroLaCajaConCodigo(caja, CODIGO);
		//compruebo que la caja este abierta
		thenLaCajaFuerteEstaAbierta(caja);
	}
	
	@Test
	public void noPuedoAbrirLaCajaFuerteSinIngresrElCodigoConElQueLaCerre() {
		//creo la caja
		CajaFuerte caja = givenExisteCajaFuerte();
		//compruebo que la caja esta abierta
		thenLaCajaFuerteEstaAbierta(caja);
		//cierro la caja con mi codigo
		whenCierroLaCajaConCodigo(caja, CODIGO);
		//compruebo que la caja este cerrada
		thenLaCajaEstaCerrada(caja);
		//abro la caja con mi codigo
		whenAbroLaCajaConCodigo(caja, CODIGO_VACIO);
		//compruebo que la caja este abierta
		thenLaCajaEstaCerrada(caja);
	}
	
	private CajaFuerte givenExisteCajaFuerte() {
		// TODO Auto-generated method stub
		return new CajaFuerte();
	}
	
	private void givenNoExisteCajaFuerte() {
		// TODO Auto-generated method stub
		
	}
	
	private void whenCierroLaCajaConCodigo(CajaFuerte caja, String codigo) {
		// TODO Auto-generated method stub
		caja.cerrar(codigo);
	}
	private void whenAbroLaCajaConCodigo(CajaFuerte caja, String pass) {
		// TODO Auto-generated method stub
		caja.abrir(pass);
	}

	
	
	
	private CajaFuerte whenCreoLaCajaFuerte() {
		// TODO Auto-generated method stub
		return new CajaFuerte();
		
	}
	
	
	private void thenLaCajaEstaCerrada(CajaFuerte caja) {
		// TODO Auto-generated method stub
		assertThat(caja.estaAbierta()).isFalse();
	}
	
	private void thenLaCajaFuerteEstaAbierta(CajaFuerte cajaFuerte) {
		// TODO Auto-generated method stub
		assertThat(cajaFuerte.estaAbierta()).isTrue();
	}

	

	
}
