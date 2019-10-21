package edu.sjsu.moni.services;

import edu.sjsu.moni.models.Geo;
import edu.sjsu.moni.models.TweetResponse;
import edu.sjsu.moni.models.User;
import twitter4j.GeoLocation;
import twitter4j.Status;

/**
 * @author monica dommaraju
 */
public class TwitterUtil {

    protected static TweetResponse getTweetResponse(Status status) {
        TweetResponse response = new TweetResponse();
        response.setId(String.valueOf(status.getId()));
        response.setText(status.getText());
        response.setUser(TwitterUtil.getUserFromStatus(status));
        response.setGeo(getGeo(status.getGeoLocation()));
        response.setCreatedAt(status.getCreatedAt());
        return response;
    }

    private static User getUserFromStatus(Status status) {
        User user = new User();
        twitter4j.User statusUser = status.getUser();
        user.setName(statusUser.getName());
        user.setFriendsCount(statusUser.getFriendsCount());
        user.setLocation(statusUser.getLocation());
        user.setFollowersCount(statusUser.getFollowersCount());
        user.setScreenName(statusUser.getScreenName());
        user.setUrl(statusUser.getURL());
        return user;
    }

    private static Geo getGeo(GeoLocation geoLocation) {
        Geo geo = new Geo();
        if (geoLocation != null) {
            geo.setLat(geoLocation.getLatitude());
            geo.setLon(geoLocation.getLongitude());
        }
        return geo;
    }
}
