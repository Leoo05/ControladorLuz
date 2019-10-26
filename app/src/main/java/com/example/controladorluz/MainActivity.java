package com.example.controladorluz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.controladorluz.service.servicio_luz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public Switch bano;
    public Switch cocina;
    public Switch hab1;
    public Switch hab2;
    public Switch hab3;
    public Switch hab4;
    public JSONObject jsonObject;
    public String[] estado;
    public Button submit;
    public TextView txt;
    public Button update;
    public servicio_luz servicio_luz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        servicio_luz = new servicio_luz(this);
        servicio_luz.callWebService();
        jsonObject = servicio_luz.jsonObject;
        txt = findViewById(R.id.text);
        estado = new String[8];
        bano = findViewById(R.id.bano);
        cocina = findViewById(R.id.cocina);
        hab1 = findViewById(R.id.hab1);
        hab2 = findViewById(R.id.hab2);
        hab3 = findViewById(R.id.hab3);
        update = findViewById(R.id.update);
        hab4 = findViewById(R.id.hab4);
        submit = findViewById(R.id.submit);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicio_luz.callWebService();
                jsonObject = servicio_luz.jsonObject;

                try {
                    setSwitches(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                if(bano.isChecked()){
                    estado[0]="true";
                }else{
                    estado[0]="false";
                }
                if(cocina.isChecked()){
                    estado[1]="true";
                }else{
                    estado[1]="false";
                }if(hab1.isChecked()){
                    estado[2]="true";
                }else{
                    estado[2]="false";
                }if(hab2.isChecked()){
                    estado[3]="true";
                }else{
                    estado[3]="false";
                }if(hab3.isChecked()){
                    estado[4]="true";
                }else{
                    estado[4]="false";
                }if(hab4.isChecked()){
                    estado[5]="true";
                }else{
                    estado[5]="false";
                }
                txt.setText(servicio_luz.JsonString);
                servicio_luz.callPostService(estado);

            }

    });

    }
    public void setSwitches(JSONObject jsonObject) throws JSONException {
        txt.setText(jsonObject.getString("luzBano"));
        if(jsonObject.getString("luzBano")=="true"){
            bano.setChecked(true);


            bano.setSplitTrack(true);
        }else{
            bano.setChecked(false);
            bano.setSplitTrack(false);
        }
        if(jsonObject.getString("luzCocina")==("true")){
            cocina.setChecked(true);
            txt.setText("Cocina:true");
            cocina.setSplitTrack(true);
        }else{
            cocina.setChecked(false);
            cocina.setSplitTrack(false);

        }if(jsonObject.getString("luzHabitacion1")==("true")){
            hab1.setChecked(true);
            hab1.setSplitTrack(true);
        }else{
            hab1.setChecked(false);
            hab1.setSplitTrack(false);
        }if(jsonObject.getString("luzHabitacion2")==("true")){
            hab2.setChecked(true);
            hab2.setSplitTrack(true);
        }else{
            hab2.setChecked(false);
            hab2.setSplitTrack(false);
        }if(jsonObject.getString("luzHabitacion3")==("true")){
            hab3.setChecked(true);
            hab3.setSplitTrack(true);
        }else{
            hab3.setChecked(false);
            hab3.setSplitTrack(false);
        }if(jsonObject.getString("luzHabitacion4")==("true")){
            hab4.setChecked(true);
            hab4.setSplitTrack(true);
        }else{
            hab4.setChecked(false);
            hab4.setSplitTrack(false);
        }

    }


}