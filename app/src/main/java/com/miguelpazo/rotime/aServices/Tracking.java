package com.miguelpazo.rotime.aServices;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.miguelpazo.rotime.contracts.ContractPointLocation;
import com.miguelpazo.rotime.models.PointLocation;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class Tracking extends Service implements LocationListener {

    public static final String TAG = "Tracking";
    private LocationManager locationManager;
    private Date prevDate = null;
    private Long difSeconds = new Long(5);
    private Boolean isFirst = true;
    private Integer idTrack;

    @Override
    public void onCreate() {
        locationManager = (LocationManager) getBaseContext().getSystemService(LOCATION_SERVICE);
        prevDate = new Date();

        startLocationRetrieving();
    }

    @Override
    public IBinder onBind(Intent intent) {
        idTrack = intent.getIntExtra("idTrack", 0);
        return null;
    }

    private void startLocationRetrieving() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Date currectDate = new Date();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        Long secondDif = timeUnit.convert(currectDate.getTime() - prevDate.getTime(), TimeUnit.MILLISECONDS);

        if (isFirst) {
            isFirst = false;
            PointLocation oPoint = new PointLocation();

            oPoint.setLatitude(location.getLatitude());
            oPoint.setLongitude(location.getLongitude());
            oPoint.setIdTrack(idTrack);

            ContractPointLocation.insert(oPoint, getContentResolver());

            Log.d(TAG + "-provider ", String.valueOf(location.getProvider()));
            Log.d(TAG + "-latitude ", String.valueOf(location.getLatitude()));
            Log.d(TAG + "-longitude ", String.valueOf(location.getLongitude()));
        } else if (secondDif >= difSeconds) {
            prevDate = currectDate;
            PointLocation oPoint = new PointLocation();

            oPoint.setLatitude(location.getLatitude());
            oPoint.setLongitude(location.getLongitude());
            oPoint.setIdTrack(idTrack);

            ContractPointLocation.insert(oPoint, getContentResolver());

            Log.d(TAG + "-provider ", String.valueOf(location.getProvider()));
            Log.d(TAG + "-latitude ", String.valueOf(location.getLatitude()));
            Log.d(TAG + "-longitude ", String.valueOf(location.getLongitude()));
        }
    }

    @Override
    public void onDestroy() {
        locationManager.removeUpdates(this);
        super.onDestroy();
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
