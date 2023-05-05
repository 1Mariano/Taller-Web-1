package ar.edu.unlam.tallerweb1;

public class CajaFuerte {
	
	private boolean abierta;
	
	public CajaFuerte() {
		abierta = true;
	}
	
	public boolean estaAbierta() {
		return abierta;
	}

	public void cerrar(String codigo) {
		// TODO Auto-generated method stub
		if(codigo.isEmpty()) {
			abierta = true;
		} else {
		abierta = false;
		}
	}
	
	public void abrir(String codigo) {
		// TODO Auto-generated method stub
		if(codigo.equals("1234")) {
			abierta = true;
		} else {
		abierta = false;
		}
	}
}
