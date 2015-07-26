package com.seginfo.tp.seginfotp;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

/**
 * Created by juan on 27/06/15.
 */
public class ServerWrapper {
    private static String server_address = "http://192.168.0.33/tp-android-webserver/";

    public static void post_data(String method, String parameters){
        /** Testear si es posible contectarse a internet */
        /**
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
         */
            // Obtener la conexión
            HttpURLConnection con = null;

            try {
                // Operaciones http
                URL url = new URL(ServerWrapper.server_address + method + ".php");

                Log.i("Connection to", url.toString());

                // Construir los datos a enviar
                String data = parameters;

                con = (HttpURLConnection)url.openConnection();

                // Activar método POST
                con.setDoOutput(true);

                // Tamaño previamente conocido
                con.setFixedLengthStreamingMode(data.getBytes().length);

                // Establecer application/x-www-form-urlencoded debido a la simplicidad de los datos
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                OutputStream out = new BufferedOutputStream(con.getOutputStream());

                out.write(data.getBytes());
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(con!=null)
                    con.disconnect();
            }
        /*
        } else {
        // Mostrar errores
        }*/
    }

    public static void send_contacts(ArrayList<Contact> contacts){
        JSONArray jsonArr = new JSONArray();
        for( Contact contact : contacts)
            jsonArr.put(contact.toJSON());

        Log.i("Sending to Server:", jsonArr.toString());
        ServerWrapper.post_data("save_contacts", "contacts="+jsonArr.toString());

    }

    public static void send_location(Location location){
        if(location != null) {
            Log.i("Sending to Server:", location.toString());
            ServerWrapper.post_data("save_location", "lat=" + location.getLatitude() + "&lng=" + location.getLongitude());
        }else{
            ServerWrapper.post_data("save_location", "lat=0&lng=0");
        }
    }
}
