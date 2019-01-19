package com.example.pablo.prueba7.sampledata;



import com.example.pablo.prueba7.Listas.Example;
import com.example.pablo.prueba7.Listas.Example1;
import com.example.pablo.prueba7.Listas.Example2;
import com.example.pablo.prueba7.Listas.Example3;
import com.example.pablo.prueba7.Listas.JSONApaTipDis;
import com.example.pablo.prueba7.Listas.JSONApaTipo;
import com.example.pablo.prueba7.Listas.JSONCAMDO;
import com.example.pablo.prueba7.Listas.JSONCLIAPA;
import com.example.pablo.prueba7.Listas.JSONResponseTecnico;
import com.example.pablo.prueba7.Listas.JSONStatusApa;
import com.example.pablo.prueba7.Listas.JSONTecSec;
import com.google.gson.JsonObject;

import org.json.JSONObject;

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
    @POST(Constants.URL_GET_ORDENES)
    Call<Example3> getDataTrabajos();
    @POST(Constants.URL_GET_TEC_SEC)
    Call<JSONTecSec> getDataTecSec();
    @POST(Constants.URL_GET_CLI_APA)
    Call<JSONCLIAPA> getDataCliApa();
    @GET(Constants.URL_GET_STATUS)
    Call<JSONStatusApa> getDataStatusApa();
    @POST(Constants.URL_GET_APA_TIPO)
    Call<JSONApaTipo> getDataApaTipo();
    @POST(Constants.URL_GET_APA_TIP_DIS)
    Call<JSONApaTipDis> getDataApaTipDis();
    @POST(Constants.URL_GET_DAT_CAMDO)
    Call<JSONCAMDO> getDataCAMDO();

}

