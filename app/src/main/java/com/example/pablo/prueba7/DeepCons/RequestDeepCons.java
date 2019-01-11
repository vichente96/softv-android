package com.example.pablo.prueba7.DeepCons;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.pablo.prueba7.InstalacionFragment;
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

public class RequestDeepCons extends AppCompatActivity {
    public Service getDeepConsService() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Clv_Orden", 345);
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

    public void getDeepCons() {
        final List<String> lista = new ArrayList();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = null;
        try {
            service = getDeepConsService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JsonObject> call = service.getDataDeepCons();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject userJson = response.body().getAsJsonObject("GetDeepConsultaOrdSerResult");
               // Log.d("response12", userJson.get("Obs").toString());
                DeepConsModel user = new DeepConsModel(
                        userJson.get("Contrato").getAsInt(),
                        userJson.get("ContratoCom").getAsString(),
                        userJson.get("STATUS").getAsString(),
                        userJson.get("NombreTecnico").getAsString(),
                        userJson.get("Obs").getAsString(),
                        userJson.get("Clv_Orden").getAsString()
                );
                MainActivity.NombreTec.setText(DeepConsModel.NombreTecnico);
                if(DeepConsModel.STATUS.equals("E")){
                    MainActivity.Status.setText("Ejecutada");

                }else if(DeepConsModel.STATUS.equals("P")){
                    MainActivity.Status.setText("Pendiente");

                }else if(DeepConsModel.STATUS.equals("V")){
                    MainActivity.Status.setText("En Visita");
                }
                MainActivity.Contrato.setText( String.valueOf(DeepConsModel.Contrato));
                InstalacionFragment.Obs.setText(String.valueOf(DeepConsModel.Obs));

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No existe conexion", Toast.LENGTH_LONG).show();
            }
        });
    }
}