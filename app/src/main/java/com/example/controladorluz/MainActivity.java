package com.example.controladorluz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Switch;
import android.widget.TextView;

import com.example.controladorluz.service.servicio_luz;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public Switch bano;
    public Switch cocina;
    public Switch hab1;
    public Switch hab2;
    public Switch hab3;
    public Switch hab4;
    public JSONObject jsonObject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        servicio_luz servicio_luz = new servicio_luz(this);
        servicio_luz.callWebService();
        jsonObject = servicio_luz.jsonObject;
        TextView txt = findViewById(R.id.text);
        String[] estado = new String[5];
        bano = findViewById(R.id.bano);
        cocina = findViewById(R.id.cocina);
        hab1 = findViewById(R.id.hab1);
        hab2 = findViewById(R.id.hab2);
        hab3 = findViewById(R.id.hab3);
        hab4 = findViewById(R.id.hab4);
        txt.setText(servicio_luz.JsonString);

        }



}
