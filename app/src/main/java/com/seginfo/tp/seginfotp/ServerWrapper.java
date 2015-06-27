package com.seginfo.tp.seginfotp;

import java.util.ArrayList;
import android.util.Log;

/**
 * Created by juan on 27/06/15.
 */
public class ServerWrapper {

    public static void send_contacts(ArrayList<Contact> contacts){
        String contacts_string = "";
        for(Contact contact : contacts){
            contacts_string += contact.asString() + "-";
        }
        Log.i("Sending to Server:", contacts_string);
    }
}
