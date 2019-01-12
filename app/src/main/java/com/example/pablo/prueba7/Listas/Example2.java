package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetdameSerDELCliresumenResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example2 {

    @SerializedName("GetdameSerDELCliresumenResult")
    @Expose
    private List<GetdameSerDELCliresumenResult> getdameSerDELCliresumenResult = null;

    public List<GetdameSerDELCliresumenResult> getdameSerDELCliresumenResult() {
        return getdameSerDELCliresumenResult;
    }

    public void setdameSerDELCliresumenResult(List<GetdameSerDELCliresumenResult> getdameSerDELCliresumenResult) {
        this.getdameSerDELCliresumenResult = getdameSerDELCliresumenResult;
    }
}
