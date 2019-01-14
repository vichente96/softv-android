package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBUSCADetOrdSerListResult {
    @SerializedName("TrabajosSer")
    @Expose
    private List<TrabajosSer> TrabajosSer = null;
    @SerializedName("Descripcion")
    @Expose
    private Object Descripcion;
    @SerializedName("Accion")
    @Expose
    private Object Accion;



    public Object getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(Object Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Object getAccion() {
        return Accion;
    }

    public void setAccion(Object Accion) {
        this.Accion = Accion;
    }


    public List<TrabajosSer> getTrabajosSer() {
        return TrabajosSer;
    }

    public void setTrabajosSer(List<TrabajosSer> TrabajosSer) {
        this.TrabajosSer = TrabajosSer;
    }


    }


