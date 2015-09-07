package com.miguelpazo.rotime.aServices;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.miguelpazo.rotime.views.MainActivity;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class Tracking extends IntentService implements LocationListener {

    public static final String TAG = "Tracking";
    private LocationManager locationManager;

    public Tracking() {
        super(TAG);

        locationManager = (LocationManager) getBaseContext().getSystemService(LOCATION_SERVICE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Starting task...");
        startLocationRetrieving();
        Log.d(TAG, "Task completed!");
    }

    private void startLocationRetrieving() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG + "-latitude ", String.valueOf(location.getLatitude()));
        Log.d(TAG + "-longitude ", String.valueOf(location.getLongitude()));
        Log.d(TAG + "-provider ", String.valueOf(location.getProvider()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
