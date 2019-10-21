package edu.sjsu.moni.controllers;

import edu.sjsu.moni.models.TweetRequest;
import edu.sjsu.moni.models.TweetResponse;
import edu.sjsu.moni.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import twitter4j.TwitterException;


/**
 * @author monica dommaraju
 */
@RestController
@RequestMapping("/tweet")
public class TwitterApiController {

    static final String SERVER_ERROR_MSG = "Internal Server Error";
    static final String TWEET_NOT_FOUND = "Tweet not found";

    @Autowired
    private TwitterService service;

    // Author: Monica Dommaraju
    @RequestMapping(method = RequestMethod.POST)
    public TweetResponse createTweet(@RequestBody TweetRequest request) {
        try {
            return service.createTweet(request);
        } catch (TwitterException e) {
            if (e.getStatusCode() == 403) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Duplicate status. Please change the tweet text!");
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, SERVER_ERROR_MSG);
            }
        }
    }

    // Author: Sruthi Chilukuri
    @RequestMapping(method = RequestMethod.GET)
    public TweetResponse getTweet(@RequestParam String id) {
        try{
            return service.getTweet(id);
        } catch (TwitterException e) {
            if (e.getStatusCode() == 404) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, TWEET_NOT_FOUND);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, SERVER_ERROR_MSG);
            }
        }
    }

    // Author: Geethu Padachery
    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteTweet(@RequestParam String id) {
        try {
            return service.deleteTweet(id);
        } catch (TwitterException e) {
            if (e.getStatusCode() == 404) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, TWEET_NOT_FOUND);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, SERVER_ERROR_MSG);
            }
        }
    }
}
