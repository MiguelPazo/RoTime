package com.miguelpazo.rotime.contracts;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.miguelpazo.rotime.contentProvider.RoTimeContentProvider;
import com.miguelpazo.rotime.models.PointLocation;
import com.miguelpazo.rotime.models.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class ContractPointLocation {

    public static final Uri URI = Uri.parse(RoTimeContentProvider.URI.toString() + "/point_locations");
    public static final Uri URI_ID = Uri.parse(RoTimeContentProvider.URI.toString() + "/point_locations/#");
    public static final Uri URI_TRACK = Uri.parse(RoTimeContentProvider.URI.toString() + "/point_locations/track/#");

    public static final String MIME_DIR = "vnd.android.cursor.dir/vnd.com.miguelpazo.rotime.point_location";
    public static final String MIME_ITEM = "vnd.android.cursor.item/vnd.com.miguelpazo.rotime.point_location";

    public static List<PointLocation> getListPointLocationByTrack(Integer idTrack, ContentResolver contentResolver) {
        List<PointLocation> lstPointLocation = new ArrayList<>();
        Uri newUri = Uri.withAppendedPath(URI_TRACK, String.valueOf(idTrack));
        String uri = newUri.toString();

        Cursor cursor = contentResolver.query(newUri, null, null, null, null);

        while (cursor.moveToNext()) {
            PointLocation oPoint = new PointLocation();

            oPoint.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            oPoint.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
            oPoint.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));
            oPoint.setIdTrack(cursor.getInt(cursor.getColumnIndex("idTrack")));

            lstPointLocation.add(oPoint);
        }

        cursor.close();

        return lstPointLocation;
    }

    public static List<PointLocation> getListPointLocation(ContentResolver contentResolver) {
        List<PointLocation> lstPointLocation = new ArrayList<>();
        Cursor cursor = contentResolver.query(URI, null, null, null, null);

        while (cursor.moveToNext()) {
            PointLocation oPoint = new PointLocation();

            oPoint.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            oPoint.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
            oPoint.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));
            oPoint.setIdTrack(cursor.getInt(cursor.getColumnIndex("idTrack")));

            lstPointLocation.add(oPoint);
        }

        cursor.close();

        return lstPointLocation;
    }

    public static Integer insert(PointLocation oPoint, ContentResolver contentResolver) {
        ContentValues content = new ContentValues();

        content.put("latitude", oPoint.getLatitude());
        content.put("longitude", oPoint.getLongitude());
        content.put("idTrack", oPoint.getIdTrack());

        Integer newId = Integer.valueOf(contentResolver.insert(URI, content).getPathSegments().get(1));

        return newId;
    }
}
