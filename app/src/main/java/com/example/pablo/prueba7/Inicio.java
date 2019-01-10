package com.example.pablo.prueba7;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;

import com.example.pablo.prueba7.OrdQue.RequestOrdSer;
import com.example.pablo.prueba7.ProximoTrabajo.RequestProxCita;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;

import java.util.ArrayList;



public class Inicio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static int OE,OP,OV,RE,RP,REP,RV;

    public static PieChart  pieChart;
    RequestOrdSer requestOrdSer = new RequestOrdSer();
    public static TextView trabajo, direccion;
    RequestProxCita requestProxCita = new RequestProxCita();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        pieChart =(PieChart)findViewById(R.id.pastel);
        trabajo= (TextView)findViewById(R.id.proximotrabajo);
        direccion = (TextView)findViewById(R.id.direccion);
        setSupportActionBar(toolbar);
        Grafica();
        Error.Errores(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ///////////////////////////////////////////////////////////////
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
            Intent intent1 = new Intent(Inicio.this, Inicio.class);
            startActivity(intent1);
            requestProxCita.getProximaCita();
            try {
                requestOrdSer.getOrdenes();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            finish();
        } else if (id == R.id.Ordenes) {
            Intent intent1 = new Intent(Inicio.this, Orden.class);
            startActivity(intent1);
            finish();
        } else if (id == R.id.Reportes) {
            Intent intent1 = new Intent(Inicio.this, Reportes.class);
            startActivity(intent1);
            finish();
        } else if (id == R.id.Configuraciones) {
            Intent intent1 = new Intent(Inicio.this, Configuracion.class);
            startActivity(intent1);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            //Acción
        }
        return false;
    }
    public static void Grafica(){

        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(1f);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(android.R.color.white);
        pieChart.setTransparentCircleRadius(1f);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(OE,"OrdenEjecutada"));
        yValues.add(new PieEntry(OP,"OrdenPendiente"));
        yValues.add(new PieEntry(OV,"OrdenEnVisita"));
        yValues.add(new PieEntry(RE,"ReporteEjecutada"));
        yValues.add(new PieEntry(RP,"ReportePendiente"));
        yValues.add(new PieEntry(REP,"ReporteEnProceso"));
        yValues.add(new PieEntry(RV,"ReporteEnVisita"));

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(7f);
        dataSet.setSelectionShift(10f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setHighlightEnabled(true);

  /*      ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);

        dataSet.setColors(colors);*/

        PieData data = new PieData((dataSet));
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);
        pieChart.setData(data);
    }

}
