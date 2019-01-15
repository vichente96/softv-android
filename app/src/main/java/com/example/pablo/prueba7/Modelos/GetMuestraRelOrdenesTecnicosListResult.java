package com.example.pablo.prueba7.Modelos;

public class GetMuestraRelOrdenesTecnicosListResult {
    public String NOMBRE;
    public int CLV_TECNICO;



    public GetMuestraRelOrdenesTecnicosListResult(String NOMBRE, int CLV_TECNICO) {
        this.NOMBRE = NOMBRE;
        this.CLV_TECNICO = CLV_TECNICO;

    }

    public  String getNOMBRE() {
        return NOMBRE;
    }
    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public int getCLV_TECNICO() {
        return CLV_TECNICO;
    }

    public void setCLV_TECNICO(int CLV_TECNICO) {
        this.CLV_TECNICO = CLV_TECNICO;
    }
}
