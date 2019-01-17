package com.example.pablo.prueba7;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.prueba7.Modelos.GetListClienteAparatosResult;
import com.example.pablo.prueba7.Request.Request;

import java.util.List;

public class CambioAparato extends AppCompatActivity {

    public static Spinner aparato, estado,tipoAparato, aparatoAsignar;
    TextView posicion;
    Request request = new Request();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_aparato);
aparato = findViewById(R.id.aparato);
        estado = findViewById(R.id.estadoaparato);
        tipoAparato = findViewById(R.id.tipo_aparato);
        aparatoAsignar = findViewById(R.id.aparatoAsignar);
        posicion = findViewById(R.id.posicion);
        request.getCliApa(getApplicationContext());
        request.getStatusApa(getApplicationContext());


    aparato.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            }
    );
    }


}
