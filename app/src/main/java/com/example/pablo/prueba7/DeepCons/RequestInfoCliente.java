package com.example.pablo.prueba7.DeepCons;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.example.pablo.prueba7.MainActivity;
import com.example.pablo.prueba7.User.UserModel;
import com.example.pablo.prueba7.sampledata.Constants;
import com.example.pablo.prueba7.sampledata.RestApiAdapter;
import com.example.pablo.prueba7.sampledata.Service;
import com.google.gson.JsonObject;

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

public class RequestInfoCliente extends AppCompatActivity {
    public Service getInfoClienteService() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CONTRATO", DeepConsModel.Contrato);
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
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public void getInfoCliente() {
        final List<String> lista = new ArrayList();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = null;
        try {
            service = getInfoClienteService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JsonObject> call = service.getDataInfoCliente();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject userJson = response.body().getAsJsonObject("GetDeepBUSCLIPORCONTRATO_OrdSerResult");
                InfoClienteModelo user = new InfoClienteModelo(
                        userJson.get("CALLE").getAsString(),
                        userJson.get("CIUDAD").getAsString(),
                        userJson.get("COLONIA").getAsString(),
                        userJson.get("Compania").getAsString(),
                        userJson.get("NOMBRE").getAsString(),
                        userJson.get("NUMERO").getAsString()
                );

                MainActivity.Empresa.setText(InfoClienteModelo.Compania);
                MainActivity.Direccion.setText(InfoClienteModelo.CALLE+" "+InfoClienteModelo.NUMERO+" "+InfoClienteModelo.COLONIA);
                MainActivity.Nombre.setText(InfoClienteModelo.NOMBRE);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No existe conexion", Toast.LENGTH_LONG).show();
            }
        });
    }
}
