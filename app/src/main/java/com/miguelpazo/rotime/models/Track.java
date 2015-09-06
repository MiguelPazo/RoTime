package com.miguelpazo.rotime.models;

import java.util.Date;
import java.util.List;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class Track {
    private String description;
    private Date date;
    private List<Location> lstLocation;

    public Track(String description) {
        this.description = description;
    }

    public List<Location> getLstLocation() {
        return lstLocation;
    }

    public void setLstLocation(List<Location> lstLocation) {
        this.lstLocation = lstLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
