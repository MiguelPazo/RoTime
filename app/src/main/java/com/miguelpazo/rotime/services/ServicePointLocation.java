package com.miguelpazo.rotime.services;

import com.miguelpazo.rotime.models.PointLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class ServicePointLocation {

    public List<PointLocation> getListPointLocations(Integer id) {
        List<PointLocation> lstPointLocation = new ArrayList<>();
        lstPointLocation.add(new PointLocation(-11.9475725, -76.9851425));
        lstPointLocation.add(new PointLocation(-11.951005, -76.9880867));
        lstPointLocation.add(new PointLocation(-11.9525256, -76.9877984));
        lstPointLocation.add(new PointLocation(-11.9560338, -76.9857539));
        lstPointLocation.add(new PointLocation(-11.9576996, -76.98626));
        lstPointLocation.add(new PointLocation(-11.9587931, -76.986905));
        lstPointLocation.add(new PointLocation(-11.9604758, -76.9881307));
        lstPointLocation.add(new PointLocation(-11.9645663, -76.9911166));
        lstPointLocation.add(new PointLocation(-11.9677918, -76.9934093));
        lstPointLocation.add(new PointLocation(-11.9711132, -76.9960757));
        lstPointLocation.add(new PointLocation(-11.9747932, -77.0005256));
        lstPointLocation.add(new PointLocation(-11.9792065, -77.0052121));
        lstPointLocation.add(new PointLocation(-11.9834722, -77.0065932));
        lstPointLocation.add(new PointLocation(-11.9888006, -77.0097816));
        lstPointLocation.add(new PointLocation(-11.992047, -77.011329));
        lstPointLocation.add(new PointLocation(-11.993847, -77.010889));
        lstPointLocation.add(new PointLocation(-11.997386, -77.009861));
        lstPointLocation.add(new PointLocation(-12.001996, -77.008564));
        lstPointLocation.add(new PointLocation(-12.005020, -77.006850));
        lstPointLocation.add(new PointLocation(-12.007836, -77.005075));
        lstPointLocation.add(new PointLocation(-12.013086, -77.002283));

        return lstPointLocation;
    }
}
