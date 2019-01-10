package com.example.pablo.prueba7.ListOrd;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.pablo.prueba7.Inicio;
import com.example.pablo.prueba7.User.UserModel;
import com.example.pablo.prueba7.sampledata.Constants;
import com.example.pablo.prueba7.sampledata.RestApiAdapter;
import com.example.pablo.prueba7.sampledata.Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import static java.util.Arrays.*;


public class RequestListOrd extends AppCompatActivity {
    public static int clave;
    ArrayList<List<GetDameListadoOrdenesAgendadasResult>> dataord;



    public Service getListOrdService() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JsonArray jsonObject1 = new JsonArray();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject.put("clv_tecnico",20041);
        jsonObject.put("op",1);
        jsonObject.put("clv_orden",0);
        jsonObject.put("contratoCom","");
        jsonObject2.put("ObjLista",jsonObject);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject2));

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IlNJU1RFIiwibmJmIjoxNTQ2OTg2OTkxLCJleHAiOjE1NDgxODY5OTEsImlhdCI6MTU0Njk4Njk5MX0.kxQQTP-sDPNl9kf6sORiOUUWMYgVQJh1COeU-3v5JBs")
                        .addHeader("Content-Type", "application/json")
                        .post(body).build();


                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.NEW_URL)
                .client(client).addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);

    }

    public void getListOrd() throws JSONException {

        Service service = getListOrdService();

        Call<Example1> call = service.getDataListOrd();
        call.enqueue(new Callback<Example1>() {


            @Override
            public void onResponse(Call<Example1> call, Response<Example1> response) {
                Example1 jsonResponse = response.body();
                dataord = new ArrayList<List<GetDameListadoOrdenesAgendadasResult>>(asList(jsonResponse.getGetDameListadoOrdenesAgendadasResult()));
                Iterator<List<GetDameListadoOrdenesAgendadasResult>> itData = dataord.iterator();
                while (itData.hasNext()){
                    List<GetDameListadoOrdenesAgendadasResult> dat = (List<GetDameListadoOrdenesAgendadasResult>) itData.next();
                    for (int i=0; i<dat.size(); i++) {
                        Log.d("Clave de orden", String.valueOf(dat.get(i).getClvOrden()));
                        Log.d("Contrato", dat.get(i).getContrato());
                        Log.d("Status", dat.get(i).getStatus());
                    }
                }

            }

            @Override
            public void onFailure(Call<Example1> call, Throwable t) {

            }


        });

    }



}
