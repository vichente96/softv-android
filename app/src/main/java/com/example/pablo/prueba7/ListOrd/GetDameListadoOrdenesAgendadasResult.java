
package com.example.pablo.prueba7.ListOrd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetDameListadoOrdenesAgendadasResult {

    @SerializedName("BaseIdUser")
    @Expose
    private Integer baseIdUser;
    @SerializedName("BaseRemoteIp")
    @Expose
    private Object baseRemoteIp;
    @SerializedName("Clv_Orden")
    @Expose
    private Integer clvOrden;
    @SerializedName("Contrato")
    @Expose
    private String contrato;
    @SerializedName("Status")
    @Expose
    private String status;

    public Integer getBaseIdUser() {
        return baseIdUser;
    }

    public void setBaseIdUser(Integer baseIdUser) {
        this.baseIdUser = baseIdUser;
    }

    public Object getBaseRemoteIp() {
        return baseRemoteIp;
    }

    public void setBaseRemoteIp(Object baseRemoteIp) {
        this.baseRemoteIp = baseRemoteIp;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
