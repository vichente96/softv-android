package com.example.pablo.prueba7;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

<<<<<<< Updated upstream
=======

import static com.example.pablo.prueba7.Listas.Array.acci;
import static com.example.pablo.prueba7.Listas.Array.descrip;

>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trabajos,
                container, false);
=======
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trabajos, container, false);
>>>>>>> Stashed changes
        Button accion = view.findViewById(R.id.accion);
        trabajo1=view.findViewById(R.id.observacion1);
        accion1=view.findViewById(R.id.accion);


<<<<<<< Updated upstream
=======
        ListView List=view.findViewById(R.id.list);
        ArrayAdapter adapter=new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,descrip);
         List.setAdapter( adapter);

>>>>>>> Stashed changes
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
