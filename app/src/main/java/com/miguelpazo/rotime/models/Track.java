package com.miguelpazo.rotime.models;

import java.util.Date;
import java.util.List;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class Track {
    private Integer id;
    private String description;
    private Date date;
    private List<PointLocation> lstPointLocation;

    public Track(String description) {
        this.description = description;
    }

    public List<PointLocation> getLstPointLocation() {
        return lstPointLocation;
    }

    public void setLstPointLocation(List<PointLocation> lstPointLocation) {
        this.lstPointLocation = lstPointLocation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
