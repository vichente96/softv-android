package com.example.pablo.prueba7.Modelos;

public class InfoClienteModelo {
    public static String CALLE;
    public static String CIUDAD;
    public static String COLONIA;
    public static String Compania;
    public static String NOMBRE;
    public static String NUMERO;

    public InfoClienteModelo(String CALLE, String CIUDAD, String COLONIA, String Compania, String NOMBRE, String NUMERO){
        this.CALLE = CALLE;
        this.CIUDAD = CIUDAD;
        this.COLONIA = COLONIA;
        this.Compania = Compania;
        this.NOMBRE = NOMBRE;
        this.NUMERO = NUMERO;
    }

    public static String getCALLE() {
        return CALLE;
    }

    public static String getCIUDAD() {
        return CIUDAD;
    }

    public static String getCOLONIA() {
        return COLONIA;
    }

    public static String getCompania() {
        return Compania;
    }

    public static String getNUMERO() {
        return NUMERO;
    }

    public static String getNOMBRE() {
        return NOMBRE;
    }

    public static void setCALLE(String CALLE) {
        InfoClienteModelo.CALLE = CALLE;
    }

    public static void setCIUDAD(String CIUDAD) {
        InfoClienteModelo.CIUDAD = CIUDAD;
    }

    public static void setCOLONIA(String COLONIA) {
        InfoClienteModelo.COLONIA = COLONIA;
    }

    public static void setCompania(String compania) {
        Compania = compania;
    }

    public static void setNUMERO(String NUMERO) {
        InfoClienteModelo.NUMERO = NUMERO;
    }

    public static void setNOMBRE(String NOMBRE) {
        InfoClienteModelo.NOMBRE = NOMBRE;
    }
}
