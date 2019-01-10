package com.example.pablo.prueba7.OrdQue;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDameOrdenesQuejasTotalesResult {

    @SerializedName("BaseIdUser")
    @Expose
    private Integer BaseIdUser;
    @SerializedName("BaseRemoteIp")
    @Expose
    private Object BaseRemoteIp;
    @SerializedName("OrdSer")
    @Expose
    private List<OrdSer> OrdSer = null;
    @SerializedName("Queja")
    @Expose
    private List<Queja> Queja = null;

    public Integer getBaseIdUser() {
        return BaseIdUser;
    }

    public void setBaseIdUser(Integer BaseIdUser) {
        this.BaseIdUser = BaseIdUser;
    }

    public Object getBaseRemoteIp() {
        return BaseRemoteIp;
    }

    public void setBaseRemoteIp(Object BaseRemoteIp) {
        this.BaseRemoteIp = BaseRemoteIp;
    }

    public List<OrdSer> getOrdSer() {
        return OrdSer;
    }

    public void setOrdSer(List<OrdSer> OrdSer) {
        this.OrdSer = OrdSer;
    }

    public List<Queja> getQueja() {
        return Queja;
    }

    public void setQueja(List<Queja> Queja) {
        this.Queja = Queja;
    }


}
