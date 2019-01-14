package com.example.pablo.prueba7.Request;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.pablo.prueba7.Inicio;
import com.example.pablo.prueba7.InstalacionFragment;
import com.example.pablo.prueba7.Listas.Example;
import com.example.pablo.prueba7.Listas.Example1;
import com.example.pablo.prueba7.Listas.Example2;
import com.example.pablo.prueba7.Listas.Example3;
import com.example.pablo.prueba7.Listas.JSONResponseTecnico;
import com.example.pablo.prueba7.MainActivity;
import com.example.pablo.prueba7.Modelos.DeepConsModel;
import com.example.pablo.prueba7.Modelos.GetBUSCADetOrdSerListResult;
import com.example.pablo.prueba7.Modelos.Get_ClvTecnicoResult;
import com.example.pablo.prueba7.Modelos.GetDameListadoOrdenesAgendadasResult;
import com.example.pablo.prueba7.Modelos.GetdameSerDELCliresumenResult;
import com.example.pablo.prueba7.Modelos.InfoClienteModelo;
import com.example.pablo.prueba7.Modelos.OrdSer;
import com.example.pablo.prueba7.Modelos.ProximaCitaModel;
import com.example.pablo.prueba7.Modelos.Queja;
import com.example.pablo.prueba7.Modelos.TrabajosSer;
import com.example.pablo.prueba7.Modelos.UserModel;
import com.example.pablo.prueba7.Services.Services;
import com.example.pablo.prueba7.Trabajos;
import com.example.pablo.prueba7.sampledata.Service;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Arrays.asList;

public class Request extends AppCompatActivity {
    Services services = new Services();


    public static String clave_tecnico;

    public static boolean b = false;
    ArrayList<List<GetdameSerDELCliresumenResult>> dataclientes;
    ArrayList<List<Queja>> dataque;
    ArrayList<List<OrdSer>> dataord;
    ArrayList<List<GetDameListadoOrdenesAgendadasResult>> dataagenda;
    ArrayList<List<Get_ClvTecnicoResult>> datatec;
    ArrayList<List<GetBUSCADetOrdSerListResult>> dataTrabajos;

    ///////////////////Token///////////////////////////
    public void getReviews() {
        try {
            final List<String> lista = new ArrayList();

            Services restApiAdapter = new Services();
            Service service = restApiAdapter.getClientService();
            Call<JsonObject> call = service.getDataUser();
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    //Peticion de datos sobre el Json "LogOnResult"
                    JsonObject userJson = response.body().getAsJsonObject("LogOnResult");
                    Log.d("response2", userJson.get("Usuario").getAsString());
                    Log.d("response3", userJson.get("Token").getAsString());
                    //Introduccion de datos del request en el Modelo para poder usarlos
                    UserModel user = new UserModel(
                            userJson.get("Usuario").getAsString(),
                            userJson.get("Token").getAsString(),
                            userJson.get("Codigo").getAsString()
                    );

                    b = true;
                    try {
                        getClv_tecnico();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "No existe conexion", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Throwable e) {
            Toast.makeText(getApplicationContext(), "No existe conexion", Toast.LENGTH_LONG).show();
        }
    }


    //////////////////Clave Tecnico////////////////////////////


    public void getClv_tecnico() throws JSONException {
        Service service = services.getTecService();
        Call<JSONResponseTecnico> call = service.getDataTec();
        call.enqueue(new Callback<JSONResponseTecnico>() {
            @Override
            public void onResponse(Call<JSONResponseTecnico> call, Response<JSONResponseTecnico> response) {
                //Guardar Body del request en JSONResponseTecnico ya que lo regresa como una lista
                JSONResponseTecnico jsonResponse = response.body();
                //Pide datos sobre el Json Get_ClvTecnicoResult haciendo referencia al JsonResponse donde se guardo
                datatec = new ArrayList<List<Get_ClvTecnicoResult>>(asList(jsonResponse.Get_ClvTecnicoResult()));
                //Se crea un Iterator con la lista para que se pueda recorrer con la informacion
                Iterator<List<Get_ClvTecnicoResult>> iteData = datatec.iterator();
                while (iteData.hasNext()) {
                    List<Get_ClvTecnicoResult> data = (List<Get_ClvTecnicoResult>) iteData.next();
                    //Se recorre la lista y se guarla la informacion en el Modelo
                    for (int i = 0; i < data.size(); i++) {
                        Log.d("response9", data.get(i).clv_tecnico);
                    }
                    clave_tecnico = data.get(0).clv_tecnico;
                    services.clave = Integer.parseInt(data.get(0).clv_tecnico);
                }

                getProximaCita();

                try {
                    getOrdenes();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JSONResponseTecnico> call, Throwable t) {

            }
        });
    }

    ///////////////////Proxima Cita///////////////////////////

    public void getProximaCita() {
        Service service = null;
        try {
            service = services.getProxService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                Inicio.trabajo.setText("Tipo de trabajo: " + user.Tipo + " Contrato: " + user.Contrato + " Hora: " + user.Hora);
                Inicio.direccion.setText("Colonia: " + user.Colonia + " Calle: " + user.Calle);


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No existe conexion", Toast.LENGTH_LONG).show();
            }
        });
    }


