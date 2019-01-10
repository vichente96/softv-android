package com.example.pablo.prueba7;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class EjecutarFragment extends Fragment {

    Button reiniciar;
    Button eject;
    EditText edt1;

    public EjecutarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ejecutar, container, false);
        reiniciar = view.findViewById(R.id.restart);
        eject = view.findViewById(R.id.ejec);
        edt1 = view.findViewById(R.id.status);

        reiniciar.setEnabled(false);

        eject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((edt1.getText().toString().trim()).equalsIgnoreCase("ERROR")) {
                    reiniciar.setEnabled(true);
                }
                if ((edt1.getText().toString().trim()).equalsIgnoreCase("PENDIENTE")) {
                    reiniciar.setEnabled(true);
                }
                if ((edt1.getText().toString().trim()).equalsIgnoreCase("EXITOSO")) {
                    reiniciar.setEnabled(false);
                }
                eject.setEnabled(false);
                reiniciar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if ((edt1.getText().toString().trim()).equalsIgnoreCase("EXITOSO")) {
                            eject.setEnabled(true);
                        }
                        if ((edt1.getText().toString().trim()).equalsIgnoreCase("ERROR")) {
                            eject.setEnabled(false);
                        }
                        if ((edt1.getText().toString().trim()).equalsIgnoreCase("PENDIENTE")) {
                            eject.setEnabled(false);
                        }
                    }
                });


            }
        });
        return view;
    }

}