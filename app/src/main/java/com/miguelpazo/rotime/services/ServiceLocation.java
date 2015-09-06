package com.miguelpazo.rotime.services;

import com.miguelpazo.rotime.models.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class ServiceLocation {

    public List<Location> getListLocations() {
        List<Location> lstLocation = new ArrayList<>();
        lstLocation.add(new Location(5.0, 6.5));
        lstLocation.add(new Location(5.0, 7.5));
        lstLocation.add(new Location(5.0, 8.5));
        lstLocation.add(new Location(5.0, 10.5));
        lstLocation.add(new Location(5.0, 9.5));

        return lstLocation;
    }
}
