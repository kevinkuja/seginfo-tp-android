package com.seginfo.tp.seginfotp;

import java.util.ArrayList;

import android.location.Location;
import android.util.Log;

/**
 * Created by juan on 27/06/15.
 */
public class ServerWrapper {

    public static void send_contacts(ArrayList<Contact> contacts){
        String contacts_string = "";
        for(Contact contact : contacts){
            contacts_string += contact.toString() + ",";
        }
        contacts_string = "["+contacts_string+"]";

        Log.i("Sending to Server:", contacts_string);
    }

    public static void send_location(Location location){
        Log.i("Sending to Server:", location.toString());
    }
}
