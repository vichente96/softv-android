package com.example.pablo.prueba7.sampledata;



import com.example.pablo.prueba7.Listas.Example;
import com.example.pablo.prueba7.Listas.Example1;
import com.example.pablo.prueba7.Listas.Example2;
import com.example.pablo.prueba7.Listas.JSONResponseTecnico;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface Service {

    @GET(Constants.URL_GET_USER)
    Call<JsonObject> getDataUser();
    @POST(Constants.URL_GET_TECNICO)
    Call<JSONResponseTecnico> getDataTec();
    @POST(Constants.URL_GET_PROX)
    Call<JsonObject> getDataProx();
    @POST(Constants.URL_GET_ORDQUE)
    Call<Example> getDataOrdenes();
    @POST(Constants.URL_GET_LIST_ORD)
    Call<Example1> getDataListOrd();
    @POST(Constants.URL_GET_DEEP_CONS)
    Call<JsonObject> getDataDeepCons();
    @POST(Constants.URL_GET_INFO_CLIENTE)
    Call<JsonObject> getDataInfoCliente();
    @POST(Constants.URL_GET_SERVICIOS)
    Call<Example2> getDataServicios();


}

