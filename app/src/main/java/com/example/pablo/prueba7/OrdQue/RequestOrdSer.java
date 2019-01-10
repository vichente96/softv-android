package com.example.pablo.prueba7.OrdQue;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.pablo.prueba7.Inicio;
import com.example.pablo.prueba7.User.UserModel;
import com.example.pablo.prueba7.sampledata.Constants;
import com.example.pablo.prueba7.sampledata.RestApiAdapter;
import com.example.pablo.prueba7.sampledata.Service;

import org.json.JSONException;
import org.json.JSONObject;

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


public class RequestOrdSer extends AppCompatActivity {
    public static int clave;
    ArrayList<List<OrdSer>> dataord;
    ArrayList<List<Queja>> dataque;


    public Service getOrdSerService() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clv_tecnico", clave);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", UserModel.Codigo)
                        .addHeader("Content-Type", "application/json")
                        .post(body).build();


                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.ROOT_URL)
                .client(client).addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);

    }

    public void getOrdenes() throws JSONException {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = getOrdSerService();

        Call<Example> call = service.getDataOrdenes();
        call.enqueue(new Callback<Example>() {


            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example jsonResponse = response.body();
                dataord = new ArrayList<List<OrdSer>>( asList(jsonResponse.getDameOrdenesQuejasTotalesResult.getOrdSer()));
                Iterator<List<OrdSer>> itData = dataord.iterator();
                while (itData.hasNext()){
                    List<OrdSer> dat = (List<OrdSer>) itData.next();
                    for (int i=0; i<dat.size(); i++) {
                        Log.d("response9", dat.get(i).getStatus());
                        Log.d("response10", String.valueOf(dat.get(i).getTotal()));
                    }
                  Inicio.OE =dat.get(0).getTotal();
                    Inicio.OP=dat.get(1).getTotal();
                    Inicio.OV=dat.get(2).getTotal();
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }


        });

        try {
            getQuejas();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getQuejas() throws JSONException {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = getOrdSerService();

        Call<Example> call = service.getDataOrdenes();
        call.enqueue(new Callback<Example>() {


            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example jsonResponse = response.body();
                dataque = new ArrayList<List<Queja>>( asList(jsonResponse.getDameOrdenesQuejasTotalesResult.getQueja()));
                Iterator<List<Queja>> itData = dataque.iterator();
                while (itData.hasNext()){
                    List<Queja> dat = (List<Queja>) itData.next();
                    for (int i=0; i<dat.size(); i++) {
                        Log.d("response7", dat.get(i).getStatus());
                        Log.d("response8", String.valueOf(dat.get(i).getTotal()));
                    }
                    Inicio.RE=dat.get(0).getTotal();
                    Inicio.RP=dat.get(1).getTotal();
                    Inicio.REP=dat.get(2).getTotal();
                    Inicio.RV=dat.get(3).getTotal();
                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }


        });
    }

}