    ///////////////////Ordenes///////////////////////////


    public void getOrdenes() throws JSONException {
        Service service = services.getOrdSerService();
        Call<Example> call = service.getDataOrdenes();


        call.enqueue(new Callback<Example>() {


            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example jsonResponse = response.body();
                dataord = new ArrayList<List<OrdSer>>(asList(jsonResponse.getDameOrdenesQuejasTotalesResult.getOrdSer()));
                Iterator<List<OrdSer>> itData = dataord.iterator();
                while (itData.hasNext()) {
                    List<OrdSer> dat = (List<OrdSer>) itData.next();
                    for (int i = 0; i < dat.size(); i++) {
                        Log.d("response9", dat.get(i).getStatus());
                        Log.d("response10", String.valueOf(dat.get(i).getTotal()));
                    }
                    Inicio.OE = dat.get(0).getTotal();
                    Inicio.OP = dat.get(1).getTotal();
                    Inicio.OV = dat.get(2).getTotal();
                    Inicio.Grafica();
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


    //////////////////Quejas////////////////////////////

    public void getQuejas() throws JSONException {
        Service service = services.getOrdSerService();
        Call<Example> call = service.getDataOrdenes();

        call.enqueue(new Callback<Example>() {


            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example jsonResponse = response.body();
                dataque = new ArrayList<List<Queja>>(asList(jsonResponse.getDameOrdenesQuejasTotalesResult.getQueja()));
                Iterator<List<Queja>> itData = dataque.iterator();
                while (itData.hasNext()) {
                    List<Queja> dat = (List<Queja>) itData.next();
                    for (int i = 0; i < dat.size(); i++) {
                        Log.d("response7", dat.get(i).getStatus());
                        Log.d("response8", String.valueOf(dat.get(i).getTotal()));
                    }
                    Inicio.RE = dat.get(0).getTotal();
                    Inicio.RP = dat.get(1).getTotal();
                    Inicio.REP = dat.get(2).getTotal();
                    Inicio.RV = dat.get(3).getTotal();
                }
                Inicio.Grafica();
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }


        });
    }

    /////////////////Lista de Ordenes/////////////////////////////


    public void getListOrd() throws JSONException {

        Service service = services.getListOrdService();

        Call<Example1> call = service.getDataListOrd();
        call.enqueue(new Callback<Example1>() {


            @Override
            public void onResponse(Call<Example1> call, Response<Example1> response) {
                Example1 jsonResponse = response.body();
                dataagenda = new ArrayList<List<GetDameListadoOrdenesAgendadasResult>>(asList(jsonResponse.getGetDameListadoOrdenesAgendadasResult()));
                Iterator<List<GetDameListadoOrdenesAgendadasResult>> itData = dataagenda.iterator();
                while (itData.hasNext()) {
                    List<GetDameListadoOrdenesAgendadasResult> dat = (List<GetDameListadoOrdenesAgendadasResult>) itData.next();
                    for (int i = 0; i < dat.size(); i++) {
                        Log.d("Clave de orden", String.valueOf(dat.get(i).getClvOrden()));
                        Log.d("Contrato", dat.get(i).getContrato());
                        Log.d("Nombre", dat.get(i).getNombre());
                        Log.d("Status", dat.get(i).getStatus());
                    }
                }

            }

            @Override
            public void onFailure(Call<Example1> call, Throwable t) {

            }


        });

    }
 ///////////////////Consuta pantalla ordenes///////////////////////////

    public void getDeepCons()throws JSONException {

        Service service = services.getDeepConsService();
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
                if (DeepConsModel.STATUS.equals("E")) {
                    MainActivity.Status.setText("Ejecutada");

                } else if (DeepConsModel.STATUS.equals("P")) {
                    MainActivity.Status.setText("Pendiente");

                } else if (DeepConsModel.STATUS.equals("V")) {
                    MainActivity.Status.setText("En Visita");
                }
                MainActivity.Contrato.setText(String.valueOf(DeepConsModel.Contrato));
                InstalacionFragment.Obs.setText(String.valueOf(DeepConsModel.Obs));

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No existe conexion", Toast.LENGTH_LONG).show();
            }
        });
    }

