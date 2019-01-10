package com.example.pablo.prueba7.User;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;



import com.example.pablo.prueba7.Request.RequestTecnico;
import com.example.pablo.prueba7.sampledata.RestApiAdapter;
import com.example.pablo.prueba7.sampledata.Service;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserController extends AppCompatActivity{
    public static boolean b;
RequestTecnico requestTecnico= new  RequestTecnico();


    public void getReviews() {
        try {
            final List<String> lista = new ArrayList();

            RestApiAdapter restApiAdapter = new RestApiAdapter();
            Service service = restApiAdapter.getClientService();
            Call<JsonObject> call = service.getDataUser();
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.code() == 400 ) {
                        Log.e("errr","onResponse - Status : " + response.code());

                        }
                    JsonObject userJson = response.body().getAsJsonObject("LogOnResult");
                    Log.d("response2", userJson.get("Usuario").getAsString());
                    Log.d("response3", userJson.get("Token").getAsString());
                    UserModel user = new UserModel(
                            userJson.get("Usuario").getAsString(),
                            userJson.get("Token").getAsString(),
                            userJson.get("TipoUser").getAsString(),
                            userJson.get("IdUsuario").getAsString(),
                            userJson.get("Codigo").getAsString()
                    );

                    b = true;
                    requestTecnico.getClv_tecnico();


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
