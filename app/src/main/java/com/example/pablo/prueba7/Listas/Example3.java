package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetBUSCADetOrdSerListResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example3 {
    @SerializedName("GetBUSCADetOrdSerListResult")
    @Expose
    public GetBUSCADetOrdSerListResult getBUSCADetOrdSerListResult;

    public GetBUSCADetOrdSerListResult  getBUSCADetOrdSerListResult() {
        return  getBUSCADetOrdSerListResult;
    }

    public void setGetBUSCADetOrdSerListResult(GetBUSCADetOrdSerListResult getBUSCADetOrdSerListResult) {
        this.getBUSCADetOrdSerListResult = getBUSCADetOrdSerListResult;
    }


}







