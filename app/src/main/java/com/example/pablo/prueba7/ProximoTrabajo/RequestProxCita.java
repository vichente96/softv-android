package com.example.pablo.prueba7.ProximoTrabajo;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.pablo.prueba7.User.UserModel;
import com.example.pablo.prueba7.sampledata.Constants;
import com.example.pablo.prueba7.sampledata.RestApiAdapter;
import com.example.pablo.prueba7.sampledata.Service;
import com.google.gson.JsonObject;
import com.example.pablo.prueba7.Inicio;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestProxCita extends AppCompatActivity {

    public Service getProxService() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("clv_tecnico", clave_tecnico);
        jsonObject.put("clv_tecnico", 0);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", UserModel.Codigo)
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ROOT_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }


    public void getProximaCita() {
        try {
            final List<String> lista = new ArrayList();

            RestApiAdapter restApiAdapter = new RestApiAdapter();
            Service service = getProxService();
            Call<JsonObject> call = service.getDataProx();
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    JsonObject userJson = response.body().getAsJsonObject("GetDameSiguienteCitaResult");
                    Log.d("response4", userJson.get("Calle").toString());
                    ProximaCitaModel user = new ProximaCitaModel(
                            userJson.get("Calle").getAsString(),
                            userJson.get("Clave").getAsInt(),
                            userJson.get("Colonia").getAsString(),
                            userJson.get("Contrato").getAsString(),
                            userJson.get("Hora").getAsString(),
                            userJson.get("NUMERO").getAsString(),
                            userJson.get("Tipo").getAsString()
                    );
                    Inicio.trabajo.setText(user.Tipo +" Contrato: "+user.Contrato+ " Hora: "+user.Hora);
                    Inicio.direccion.setText("Colonia: " + user.Colonia+" Calle: "+user.Calle);



                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "No existe conexion", Toast.LENGTH_LONG).show();
                }
            });
        }catch (Throwable e){
            Toast.makeText(getApplicationContext(), "No existe conexion", Toast.LENGTH_LONG).show();
        }
    }


    private void result (UserModel user){

    }
}
