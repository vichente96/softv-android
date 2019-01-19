package com.example.pablo.prueba7;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pablo.prueba7.Adapters.TrabajosAdapters;

import static com.example.pablo.prueba7.Listas.Array.acci;
import static com.example.pablo.prueba7.Listas.Array.descrip;


/**
 * A simple {@link Fragment} subclass.
 */
public class Trabajos extends Fragment{
    public static TextView trabajo1;
    public static Button accion1;


    public Trabajos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trabajos, container, false);
        Button accion = view.findViewById(R.id.accion);
        trabajo1=view.findViewById(R.id.observacion1);
        accion1=view.findViewById(R.id.accion);


        /*ListView List=view.findViewById(R.id.list);
        ArrayAdapter adapter=new ArrayAdapter(getActivity().getApplicationContext(),R.layout.list_trabajos,descrip);
         List.setAdapter( adapter);*/

        accion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getContext(), asignacion.class);
                startActivity(intento);
            }
        });
        return view;
    }

}
