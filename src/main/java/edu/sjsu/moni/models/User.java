package edu.sjsu.moni.models;

// Author Sruthi Chilukuri
public class User {

    private String name;
    private String screenName;
    private String location;
    private String url;
    private int followersCount;
    private int friendsCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", screenName='" + screenName + '\'' +
                ", location='" + location + '\'' +
                ", url='" + url + '\'' +
                ", followersCount=" + followersCount +
                ", friendsCount=" + friendsCount +
                '}';
    }
}
