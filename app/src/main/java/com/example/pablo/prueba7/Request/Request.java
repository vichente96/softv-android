package com.example.pablo.prueba7.Request;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.example.pablo.prueba7.CambioAparato;
import com.example.pablo.prueba7.CambioDom;
import com.example.pablo.prueba7.Inicio;
import com.example.pablo.prueba7.InstalacionFragment;
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
import com.example.pablo.prueba7.MainActivity;
import com.example.pablo.prueba7.Modelos.GetDameDatosCAMDOResult;
import com.example.pablo.prueba7.Modelos.DeepConsModel;
import com.example.pablo.prueba7.Modelos.GetBUSCADetOrdSerListResult;
import com.example.pablo.prueba7.Modelos.GetListAparatosDisponiblesByIdArticuloResult;
import com.example.pablo.prueba7.Modelos.GetListClienteAparatosResult;
import com.example.pablo.prueba7.Modelos.GetListTipoAparatosByIdArticuloResult;
import com.example.pablo.prueba7.Modelos.GetMuestraRelOrdenesTecnicosListResult;
import com.example.pablo.prueba7.Modelos.GetSP_StatusAparatosListResult;
import com.example.pablo.prueba7.Modelos.Get_ClvTecnicoResult;
import com.example.pablo.prueba7.Modelos.GetDameListadoOrdenesAgendadasResult;
import com.example.pablo.prueba7.Modelos.GetdameSerDELCliresumenResult;
import com.example.pablo.prueba7.Modelos.InfoClienteModelo;
import com.example.pablo.prueba7.Modelos.OrdSer;
import com.example.pablo.prueba7.Modelos.ProximaCitaModel;
import com.example.pablo.prueba7.Modelos.Queja;
import com.example.pablo.prueba7.Modelos.UserModel;
import com.example.pablo.prueba7.Services.Services;
import com.example.pablo.prueba7.sampledata.Service;
import com.google.gson.JsonObject;
import com.example.pablo.prueba7.Listas.Array;
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
Array array = new Array();
CambioDom c = new CambioDom();
    public static String clave_tecnico;
