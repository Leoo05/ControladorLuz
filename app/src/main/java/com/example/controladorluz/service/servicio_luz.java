package com.example.controladorluz.service;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.http.conn.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;


import javax.net.ssl.HttpsURLConnection;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class servicio_luz {
    public static JSONObject jsonObject;
    public static String JsonString;

    public static final String URL_SWAPI = "https://swapi.co/api/";
    private TextView tvName;
    String nameResponse = "";
    public static Context context;

    public servicio_luz(Context conext){
        this.context = conext;
    }
    public void makeCall (View v) {
        callWebService();
    }

    public void sendData (View v) {
        String[] estado = new String[5];
        callPostService (estado);
    }

    public void callPostService (final String[] estado) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL urlService = new URL ("http://10.10.9.101:3000/aptos/changeAptoStatusBody" );
                    HttpURLConnection connection =  (HttpURLConnection) urlService.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
                    wr.writeBytes("{\"luzBano\":"+estado[0]+",\"luzCocina\":"+estado[1]+",\"luzHabitacion1\":"+estado[2]+",\"luzHabitacion2\":"+estado[3]+",\"luzHabitacion3\":"+estado[4]+",\"luzHabitacion4\":"+estado[5]+"}");
                    wr.close();

                    InputStream responseBody = connection.getInputStream();
                    if (connection.getResponseCode() == 200) {
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public static String getStringFromInputStream(InputStream stream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder total = new StringBuilder();
        for (String line; (line = r.readLine()) != null; ) {
            total.append(line).append('\n');
        }
        return total.toString();
    }
    public void callWebService(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    URL urlService = new URL ("http://10.10.9.101:3000/aptos/aptostatus" );
                    HttpURLConnection connection =  (HttpURLConnection) urlService.openConnection();

                    connection.setRequestMethod("GET");
                    InputStream responseBody = connection.getInputStream();

                    if (connection.getResponseCode() == 200) {
                        // Success
                        JsonString = getStringFromInputStream(responseBody);
                        jsonObject = new JSONObject(JsonString);


                    } else {
                        // Error handling code goes here
                        Log.v("ERROR", "ERROR");
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    } // end callWebService()


}