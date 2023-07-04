package ar.edu.unlam.tallerweb1.delivery;

import java.util.Date;

public class DatosPago {
    private String nombreYApellidoDeTitular;
    private String numeroDeTarjeta;
    private String cvv;
    private Date vencimiento;

    public String getNombreYApellidoDeTitular() {
        return nombreYApellidoDeTitular;
    }

    public void setNombreYApellidoDeTitular(String nombreYApellidoDeTitular) {
        this.nombreYApellidoDeTitular = nombreYApellidoDeTitular;
    }

    public String getNumeroDeTarjeta() {
        return numeroDeTarjeta;
    }

    public void setNumeroDeTarjeta(String numeroDeTarjeta) {
        this.numeroDeTarjeta = numeroDeTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }
}
