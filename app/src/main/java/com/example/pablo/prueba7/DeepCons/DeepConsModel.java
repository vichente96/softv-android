package com.example.pablo.prueba7.DeepCons;

import java.util.Objects;

public class DeepConsModel {
    public static int Contrato;
    public static String ContatoCom;
    public static String STATUS;
    public static String NombreTecnico;
    public static String Obs;

    public DeepConsModel(int Contrato, String ContratoCom, String STATUS, String NombreTecnico, String Obs){
        this.Contrato = Contrato;
        this.ContatoCom = ContratoCom;
        this.STATUS = STATUS;
        this.NombreTecnico = NombreTecnico;
        this.Obs = Obs;

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
}
