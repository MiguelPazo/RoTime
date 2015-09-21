package com.miguelpazo.rotime.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.miguelpazo.rotime.conexion.SQLiteManager;
import com.miguelpazo.rotime.contracts.ContractPointLocation;
import com.miguelpazo.rotime.contracts.ContractTrack;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 20/09/2015.
 */
public class RoTimeContentProvider extends ContentProvider {

    public static final Uri URI = Uri.parse("content://com.miguelpazo.rotime.ContentProvider");
    private SQLiteManager sqLiteManager;
    private static UriMatcher uriMatcher;

    private static final int TRACKS = 1;
    private static final int TRACKS_ID = 2;
    private static final int POINT_LOCATIONS = 3;
    private static final int POINT_LOCATIONS_ID = 4;
    private static final int POINT_LOCATIONS_TRACK = 5;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(URI.getAuthority(), "track", TRACKS);
        uriMatcher.addURI(URI.getAuthority(), "track/#", TRACKS_ID);
        uriMatcher.addURI(URI.getAuthority(), "point_locations", POINT_LOCATIONS);
        uriMatcher.addURI(URI.getAuthority(), "point_locations/track/#", POINT_LOCATIONS_TRACK);
        uriMatcher.addURI(URI.getAuthority(), "point_locations/#", POINT_LOCATIONS_ID);
    }

    @Override
    public boolean onCreate() {
        sqLiteManager = new SQLiteManager(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String where, String[] columns, String sort) {
        Cursor response = null;
        Integer matcher = uriMatcher.match(uri);

        if (matcher != UriMatcher.NO_MATCH) {
            String table = null;

            switch (matcher) {
                case TRACKS:
                    table = SQLiteManager.TABLE_TRACKS;
                    break;
                case TRACKS_ID:
                    table = SQLiteManager.TABLE_TRACKS;
                    where = "_id=" + uri.getLastPathSegment();
                    break;
                case POINT_LOCATIONS:
                    table = SQLiteManager.TABLE_PLOCATIONS;
                    break;
                case POINT_LOCATIONS_TRACK:
                    table = SQLiteManager.TABLE_PLOCATIONS;
                    where = "idTrack=" + uri.getLastPathSegment();
                    break;
            }

            if (table != null) {
                response = sqLiteManager.getReadableDatabase().query(table, projection, where, columns, null, null, sort);
            }
        }

        return response;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TRACKS:
                return ContractTrack.MIME_DIR;
            case TRACKS_ID:
                return ContractTrack.MIME_ITEM;
            case POINT_LOCATIONS:
                return ContractPointLocation.MIME_DIR;
            case POINT_LOCATIONS_ID:
                return ContractPointLocation.MIME_ITEM;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Uri response = null;
        Integer matcher = uriMatcher.match(uri);

        if (matcher != UriMatcher.NO_MATCH) {
            String table = null;
            Uri contractUri = null;

            switch (matcher) {
                case TRACKS:
                    table = SQLiteManager.TABLE_TRACKS;
                    contractUri = ContractTrack.URI;
                    break;
                case POINT_LOCATIONS:
                    table = SQLiteManager.TABLE_PLOCATIONS;
                    contractUri = ContractPointLocation.URI;
                    break;
            }

            if (table != null) {
                long id = sqLiteManager.getWritableDatabase().insert(table, null, contentValues);
                response = (id != -1) ? ContentUris.withAppendedId(contractUri, id) : null;
            }
        }

        return response;
    }

    @Override
    public int delete(Uri uri, String where, String[] selectionArgs) {
        int response = 0;
        Integer matcher = uriMatcher.match(uri);

        if (uriMatcher.match(uri) != UriMatcher.NO_MATCH) {
            String table = null;

            switch (matcher) {
                case TRACKS_ID:
                    table = SQLiteManager.TABLE_TRACKS;
                    where = "_id=" + uri.getLastPathSegment();
                    break;
                case POINT_LOCATIONS_ID:
                    table = SQLiteManager.TABLE_TRACKS;
                    where = "_id=" + uri.getLastPathSegment();
                    break;
                case POINT_LOCATIONS_TRACK:
                    table = SQLiteManager.TABLE_TRACKS;
                    where = "idTrack=" + uri.getLastPathSegment();
                    break;
            }

            if (table != null) {
                response = sqLiteManager.getWritableDatabase().delete(table, where, selectionArgs);
            }
        }

        return response;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String where, String[] selectionArgs) {
        int response = 0;
        Integer matcher = uriMatcher.match(uri);

        if (uriMatcher.match(uri) != UriMatcher.NO_MATCH) {
            String table = null;

            switch (matcher) {
                case TRACKS_ID:
                    table = SQLiteManager.TABLE_TRACKS;
                    where = "_id=" + uri.getLastPathSegment();
                    break;
                case POINT_LOCATIONS_ID:
                    table = SQLiteManager.TABLE_TRACKS;
                    where = "_id=" + uri.getLastPathSegment();
                    break;
            }

            if (table != null) {
                response = sqLiteManager.getWritableDatabase().update(table, contentValues, where, selectionArgs);
            }
        }

        return response;
    }
}
