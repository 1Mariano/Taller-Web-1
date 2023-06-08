package ar.edu.unlam.tallerweb1.delivery;

public class DatosEnvio {

    private String calle;
    private Integer numero;
    private String pisoODepartamento;
    private String codigoPostal;
    private String localidad;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPisoODepartamento() {
        return pisoODepartamento;
    }

    public void setPisoODepartamento(String pisoODepartamento) {
        this.pisoODepartamento = pisoODepartamento;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
