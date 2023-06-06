package ar.edu.unlam.tallerweb1.delivery;

public class DatosLogin {
    private String email;
    private String password;
    private Boolean recordarDatos;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRecordarDatos() {
        return recordarDatos;
    }

    public void setRecordarDatos(Boolean recordarDatos) {
        this.recordarDatos = recordarDatos;
    }
}