    /////////////////Informacion del Cliente/////////////////////////////

    public void getInfoCliente() throws JSONException {

        Service service = services.getInfoClienteService();
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
                MainActivity.Direccion.setText(InfoClienteModelo.CALLE + " " + InfoClienteModelo.NUMERO + " " + InfoClienteModelo.COLONIA);
                MainActivity.Nombre.setText(InfoClienteModelo.NOMBRE);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No existe conexion", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void getServicios() throws JSONException {

        Service service = services.getServiciosService();
        Call<Example2> call = service.getDataServicios();
        call.enqueue(new Callback<Example2>() {


            @Override
            public void onResponse(Call<Example2> call, Response<Example2> response) {
                Example2 jsonResponse = response.body();
                dataclientes = new ArrayList<List<GetdameSerDELCliresumenResult>>(asList(jsonResponse.getdameSerDELCliresumenResult()));
                Iterator<List<GetdameSerDELCliresumenResult>> itData = dataclientes.iterator();
                while (itData.hasNext()) {
                    List<GetdameSerDELCliresumenResult> dat = (List<GetdameSerDELCliresumenResult>) itData.next();
                    for (int i = 0; i < dat.size(); i++) {
                        Log.d("Resumen", dat.get(i).getResumen());
                    }
                    MainActivity.InfoServicios.setText("    " + dat.get(0).getResumen() + '\n' + "    " + dat.get(1).getResumen() + '\n' +
                            dat.get(2).getResumen() + '\n' + dat.get(3).getResumen() + '\n' + dat.get(4).getResumen());
                }
            }

            @Override
            public void onFailure(Call<Example2> call, Throwable t) {

            }


        });
    }
/////////////////////////////informacion trabajos//////////////////////////////


    public void getTrabajos() throws JSONException {
        Service service = services.getTrabajoService();
        Call<Example3> call = service.getDataTrabajos();
        call.enqueue(new Callback<Example3>() {
            @Override
            public void onResponse(Call<Example3> call, Response<Example3> response) {
                Example3 jsonResponse = response.body();
                dataTrabajos =  new ArrayList<List<GetBUSCADetOrdSerListResult>>(asList(jsonResponse.getGetBUSCADetOrdSerListResult()));
                Iterator<List<GetBUSCADetOrdSerListResult>> itData = dataTrabajos.iterator();
                while (itData.hasNext()) {
                    List<GetBUSCADetOrdSerListResult> dat = (List<GetBUSCADetOrdSerListResult>) itData.next();
                    for (int i = 0; i < dat.size(); i++) {
                        Log.d("response11", dat.get(i).getDescripcion());

                    }
                    Trabajos.trabajo1.setText("    "+ dat.get(0).getDescripcion());
                }

            }

            @Override
            public void onFailure(Call<Example3> call, Throwable t) {

            }

        });

    }

}