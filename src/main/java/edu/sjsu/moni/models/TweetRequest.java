package edu.sjsu.moni.models;

public class TweetRequest {
    private String text;
    private Geo geo;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }
}
