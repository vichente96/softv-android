package com.example.pablo.prueba7.Servicios;

import android.util.Log;

import com.example.pablo.prueba7.DeepCons.DeepConsModel;
import com.example.pablo.prueba7.MainActivity;
import com.example.pablo.prueba7.User.UserModel;
import com.example.pablo.prueba7.sampledata.Constants;
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

import static java.util.Arrays.asList;

public class RequestServicios {
    ArrayList<List<GetdameSerDELCliresumenResult>> dataord;



    public Service getServiciosService() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Contrato",DeepConsModel.Contrato);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject));

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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.NEW_URL)
                .client(client).addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);

    }

    public void getServicios()  {

        Service service = null;
        try {
            service = getServiciosService();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<Example2> call = service.getDataServicios();
        call.enqueue(new Callback<Example2>() {


            @Override
            public void onResponse(Call<Example2> call, Response<Example2> response) {
                Example2 jsonResponse = response.body();
                dataord = new ArrayList<List<GetdameSerDELCliresumenResult>>(asList(jsonResponse.getdameSerDELCliresumenResult()));
                Iterator<List<GetdameSerDELCliresumenResult>> itData = dataord.iterator();
                while (itData.hasNext()){
                    List<GetdameSerDELCliresumenResult> dat = (List<GetdameSerDELCliresumenResult>) itData.next();
                    for (int i=0; i<dat.size(); i++) {
                        Log.d("Resumen", dat.get(i).getResumen());
                    }
                    MainActivity.InfoServicios.setText("    "+dat.get(0).getResumen()+'\n'+"    "+dat.get(1).getResumen()+'\n'+
                            dat.get(2).getResumen()+'\n'+ dat.get(3).getResumen()+'\n'+dat.get(4).getResumen());
                }
            }

            @Override
            public void onFailure(Call<Example2> call, Throwable t) {

            }


        });

    }


}
