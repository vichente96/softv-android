package com.example.pablo.prueba7.ListOrd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOrd {

    @SerializedName("BaseIdUser")
    @Expose
    private Integer baseIdUser;

    @SerializedName("BaseRemoteIp")
    @Expose
    private Object baseRemoteIp;

    @SerializedName("Clv_Orden")
    @Expose
    private int clv_orden;

    @SerializedName("Contrato")
    @Expose
    private String contrato;

    @SerializedName("Status")
    @Expose
    private String status;

    public Integer getBaseIdUser() {
        return baseIdUser;
    }

    public Object getBaseRemoteIp() {
        return baseRemoteIp;
    }

    public int getClv_orden() {
        return clv_orden;
    }

    public String getContrato() {
        return contrato;
    }

    public String getStatus() {
        return status;
    }

    public void setBaseIdUser(Integer baseIdUser) {
        this.baseIdUser = baseIdUser;
    }

    public void setBaseRemoteIp(Object baseRemoteIp) {
        this.baseRemoteIp = baseRemoteIp;
    }

    public void setClv_orden(int clv_orden) {
        this.clv_orden = clv_orden;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
