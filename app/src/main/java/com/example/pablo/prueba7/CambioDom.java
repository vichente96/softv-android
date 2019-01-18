package com.example.pablo.prueba7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CambioDom extends AppCompatActivity {

    Button aceptar;
   public static TextView Ciudad, Localidad, Colonia, Calle, Numero, Numero_i, Telefono, CalleN, CalleS, CallleE, CalleO;
   public static ImageView CasaNorte, CasaSur, CasaEste, CasaOeste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_domicilio);
        aceptar = findViewById(R.id.aceptar);
        Ciudad = findViewById(R.id.ciudad);
        Localidad = findViewById(R.id.localidad);
        Colonia = findViewById(R.id.colonia);
        Calle = findViewById(R.id.Calledom);
        Numero = findViewById(R.id.numero);
        Numero_i = findViewById(R.id.numeroi);
        Telefono = findViewById(R.id.telefono);
        CalleN = findViewById(R.id.callenorte);
        CalleS = findViewById(R.id.callesur);
        CallleE = findViewById(R.id.calleeste);
        CalleO = findViewById(R.id.calleoeste);
        CasaNorte = findViewById(R.id.casanorte);
        CasaSur = findViewById(R.id.casasur);
        CasaEste = findViewById(R.id.casaeste);
        CasaOeste = findViewById(R.id.casaoeste);



        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento1 = new Intent(CambioDom.this, Orden.class);
                startActivity(intento1);
            }
        });


    }
}
