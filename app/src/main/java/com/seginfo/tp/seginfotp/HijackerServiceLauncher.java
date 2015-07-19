package com.seginfo.tp.seginfotp;

import android.content.Context;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by juan on 19/07/15.
 */
public class HijackerServiceLauncher {
    private final Integer FREQ_HIJACKER = 10; // in sec

    private static HijackerServiceLauncher instance = null;

    private Boolean running = false;
    private Context context;

    public static HijackerServiceLauncher getInstance() {
        if(instance == null) {
            instance = new HijackerServiceLauncher();
        }
        return instance;
    }

    public void runFor(Context context){
        if( !running ) {
            running = true;
            this.context = context;
            launch_hijacker_service();
        }
    }


    private void launch_hijacker_service(){
        Intent serviceIntent = new Intent(context, HijackerService.class);
        context.startService(serviceIntent);

        repeatWithTimer(serviceIntent);
    }

    private void repeatWithTimer(Intent intent){
        final Intent myIntent = intent;
        long frequency = FREQ_HIJACKER*1000; // in millis

        Timer timer = new Timer ();
        TimerTask repetitiveTask = new TimerTask () {
            @Override
            public void run () {
                context.startService(myIntent);
            }
        };

        // schedule the task to run starting now and then every hour...
        timer.schedule (repetitiveTask, 0l, frequency);
    }
}
