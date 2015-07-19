package com.seginfo.tp.seginfotp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by juan on 19/07/15.
 */
public class HijackerServiceLauncherAtBoot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            HijackerServiceLauncher.getInstance().runFor(context);
        }
    }

}
