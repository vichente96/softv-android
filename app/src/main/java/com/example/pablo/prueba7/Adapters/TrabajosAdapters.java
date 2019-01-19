package com.example.pablo.prueba7.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pablo.prueba7.R;

import java.util.ArrayList;

import retrofit2.Converter;

import static com.example.pablo.prueba7.Listas.Array.acci;
import static com.example.pablo.prueba7.Listas.Array.descrip;

public class TrabajosAdapters extends AppCompatActivity {
    ListView ordenes;

    public TrabajosAdapters(Context applicationContext, int list_trabajos, ArrayList<String> descrip, ArrayList<String> acci) {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // dataagenda = new ArrayList<>();


        //ArrayAdapter adapt=new ArrayAdapter(this,android.R.layout.simple_list_item_1,nombrex);
        //ordenes.setAdapter(adapt);

    }

    class TrabajosAdapterss extends BaseAdapter{

        @Override
        public int getCount() {
            return descrip.size();
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

            convertView=getLayoutInflater().inflate(R.layout.list_trabajos,null);

            TextView descripcion =convertView.findViewById(R.id.observacion1);
            Button accion=convertView.findViewById(R.id.accion);


            descripcion.setText(descrip.get(position));
            accion.setText(acci.get(position));



            return convertView;
        }
    }
}



