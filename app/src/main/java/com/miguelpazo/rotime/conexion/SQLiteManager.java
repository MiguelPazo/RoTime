package com.miguelpazo.rotime.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 20/09/2015.
 */
public class SQLiteManager extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "unencrypted-roTime-database";
    public static int DATABASE_VERSION = 1;
    public static String TABLE_TRACKS = "tracks";
    public static String TABLE_PLOCATIONS = "point_locations";

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTracks = "CREATE TABLE " + TABLE_TRACKS + "(_id INTEGER PRIMARY KEY, description TEXT, creationTime INTEGER)";
        String sqlPLocations = "CREATE TABLE " + TABLE_PLOCATIONS + "(_id INTEGER PRIMARY KEY, idTrack INTEGER, latitude TEXT, longitude TEXT)";

        sqLiteDatabase.execSQL(sqlTracks);
        sqLiteDatabase.execSQL(sqlPLocations);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
