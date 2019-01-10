package com.example.pablo.prueba7.OrdQue;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrdSer {

    @SerializedName("BaseIdUser")
    @Expose
    private Integer baseIdUser;
    @SerializedName("BaseRemoteIp")
    @Expose
    private Object baseRemoteIp;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Total")
    @Expose
    private Integer total;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


}
