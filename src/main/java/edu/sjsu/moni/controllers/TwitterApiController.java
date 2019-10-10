package edu.sjsu.moni.controllers;

import edu.sjsu.moni.models.TweetRequest;
import edu.sjsu.moni.models.TweetResponse;
import edu.sjsu.moni.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import twitter4j.TwitterException;

@RestController
@RequestMapping("/tweet")
public class TwitterApiController {

    @Autowired
    private TwitterService service;

    @RequestMapping(method = RequestMethod.POST)
    public TweetResponse createTweet(@RequestBody TweetRequest request) {
        try {
            return service.createTweet(request);
        } catch (TwitterException e) {
            if (e.getStatusCode() == 403) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Duplicate status. Please change the tweet text!");
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public TweetResponse getTweet(@RequestParam String id) {
        try{
            return service.getTweet(id);
        } catch (TwitterException e) {
            if (e.getStatusCode() == 404) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tweet not found");
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
            }
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteTweet(@RequestParam String id) {
        try {
            return service.deleteTweet(id);
        } catch (TwitterException e) {
            if (e.getStatusCode() == 404) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tweet not found");
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
            }
        }
    }
}
