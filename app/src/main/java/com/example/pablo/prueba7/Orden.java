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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Request.Request;

import org.json.JSONException;

public class Orden extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    Request request = new Request();
    Button orden1, cambiodom, cambioapa;
    ListView ordenes;


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        orden1 =  findViewById(R.id.orden);
        cambiodom = findViewById(R.id.cambiodom);
        cambioapa = findViewById(R.id.cambioapa);
        ordenes=findViewById(R.id.listorden);
        Error.Errores(this);

        ////////////////////////////////////////

        OrdenesListAdaapter1 ordAdapt=new OrdenesListAdaapter1();
        ordenes.setAdapter(ordAdapt);    //Asignacion del adapatador a la listView

        //////////////////////////////////////////

        //* Boton para ir a menu principal
        orden1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intento1 = new Intent(Orden.this, MainActivity.class);
                startActivity(intento1);

                   request.getDeepCons();



            }
        });
        cambiodom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request.getCAMDO();
                Intent intent = new Intent(Orden.this, CambioDom.class);
                startActivity(intent);
            }
        });

        cambioapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Orden.this, CambioAparato.class);
                startActivity(intent);
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

    ///////////////////////ADAPTADOR ORDENES//////////////////////
    class OrdenesListAdaapter1 extends BaseAdapter {
        @Override
        public int getCount() {
            return Array.ordenx.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            convertView = getLayoutInflater().inflate(R.layout.ordenes_list_items,null);


            TextView status=(TextView)convertView.findViewById(R.id.statusv);
            Button orden=(Button)convertView.findViewById(R.id.ordenv);
            TextView contrato=(TextView)convertView.findViewById(R.id.contratov);
            TextView nombre=(TextView)convertView.findViewById(R.id.nombrev);


            status.setText(Array.statusx.get(position));
            orden.setText(Array.ordenx.get(position));
            contrato.setText(Array.contratox.get(position));
            nombre.setText(Array.nombrex.get(position));

            return convertView;
        }
    }

    ////////////////////////////////////////////

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
            Intent intent1 = new Intent(Orden.this, Inicio.class);
            startActivity(intent1);
            //Actualizar la siguente cita y la grafica
           request.getProximaCita();

                request.getOrdenes();

        } else if (id == R.id.Ordenes) {
            Intent intent1 = new Intent(Orden.this, Orden.class);
            startActivity(intent1);


        } else if (id == R.id.Reportes) {
            Intent intent1 = new Intent(Orden.this, Reportes.class);
            startActivity(intent1);

        } else if (id == R.id.Configuraciones) {
            Intent intent1 = new Intent(Orden.this, Configuracion.class);
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
