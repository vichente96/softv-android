package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetDameOrdenesQuejasTotalesResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("GetDameOrdenesQuejasTotalesResult")
    @Expose
    public GetDameOrdenesQuejasTotalesResult getDameOrdenesQuejasTotalesResult;


    public GetDameOrdenesQuejasTotalesResult getGetDameOrdenesQuejasTotalesResult() {
        return getDameOrdenesQuejasTotalesResult;
    }

    public void setGetDameOrdenesQuejasTotalesResult(GetDameOrdenesQuejasTotalesResult getDameOrdenesQuejasTotalesResult) {
        this.getDameOrdenesQuejasTotalesResult = getDameOrdenesQuejasTotalesResult;
    }


}
