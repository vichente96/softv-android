package com.example.pablo.prueba7;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class asignado extends AppCompatActivity {
    Button escanear;
    Button cambio;
    TextView codigo;
    String contents;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignado);
        escanear = (Button) findViewById(R.id.escanear);
        codigo = (TextView) findViewById(R.id.codigo);

        cambio=(Button)findViewById(R.id.regresar);
        cambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento=new Intent(asignado.this,asignacion.class);
                startActivity(intento);
            }
        });


        escanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(asignado.this);
                scanIntegrator.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (scanningResult != null){
            contents = data.getStringExtra("SCAN_RESULT");
            codigo.setText(contents);
            codigo.setVisibility(TextView.VISIBLE);


        }
    }


}