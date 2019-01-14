package com.example.pablo.prueba7.Modelos;

public class UserModel {
    private String Usuario;
    public static String Token;
    public static String Codigo;


    public UserModel(String Usuario, String Token, String codigo) {
        this.Usuario = Usuario;
        this.Token = Token;
        this.Codigo=codigo;

    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public static String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) { Codigo = codigo; }
}