package edu.sjsu.moni.models;

// Author Sruthi Chilukuri
public class Geo {

    private Double lat;
    private Double lon;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
