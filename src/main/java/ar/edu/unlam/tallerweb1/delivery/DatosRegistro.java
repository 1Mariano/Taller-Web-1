package ar.edu.unlam.tallerweb1.delivery;

public class DatosRegistro {
    private String email;
    private String clave;
    private String repiteClave;
    private String nombre;
    private String apellido;
    private Long dni;
    private String calle;
    private Integer numero;
    private String pisoODepartamento;
    private String codigoPostal;
    private String localidad;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRepiteClave() {
        return repiteClave;
    }

    public void setRepiteClave(String repiteClave) {
        this.repiteClave = repiteClave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

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
