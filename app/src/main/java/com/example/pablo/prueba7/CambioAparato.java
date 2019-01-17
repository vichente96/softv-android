package com.example.pablo.prueba7;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.GetListClienteAparatosResult;
import com.example.pablo.prueba7.Modelos.GetListTipoAparatosByIdArticuloResult;
import com.example.pablo.prueba7.Request.Request;

import java.util.Iterator;
import java.util.List;

public class CambioAparato extends AppCompatActivity {

    public static Spinner aparato, estado,tipoAparato, aparatoAsignar;
    public static int idArticulo, contrato, idArticulo2;
    Request request = new Request();
    Array array = new Array();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_aparato);
aparato = findViewById(R.id.aparato);
        estado = findViewById(R.id.estadoaparato);
        tipoAparato = findViewById(R.id.tipo_aparato);
        aparatoAsignar = findViewById(R.id.aparatoAsignar);
        request.getCliApa(getApplicationContext());
        request.getStatusApa(getApplicationContext());


    aparato.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Iterator<List<GetListClienteAparatosResult>> itdata = array.dataCliApa.iterator();
                    List<GetListClienteAparatosResult> dat = itdata.next();
                    idArticulo = dat.get(position).getIdArticulo();
                    contrato = dat.get(position).getControNet();
                    request.getApaTipo(getApplicationContext());


                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            }
    );

tipoAparato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Iterator<List<GetListTipoAparatosByIdArticuloResult>> itdata = array.dataApaTipo.iterator();
        List<GetListTipoAparatosByIdArticuloResult> dat = itdata.next();
        idArticulo2 = dat.get(position).getIdArticulo();
        request.getApaTipDis(getApplicationContext());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
    }


}
