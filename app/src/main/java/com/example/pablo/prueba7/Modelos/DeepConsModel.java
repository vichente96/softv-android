package com.example.pablo.prueba7.Modelos;


public class DeepConsModel {
    public static int Contrato;
    public static String ContatoCom;
    public static String STATUS;
    public static String NombreTecnico;
    public static String Obs;
    public static String Clv_Orden;

    public DeepConsModel(int Contrato, String ContratoCom, String STATUS, String NombreTecnico, String Obs, String Clv_Orden){
        this.Contrato = Contrato;
        this.ContatoCom = ContratoCom;
        this.STATUS = STATUS;
        this.NombreTecnico = NombreTecnico;
        this.Obs = Obs;
        this.Clv_Orden = Clv_Orden;

    }

    public static int getContrato() {
        return Contrato;
    }

    public static String getContatoCom() {
        return ContatoCom;
    }

    public static String getSTATUS() {
        return STATUS;
    }

    public void setContrato(int contrato) {
        Contrato = contrato;
    }

    public void setContatoCom(String contatoCom) {
        ContatoCom = contatoCom;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public static String getNombreTecnico() {
        return NombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        NombreTecnico = nombreTecnico;
    }

    public static String getObs() {
        return Obs;
    }

    public static void setObs(String obs) {
        Obs = obs;
    }

    public static String getClv_Orden() {
        return Clv_Orden;
    }

    public static void setClv_Orden(String clv_Orden) {
        Clv_Orden = clv_Orden;
    }
}
