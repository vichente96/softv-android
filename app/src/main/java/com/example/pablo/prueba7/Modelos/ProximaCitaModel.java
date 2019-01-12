package com.example.pablo.prueba7.Modelos;

public class ProximaCitaModel {
    public  String Calle;
    public int Clave;
    public String Colonia;
    public String Contrato;
    public String Hora;
    public  String NUMERO;
    public String Tipo;


    public ProximaCitaModel(String Calle, int Clave, String Colonia, String Contrato, String Hora, String NUMERO, String Tipo){
        this.Calle = Calle;
        this.Clave = Clave;
        this.Colonia = Colonia;
        this.Contrato = Contrato;
        this.Hora = Hora;
        this.NUMERO = NUMERO;
        this.Tipo = Tipo;

    }

    public String getCalle() {
        return Calle;
    }

    public int getClave() {
        return Clave;
    }

    public String getColonia() {
        return Colonia;
    }

    public String getContrato() {
        return Contrato;
    }

    public String getHora() {
        return Hora;
    }

    public String getNUMERO() {
        return NUMERO;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

    public void setClave(int clave) {
        Clave = clave;
    }

    public void setColonia(String colonia) {
        Colonia = colonia;
    }

    public void setContrato(String contrato) {
        Contrato = contrato;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
}
