package com.example.pablo.prueba7.Request;


public class TecnicoModel {
    public String clv_tecnico;
    public static String BaseIdUser;


    public TecnicoModel(String clv_tecnico,  String baseIdUser) {
        this.clv_tecnico = clv_tecnico;
        this.BaseIdUser = baseIdUser;
    }

    public  String getClv_tecnico() {
        return clv_tecnico;
    }
    public void setClv_tecnico(String clv_tecnico) {
        this.clv_tecnico = clv_tecnico;
    }

    public static String getBaseIdUser() {
        return BaseIdUser;
    }

    public static void setBaseIdUser(String baseIdUser) {
        BaseIdUser = baseIdUser;
    }
}