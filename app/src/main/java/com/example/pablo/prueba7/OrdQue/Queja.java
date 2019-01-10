package com.example.pablo.prueba7.OrdQue;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Queja {

    @SerializedName("BaseIdUser")
    @Expose
    public Integer baseIdUser;
    @SerializedName("BaseRemoteIp")
    @Expose
    public Object baseRemoteIp;
    @SerializedName("Status")
    @Expose
    public String status;
    @SerializedName("Total")
    @Expose
    public Integer total;

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
