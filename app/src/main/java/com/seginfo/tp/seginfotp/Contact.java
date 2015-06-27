package com.seginfo.tp.seginfotp;

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

    public String toString(){
        String telefonos = "";
        for(String phone : _telefonos) telefonos += phone+",";

        return "{id:"+_id+"; _nombre:"+_nombre+"; telefonos:["+telefonos+"]}";
    }
}