String a="Seleccione tecnico secundario";

    public static boolean b = false;

    public static String datos[];


    ///////////////////Token///////////////////////////
    public void getReviews() {

            final List<String> lista = new ArrayList();

            Services restApiAdapter = new Services();
            Service service = restApiAdapter.getClientService();
            Call<JsonObject> call = service.getDataUser();
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    //Peticion de datos sobre el Json "LogOnResult"
                    try {
                        JsonObject userJson = response.body().getAsJsonObject("LogOnResult");
                        Log.d("response2", userJson.get("Usuario").getAsString());
                        Log.d("response3", userJson.get("Token").getAsString());
                        //Introduccion de datos del request en el Modelo para poder usarlos
                        UserModel user = new UserModel(
                                userJson.get("Usuario").getAsString(),
                                userJson.get("Token").getAsString(),
                                userJson.get("Codigo").getAsString()
                        );
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Error en el Login(request)",Toast.LENGTH_LONG).show();
                    }



                    b = true;
                        getClv_tecnico();


                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
    }
    //////////////////Clave Tecnico////////////////////////////
    public void getClv_tecnico() {
        Service service = null;
        try {
            service = services.getTecService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONResponseTecnico> call = service.getDataTec();
        call.enqueue(new Callback<JSONResponseTecnico>() {
            @Override
            public void onResponse(Call<JSONResponseTecnico> call, Response<JSONResponseTecnico> response) {
                //Guardar Body del request en JSONResponseTecnico ya que lo regresa como una lista
                JSONResponseTecnico jsonResponse = response.body();
                //Pide datos sobre el Json Get_ClvTecnicoResult haciendo referencia al JsonResponse donde se guardo
                array.datatec = new ArrayList<List<Get_ClvTecnicoResult>>(asList(jsonResponse.Get_ClvTecnicoResult()));
                //Se crea un Iterator con la lista para que se pueda recorrer con la informacion
                Iterator<List<Get_ClvTecnicoResult>> iteData = array.datatec.iterator();
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
                    getOrdenes();


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

            }
        });
    }
    ///////////////////Ordenes///////////////////////////
    public void getOrdenes()  {
        Service service = null;
        try {
            service = services.getOrdSerService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<Example> call = service.getDataOrdenes();


        call.enqueue(new Callback<Example>() {


            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example jsonResponse = response.body();
                array.dataord = new ArrayList<List<OrdSer>>(asList(jsonResponse.getDameOrdenesQuejasTotalesResult.getOrdSer()));
                Iterator<List<OrdSer>> itData = array.dataord.iterator();
                while (itData.hasNext()) {
                    List<OrdSer> dat = (List<OrdSer>) itData.next();
                    for (int i = 0; i < dat.size(); i++) {
                        Log.d("response9", dat.get(i).getStatus());
                        Log.d("response10", String.valueOf(dat.get(i).getTotal()));
                    }
                    try{
                        Inicio.OE = dat.get(0).getTotal();
                    }catch (Exception e){
                        Inicio.OE = 0;
                    }
                    try{
                        Inicio.OP = dat.get(1).getTotal();
                    }catch (Exception e){
                        Inicio.OP = 0;
                    }
                    try{
                        Inicio.OV = dat.get(2).getTotal();
                    }catch (Exception e){
                        Inicio.OV = 0;
                    }
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }


        });

            getQuejas();

    }
    //////////////////Quejas////////////////////////////
    public void getQuejas()  {
        Service service = null;
        try {
            service = services.getOrdSerService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<Example> call = service.getDataOrdenes();

        call.enqueue(new Callback<Example>() {


            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example jsonResponse = response.body();
                array.dataque = new ArrayList<List<Queja>>(asList(jsonResponse.getDameOrdenesQuejasTotalesResult.getQueja()));
                Iterator<List<Queja>> itData = array.dataque.iterator();
                while (itData.hasNext()) {
                    List<Queja> dat = (List<Queja>) itData.next();
                    for (int i = 0; i < dat.size(); i++) {
                        Log.d("response7", dat.get(i).getStatus());
                        Log.d("response8", String.valueOf(dat.get(i).getTotal()));
                    }
                    try{
                        Inicio.RE = dat.get(0).getTotal();
                    }catch (Exception e){
                        Inicio.RE = 0;
                    }
                    try{
                        Inicio.RP = dat.get(1).getTotal();
                    }catch (Exception e){
                        Inicio.RP = 0;
                    }
                    try{
                        Inicio.REP = dat.get(2).getTotal();
                    }catch (Exception e){
                        Inicio.REP = 0;
                    }
                    try{
                        Inicio.RV = dat.get(3).getTotal();
                    }catch (Exception e){
                        Inicio.RV = 0;
                    }
                }
                Inicio.Grafica();
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }


        });
    }
    /////////////////Lista de Ordenes/////////////////////////////
    public void getListOrd()  {

        Service service = null;
        try {
            service = services.getListOrdService();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<Example1> call = service.getDataListOrd();
        call.enqueue(new Callback<Example1>() {


            @Override
            public void onResponse(Call<Example1> call, Response<Example1> response) {
                Example1 jsonResponse = response.body();
                array.dataagenda = new ArrayList<List<GetDameListadoOrdenesAgendadasResult>>(asList(jsonResponse.getGetDameListadoOrdenesAgendadasResult()));
                Iterator<List<GetDameListadoOrdenesAgendadasResult>> itData = array.dataagenda.iterator();
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
    public void getDeepCons() {

        Service service = null;
        try {
            service = services.getDeepConsService();
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

            }
        });
    }
    /////////////////Informacion del Cliente/////////////////////////////
    public void getInfoCliente()  {

        Service service = null;
        try {
            service = services.getInfoClienteService();
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
                MainActivity.Direccion.setText(InfoClienteModelo.CALLE + " " + InfoClienteModelo.NUMERO + " " + InfoClienteModelo.COLONIA);
                MainActivity.Nombre.setText(InfoClienteModelo.NOMBRE);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
    /////////////////ServiciosdelCliente/////////////////////////////
    public void getServicios()  {

        Service service = null;
        try {
            service = services.getServiciosService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<Example2> call = service.getDataServicios();
        call.enqueue(new Callback<Example2>() {


            @Override
            public void onResponse(Call<Example2> call, Response<Example2> response) {
                Example2 jsonResponse = response.body();
                array.dataclientes = new ArrayList<List<GetdameSerDELCliresumenResult>>(asList(jsonResponse.getdameSerDELCliresumenResult()));
                Iterator<List<GetdameSerDELCliresumenResult>> itData = array.dataclientes.iterator();
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
    public void getTrabajos()  {
        Service service = null;
        try {
            service = services.getTrabajoService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<Example3> call = service.getDataTrabajos();
        call.enqueue(new Callback<Example3>() {
            @Override
            public void onResponse(Call<Example3> call, Response<Example3> response) {
                Example3 jsonResponse = response.body();
                array.dataTrabajos =  new ArrayList<List<GetBUSCADetOrdSerListResult>>(asList(jsonResponse.getGetBUSCADetOrdSerListResult()));
                Iterator<List<GetBUSCADetOrdSerListResult>> itData = array.dataTrabajos.iterator();
                while (itData.hasNext()) {
                    List<GetBUSCADetOrdSerListResult> dat = (List<GetBUSCADetOrdSerListResult>) itData.next();
                    for (int i = 0; i < dat.size(); i++) {
                        Log.d("response11", dat.get(i).getDescripcion());

                    }
                }

            }

            @Override
            public void onFailure(Call<Example3> call, Throwable t) {

            }

        });

    }
////TecnicoSecundario////
    public void getTecSec(final Context context){

        Service service = null;
        try {
            service = services.getTecSecService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONTecSec> call = service.getDataTecSec();
        call.enqueue(new Callback<JSONTecSec>() {
            @Override
            public void onResponse(Call<JSONTecSec> call, Response<JSONTecSec> response) {
                JSONTecSec jsonResponse = response.body();
                array.dataTecSec = new ArrayList<List<GetMuestraRelOrdenesTecnicosListResult>>(asList(jsonResponse.GetMuestraRelOrdenesTecnicosListResult()));
                Iterator<List<GetMuestraRelOrdenesTecnicosListResult>> itdata = array.dataTecSec.iterator();
                while (itdata.hasNext()){
                    List<GetMuestraRelOrdenesTecnicosListResult> dat = itdata.next();
                    datos = new String[dat.size()+1];
                    int j=1;
                    datos[0] = a;
                    for(int i=0; i< dat.size(); i++){
                        Log.d("responsetecsec", dat.get(i).getNOMBRE());
                        datos[j] = dat.get(i).getNOMBRE();
                        j=j+1;
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
                    InstalacionFragment.TecSec.setAdapter(adapter);
                    InstalacionFragment.Obs.setText(String.valueOf(DeepConsModel.Obs));
                }




            }

            @Override
            public void onFailure(Call<JSONTecSec> call, Throwable t) {

            }
        });
    }
////ClientesAparato////
    public void getCliApa(final Context context) {

        Service service = null;
        try {
            service = services.getCliApaService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONCLIAPA> call = service.getDataCliApa();
        call.enqueue(new Callback<JSONCLIAPA>() {


            @Override
            public void onResponse(Call<JSONCLIAPA> call, Response<JSONCLIAPA> response) {
                JSONCLIAPA jsonResponse = response.body();
                array.dataCliApa = new ArrayList<List<GetListClienteAparatosResult>>(asList(jsonResponse.GetListClienteAparatosResult()));
                Iterator<List<GetListClienteAparatosResult>> itdata = array.dataCliApa.iterator();
                while (itdata.hasNext()){
                    List<GetListClienteAparatosResult> dat = itdata.next();
                    String datos[] = new String[dat.size()];
                    for (int i=0; i<dat.size(); i++){
                        Log.d("responseAparatosCliente", String.valueOf(dat.get(i).Descripcion));
                        datos[i] = dat.get(i).getMac();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
                    CambioAparato.aparato.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<JSONCLIAPA> call, Throwable t) {

            }
        });
    }
    ////Status Aparato////
    public void getStatusApa(final Context context) {
        Service service = services.getStatusApa();
        Call<JSONStatusApa> call = service.getDataStatusApa();
        call.enqueue(new Callback<JSONStatusApa>() {
            @Override
            public void onResponse(Call<JSONStatusApa> call, Response<JSONStatusApa> response) {
                JSONStatusApa jsonResponse = response.body();
                array.dataStaApa = new ArrayList<List<GetSP_StatusAparatosListResult>>(asList(jsonResponse.GetSP_StatusAparatosListResult()));
                Iterator<List<GetSP_StatusAparatosListResult>> itdata = array.dataStaApa.iterator();
                while (itdata.hasNext()){
                    List<GetSP_StatusAparatosListResult> dat = itdata.next();
                    String datos[] = new String[dat.size()];
                    for(int i=0; i< dat.size(); i++){
                        Log.d("responseStatus", dat.get(i).Concepto);
                        datos[i] = dat.get(i).getConcepto();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
                    CambioAparato.estado.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<JSONStatusApa> call, Throwable t) {

            }

        });
    }
    ////TipoAparato////
    public void getApaTipo(final Context context) {
        Service service = null;
        try {
            service = services.getApaTipoService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONApaTipo> call = service.getDataApaTipo();
        call.enqueue(new Callback<JSONApaTipo>() {


            @Override
            public void onResponse(Call<JSONApaTipo> call, Response<JSONApaTipo> response) {

                JSONApaTipo jsonResponse = response.body();
                array.dataApaTipo = new ArrayList<List<GetListTipoAparatosByIdArticuloResult>>(asList(jsonResponse.GetListTipoAparatosByIdArticuloResult()));
                Iterator<List<GetListTipoAparatosByIdArticuloResult>> itdata = array.dataApaTipo.iterator();
                while (itdata.hasNext()){
                    List<GetListTipoAparatosByIdArticuloResult> dat = itdata.next();
                    String datos[] = new String[dat.size()];
                    for (int i=0; i<dat.size(); i++){
                        Log.d("responseIdArticulo", String.valueOf(dat.get(i).IdArticulo));
                        datos[i] = dat.get(i).getNombre();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
                    CambioAparato.tipoAparato.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<JSONApaTipo> call, Throwable t) {

            }
        });
    }
    ////AparatoDisponible////
    public void getApaTipDis(final Context context) {

        Service service = null;
        try {
            service = services.getApaTipDisService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONApaTipDis> call = service.getDataApaTipDis();
        call.enqueue(new Callback<JSONApaTipDis>() {


            @Override
            public void onResponse(Call<JSONApaTipDis> call, Response<JSONApaTipDis> response) {
                JSONApaTipDis jsonResponse = response.body();
                array.dataApaTipDis = new ArrayList<List<GetListAparatosDisponiblesByIdArticuloResult>>(asList(jsonResponse.GetListAparatosDisponiblesByIdArticuloResult()));
                Iterator<List<GetListAparatosDisponiblesByIdArticuloResult>> itdata = array.dataApaTipDis.iterator();
                while (itdata.hasNext()){
                    List<GetListAparatosDisponiblesByIdArticuloResult> dat = itdata.next();
                    String datos[] = new String[dat.size()];
                    for (int i=0; i<dat.size(); i++){
                        Log.d("responseClv_aparato", String.valueOf(dat.get(i).Clv_Aparato));
                        datos[i] = dat.get(i).getDescripcion();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
                    CambioAparato.aparatoAsignar.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<JSONApaTipDis> call, Throwable t) {

            }
        });
    }
    public void getCAMDO() {
        Service service = null;
        try {
            service = services.getCAMODOService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONCAMDO> call = service.getDataCAMDO();
        call.enqueue(new Callback<JSONCAMDO>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<JSONCAMDO> call, Response<JSONCAMDO> response) {
                JSONCAMDO jsonResponse = response.body();
                array.dataCAMDO = new ArrayList<List<GetDameDatosCAMDOResult>>(asList(jsonResponse.getDameDatosCAMDOResult()));
                Iterator<List<GetDameDatosCAMDOResult>> itdata = array.dataCAMDO.iterator();
                while (itdata.hasNext()){
                    List<GetDameDatosCAMDOResult> dat = itdata.next();
                    String datos[] = new String[dat.size()];
                    for (int i=0; i<dat.size(); i++){
                        Log.d("casa", dat.get(i).getCasa());
                    }
                    c.Ciudad.setText(dat.get(0).getCiudad());
                    c.Localidad.setText(dat.get(0).getLocalidad());
                    c.Colonia.setText(dat.get(0).getColonia());
                    c.Calle.setText(dat.get(0).getCalle());
                    c.Numero.setText(String.valueOf(dat.get(0).getNUMERO()));
                    c.Numero_i.setText(dat.get(0).getNum_int());
                    c.Telefono.setText(dat.get(0).getTELEFONO());
                    c.CalleN.setText(dat.get(0).getCalleNorte());
                    c.CalleS.setText(dat.get(0).getCalleSur());
                    c.CallleE.setText(dat.get(0).getCalleEste());
                    c.CalleO.setText(dat.get(0).getCalleOeste());

                    if(dat.get(0).getCasa().equals("N")){
                        c.CasaNorte.setVisibility(View.VISIBLE);
                    }if(dat.get(0).getCasa().equals("S")){
                        c.CasaSur.setVisibility(View.VISIBLE);
                    }if(dat.get(0).getCasa().equals("E")) {
                        c.CasaEste.setVisibility(View.VISIBLE);
                    }if(dat.get(0).getCasa().equals("O")){
                        c.CasaOeste.setVisibility(View.VISIBLE);
                    }


                }
            }

            @Override
            public void onFailure(Call<JSONCAMDO> call, Throwable t) {

            }
        });
    }

}
