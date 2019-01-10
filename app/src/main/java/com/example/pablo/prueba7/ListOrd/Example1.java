package com.example.pablo.prueba7.ListOrd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Example1 {

    @SerializedName("GetDameListadoOrdenesAgendadasResult")
    @Expose
    private List<GetDameListadoOrdenesAgendadasResult> getDameListadoOrdenesAgendadasResult = null;

    public List<GetDameListadoOrdenesAgendadasResult> getGetDameListadoOrdenesAgendadasResult() {
        return getDameListadoOrdenesAgendadasResult;
    }

    public void setGetDameListadoOrdenesAgendadasResult(List<GetDameListadoOrdenesAgendadasResult> getDameListadoOrdenesAgendadasResult) {
        this.getDameListadoOrdenesAgendadasResult = getDameListadoOrdenesAgendadasResult;
    }



}
