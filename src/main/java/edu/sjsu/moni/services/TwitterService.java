package edu.sjsu.moni.services;

import edu.sjsu.moni.models.Geo;
import edu.sjsu.moni.models.TweetRequest;
import edu.sjsu.moni.models.TweetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.*;

/**
 * @author monica dommaraju
 */
@Component
public class TwitterService {

    public static final String DELETE_SUCCESS_MSG = "Successfully Deleted!";
    public static final String ERROR = "ERROR";

    @Autowired
    private Twitter twitter;

    protected TwitterService(final Twitter twitter) {
        this.twitter = twitter;
    }

    public TweetResponse createTweet(TweetRequest request) throws TwitterException {
        Geo geo = request.getGeo();
        GeoLocation geoLocation = null;
        if (geo != null) {
            geoLocation = new GeoLocation(geo.getLat(), geo.getLon());
        }

        StatusUpdate statusUpdate = new StatusUpdate(request.getText());
        statusUpdate.setLocation(geoLocation);

        Status status = twitter.updateStatus(statusUpdate);
        return TwitterUtil.getTweetResponse(status);
    }

    // Author Sruthi Chilukuri
    public TweetResponse getTweet(String id) throws TwitterException {
        long idNum = Long.parseLong(id);
        Status status = twitter.showStatus(idNum);
        return TwitterUtil.getTweetResponse(status);
    }

    // Author Geethu Padachery
    public String deleteTweet(String id) throws TwitterException {
        long idNum = Long.parseLong(id);
        Status status = twitter.destroyStatus(idNum);
        if (status != null && status.getId() == idNum)
            return DELETE_SUCCESS_MSG;
        else {
            return ERROR;
        }
    }
}


