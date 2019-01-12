
package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetDameListadoOrdenesAgendadasResult {


    @SerializedName("Clv_Orden")
    @Expose
    private Integer clvOrden;
    @SerializedName("Contrato")
    @Expose
    private String contrato;
    @SerializedName("NOMBRE")
    @Expose
    private String nombre;
    @SerializedName("Status")
    @Expose
    private String status;



    public Integer getClvOrden() {
        return clvOrden;
    }

    public void setClvOrden(Integer clvOrden) {
        this.clvOrden = clvOrden;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
