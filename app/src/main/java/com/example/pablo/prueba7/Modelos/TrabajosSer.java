package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrabajosSer {

    @SerializedName("Accion")
    @Expose

    public static String accion;
    @SerializedName("Descripcion")
    @Expose
    public static String descripcion;


    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion= descripcion;
    }







}
