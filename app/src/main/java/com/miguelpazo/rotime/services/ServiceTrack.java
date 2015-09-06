package com.miguelpazo.rotime.services;

import com.miguelpazo.rotime.models.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class ServiceTrack {

    public List<Track> getListTracks() {
        List<Track> lstTrack = new ArrayList<>();

        lstTrack.add(new Track("Ruta 1"));
        lstTrack.add(new Track("Ruta 2"));
        lstTrack.add(new Track("Ruta 3"));
        lstTrack.add(new Track("Ruta 4"));
        lstTrack.add(new Track("Ruta 5"));

        return lstTrack;
    }
}
