package com.webdesarrollador.servicestutorial3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void play(View v) {
        //Empezamos el servicio
        startService(new Intent(this, Service3.class));
    }

    public void stop(View v) {
        //Detenemos el servicio
        stopService(new Intent(this, Service3.class));
    }
}
