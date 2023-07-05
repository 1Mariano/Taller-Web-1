package ar.edu.unlam.tallerweb1.delivery;

import java.util.Date;

public class DatosPago {

    private String opcionDePago;
    private String tipoDeTarjeta;
    private String nombreYApellidoDeTitular;
    private String numeroDeTarjeta;
    private String vencimiento;
    private String cvv;

    public String getOpcionDePago() {
        return opcionDePago;
    }

    public void setOpcionDePago(String opcionDePago) {
        this.opcionDePago = opcionDePago;
    }

    public String getTipoDeTarjeta() {
        return tipoDeTarjeta;
    }

    public void setTipoDeTarjeta(String tipoDeTarjeta) {
        this.tipoDeTarjeta = tipoDeTarjeta;
    }

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

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
