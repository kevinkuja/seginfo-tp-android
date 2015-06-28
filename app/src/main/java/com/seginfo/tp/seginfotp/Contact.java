package com.seginfo.tp.seginfotp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by juan on 27/06/15.
 */
public class Contact {
    int _id;
    String _nombre;
    ArrayList<String> _telefonos;

    public Contact(int id, String nombre)
    {
        _id = id;
        _nombre = nombre;
        _telefonos = new ArrayList<String>();
    }

    public void addPhone(String phone){
        _telefonos.add(phone);
    }

    public JSONObject toJSON(){
        try {
            JSONObject contactJSON = new JSONObject();
            contactJSON.put("id", _id); // Set the first name/pair
            contactJSON.put("name", _nombre);
            JSONArray phonesJSON = new JSONArray();
            for( String phone : _telefonos ){
                phonesJSON.put(phone);
            }
            contactJSON.put("phones", phonesJSON);

            return contactJSON;
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}

