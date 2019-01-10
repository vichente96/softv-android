package com.example.pablo.prueba7.Request;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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

import com.example.pablo.prueba7.OrdQue.RequestOrdSer;
import com.example.pablo.prueba7.ProximoTrabajo.RequestProxCita;
import com.example.pablo.prueba7.User.Login;
import com.example.pablo.prueba7.User.UserModel;
import com.example.pablo.prueba7.sampledata.Constants;
import com.example.pablo.prueba7.sampledata.RestApiAdapter;
import com.example.pablo.prueba7.sampledata.Service;

import org.json.JSONException;
import org.json.JSONObject;


public class RequestTecnico extends AppCompatActivity {
   public static String clave_tecnico;

    RequestProxCita requestProxCita = new RequestProxCita();
    RequestOrdSer requestOrdSer = new RequestOrdSer();

    public ArrayList<TecnicoModel> data;
    public Service getTecService() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Clv_Usuario",Login.cvl_usuario);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
try {
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

}catch (Exception e){
    getTecService();
}


        return null;
    }

    public void getClv_tecnico() {

    RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = null;
        try {
            service = getTecService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONResponseTecnico> call = service.getDataTec();
            call.enqueue(new Callback<JSONResponseTecnico>() {
        @Override
        public void onResponse(Call<JSONResponseTecnico> call, Response<JSONResponseTecnico> response) {
            JSONResponseTecnico jsonResponse = response.body();
            data = new ArrayList<>(Arrays.asList(jsonResponse.Get_ClvTecnicoResult()));
            Iterator<TecnicoModel> iteData = data.iterator();
            while (iteData.hasNext()){
                TecnicoModel dat = iteData.next();
                Log.d("response5", dat.getClv_tecnico());
                clave_tecnico=dat.getClv_tecnico();
                requestOrdSer.clave = Integer.parseInt(dat.clv_tecnico);
            }
            requestProxCita.getProximaCita();
            try {
                requestOrdSer.getOrdenes();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        @Override
        public void onFailure(Call<JSONResponseTecnico> call, Throwable t) {
            getClv_tecnico();

        }
});


    }
}



