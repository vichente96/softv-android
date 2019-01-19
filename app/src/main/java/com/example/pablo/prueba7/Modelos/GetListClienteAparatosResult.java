package com.example.pablo.prueba7.Modelos;

public class GetListClienteAparatosResult {
    public  int ControNet;
    public  String Descripcion;
    public  int idArticulo;
    public String Mac;

    public GetListClienteAparatosResult (int ControlNet, String Descripcion, int idArticulo, String mac){
        this.ControNet = ControlNet;
        this.Descripcion = Descripcion;
        this.idArticulo = idArticulo;
        this.Mac = mac;

    }

    public  int getControNet() {
        return ControNet;
    }

    public  String getDescripcion() {
        return Descripcion;
    }

    public  int getIdArticulo() {
        return idArticulo;
    }

    public  void setControNet(int ControNet) {
        ControNet = ControNet;
    }

    public  void setDescripcion(String Descripcion) {
        Descripcion = Descripcion;
    }

    public  void setIdArticulo(int idArticulo) {
        idArticulo = idArticulo;
    }

    public String getMac() {
        return Mac;
    }

    public void setMac(String mac) {
        Mac = mac;
    }
}
