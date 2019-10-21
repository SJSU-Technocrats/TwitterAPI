package edu.sjsu.moni.models;

// Author Sruthi Chilukuri
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

    @Override
    public String toString() {
        return "TweetRequest{" +
                "text='" + text + '\'' +
                ", geo=" + geo +
                '}';
    }
}
