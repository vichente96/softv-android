package com.example.pablo.prueba7.Modelos;

public class GetSP_StatusAparatosListResult {
    public String Clv_StatusCableModem;
    public String Concepto;

    public GetSP_StatusAparatosListResult(String clv_StatusCableModem, String concepto) {
        this.Clv_StatusCableModem = clv_StatusCableModem;
        this.Concepto = concepto;
    }

    public String getClv_StatusCableModem() {
        return Clv_StatusCableModem;
    }

    public String getConcepto() {
        return Concepto;
    }

    public void setClv_StatusCableModem(String clv_StatusCableModem) {
        Clv_StatusCableModem = clv_StatusCableModem;
    }

    public void setConcepto(String concepto) {
        Concepto = concepto;
    }
}
