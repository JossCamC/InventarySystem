package com.alumn.InventarySystem.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alumn.InventarySystem.R;

import java.util.Timer;
import java.util.TimerTask;

public class controlador_inicio extends AppCompatActivity {
    private long duracion=1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_inicio);
        TimerTask task= new TimerTask () {
            @Override
            public void run() {
                Intent LLmarJuego;
                LLmarJuego = new Intent(controlador_inicio.this, controlador_menus.class);
                startActivity(LLmarJuego);
                controlador_inicio.this.finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,duracion);
    }
}
