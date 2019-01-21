package com.example.pablo.prueba7;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.pablo.prueba7.Modelos.UserModel;
import com.example.pablo.prueba7.Request.Request;


import org.json.JSONException;

public class Configuracion extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button CS;
    Request request = new Request();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CS = (Button)findViewById(R.id.CS);
        Error.Errores(this);
        //Boton para cerrar sesion
        CS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel.Token="";
                try {
                     request.b = false;
                    Intent intento = new Intent(Configuracion.this, Login.class);
                    startActivity(intento);
                }
                catch (Exception e){

                }

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Inicio) {
            Intent intent1 = new Intent(Configuracion.this, Inicio.class);
            startActivity(intent1);
            //Actualizar la siguente cita y la grafica
           request.getProximaCita();
           request.getOrdenes();

        } else if (id == R.id.Ordenes) {
            Intent intent1 = new Intent(Configuracion.this, Orden.class);
            startActivity(intent1);

        } else if (id == R.id.Reportes) {
            Intent intent1 = new Intent(Configuracion.this, Reportes.class);
            startActivity(intent1);

        } else if (id == R.id.Configuraciones) {
            Intent intent1 = new Intent(Configuracion.this, Configuracion.class);
            startActivity(intent1);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //Bloquear el boton de atras
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
        }
        return false;
    }
}
