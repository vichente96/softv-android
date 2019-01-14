package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBUSCADetOrdSerListResult {

    @SerializedName("TrabajosSer")
    @Expose
    public static List<TrabajosSer> TrabajosSer = null;
    @SerializedName("Descripcion")
    @Expose
    public static String Descripcion;
    @SerializedName("Accion")
    @Expose
    public static String  Accion;



    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public String getAccion() {
        return Accion;
    }

    public void setAccion(String accion) {
        this.Accion = accion;
    }


    public List<TrabajosSer> getTrabajosSer() {
        return TrabajosSer;
    }

    public void setTrabajosSer(List<TrabajosSer> TrabajosSer) {
        this.TrabajosSer = TrabajosSer;
    }


    }


