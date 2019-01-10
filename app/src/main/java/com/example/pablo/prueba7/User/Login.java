package com.example.pablo.prueba7.User;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.pablo.prueba7.Error;
import com.example.pablo.prueba7.Inicio;
import com.example.pablo.prueba7.ListOrd.RequestListOrd;
import com.example.pablo.prueba7.R;

import org.json.JSONException;


public class Login extends AppCompatActivity {
    /*
     *Login
     */

    EditText usurio, contraseña;
    Button entrar, entrar2;
    String user;
    public static String enco;
    public static String cvl_usuario;
    UserController userController = new UserController();
    RequestListOrd requestListOrd = new RequestListOrd();
    public final static String CHANNEL_ID = "NOTIFICACION";
    public final static int NOTIFICACION_ID = 0;
    public static TextView clave;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usurio = (EditText) findViewById(R.id.usurio);
        contraseña = (EditText) findViewById(R.id.contraseña);
        entrar = (Button)findViewById(R.id.entrar);
        clave = (TextView)findViewById(R.id.tokenview);
        cvl_usuario= usurio.getText().toString();
/*
        token.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotificationChannel();
                createNotification();
            }
        });
        */


        Error.Errores(this);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               user = usurio.getText().toString() + ":" + contraseña.getText().toString();
                enco = (android.util.Base64.encodeToString(user.getBytes(), android.util.Base64.NO_WRAP));
                userController.getReviews();
                if (UserController.b=true){
                    userController.getReviews();
                    Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();
                  Intent intento = new Intent(Login.this, Inicio.class);
                  startActivity(intento);
                }
            }
        });

    }
    public void noti(){
        createNotificationChannel();
        createNotification();

    }
    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Noticacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_menu_send);
        builder.setContentTitle("SofTv");
        builder.setContentText("Nueva Ordenn de Servicio");
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());

    }
    public static void esperar(int segundos){
        try {
            Thread.sleep(segundos * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
