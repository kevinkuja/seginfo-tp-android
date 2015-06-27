package com.seginfo.tp.seginfotp;

/**
 * Created by juan on 27/06/15.
 */
public class Contact {
    int _id;
    String _nombre;
    String _telefonos;

    public Contact(int id, String nombre, String telefonos)
    {
        _id = id;
        _nombre = nombre;
        _telefonos = telefonos;
    }

    public String asString(){
        return "{id:"+_id+"; _nombre:"+_nombre+"; telefonos:["+_telefonos+"]}";
    }
}

