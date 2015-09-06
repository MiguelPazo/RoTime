package com.miguelpazo.rotime.views;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.miguelpazo.rotime.R;
import com.miguelpazo.rotime.models.PointLocation;
import com.miguelpazo.rotime.models.Track;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class FragMaps extends MapFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Track oTrack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oTrack = MainActivity.oTrack;

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        googleMap = gMap;

        //first and last marker
        PointLocation firstPoint = oTrack.getLstPointLocation().get(0);
        PointLocation lastPoint = oTrack.getLstPointLocation().get(oTrack.getLstPointLocation().size() - 1);

        MarkerOptions firstMarker = new MarkerOptions();
        MarkerOptions lastMarker = new MarkerOptions();

        firstMarker
                .position(new LatLng(firstPoint.getLatitude(), firstPoint.getLongitude()))
                .title(getString(R.string.title_begin))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

        lastMarker
                .position(new LatLng(lastPoint.getLatitude(), lastPoint.getLongitude()))
                .title(getString(R.string.title_begin))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

        googleMap.addMarker(firstMarker);
        googleMap.addMarker(lastMarker);

        //draw polyline
        PolylineOptions polylineOptions = new PolylineOptions();

        for (PointLocation pointLocation : oTrack.getLstPointLocation()) {
            polylineOptions.add(new LatLng(pointLocation.getLatitude(), pointLocation.getLongitude()));
        }

        polylineOptions.width(6f);
        polylineOptions.color(Color.RED);

        googleMap.addPolyline(polylineOptions);

        //moving camera
        moveTo(firstPoint.getLatitude(), firstPoint.getLongitude(), true);
    }

    public void moveTo(final double latitude, final double longitude, final boolean animate) {
        final LatLng latlng = new LatLng(latitude, longitude);
        final CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latlng)
                .zoom(17.25f)
                .build();
        final CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        if (animate) {
            googleMap.animateCamera(cameraUpdate);
        } else {
            googleMap.moveCamera(cameraUpdate);
        }
    }
}
