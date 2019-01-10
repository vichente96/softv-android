package com.example.pablo.prueba7.User;

public class UserModel {
    private String Usuario;
    public static String Token;
    private String TipoUser;
    private String IdUsuario;
    public static String Codigo;


    public UserModel(String Usuario, String Token, String TipoUser, String IdUsuario, String codigo) {
        this.Usuario = Usuario;
        this.Token = Token;
        this.TipoUser = TipoUser;
        this.IdUsuario = IdUsuario;
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

    public String getTipoUser() {
        return TipoUser;
    }

    public void setTipoUser(String TipoUser) {
        this.TipoUser = TipoUser;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }
}