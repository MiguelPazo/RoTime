package com.miguelpazo.rotime.contracts;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.miguelpazo.rotime.contentProvider.RoTimeContentProvider;
import com.miguelpazo.rotime.models.Track;
import com.miguelpazo.rotime.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class ContractTrack {

    public static final Uri URI = Uri.parse(RoTimeContentProvider.URI.toString() + "/track");
    public static final Uri URI_ID = Uri.parse(RoTimeContentProvider.URI.toString() + "/track/#");

    public static final String MIME_DIR = "vnd.android.cursor.dir/vnd.com.miguelpazo.rotime.track";
    public static final String MIME_ITEM = "vnd.android.cursor.item/vnd.com.miguelpazo.rotime.track";


    public static List<Track> getListTracks(ContentResolver contentResolver) {
        List<Track> lstTrack = new ArrayList<>();

        Cursor cursor = contentResolver.query(URI, null, null, null, "creationTime DESC");

        while (cursor.moveToNext()) {
            Track oTrack = new Track();

            oTrack.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            oTrack.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            oTrack.setCreationTime(cursor.getLong(cursor.getColumnIndex("creationTime")));

            lstTrack.add(oTrack);
        }

        cursor.close();

        return lstTrack;
    }

    public static Integer insert(Track oTrack, ContentResolver contentResolver) {
        ContentValues content = new ContentValues();

        content.put("description", oTrack.getDescription());
        content.put("creationTime", oTrack.getCreationTime());

        Integer newId = Integer.valueOf(contentResolver.insert(URI, content).getPathSegments().get(1));

        return newId;
    }
}
