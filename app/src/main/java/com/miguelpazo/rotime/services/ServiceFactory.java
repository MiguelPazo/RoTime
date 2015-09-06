package com.miguelpazo.rotime.services;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class ServiceFactory {
    public static ServiceFactory instance = null;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }

        return instance;
    }

    public ServicePointLocation getServicePointLocation() {
        return new ServicePointLocation();
    }

    public ServiceTrack getServiceTrack() {
        return new ServiceTrack();
    }
}
