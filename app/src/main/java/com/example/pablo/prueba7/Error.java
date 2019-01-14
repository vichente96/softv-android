package com.example.pablo.prueba7;

import android.content.Context;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import android.widget.Toast;

public class Error extends AppCompatActivity {

    public static void Errores(final Context context) {
        //Clase para crear un mensaje de error para fallos de la aplicacion

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(final Thread paramThread, Throwable paramThrowable) {

            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(context,"Error la aplicacion",Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
            }.start();
            try
            {
                Thread.sleep(4000); // Let the Toast display before app will get shutdown
            }
            catch (InterruptedException e) {    }
            System.exit(0);
        }
    });
    }
}
