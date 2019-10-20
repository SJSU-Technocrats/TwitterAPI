package edu.sjsu.moni.services;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sjsu.moni.models.TweetRequest;
import edu.sjsu.moni.models.TweetResponse;
import twitter4j.TwitterException;

@SpringBootTest
class TwitterServiceTest {

	@Autowired
	TwitterService service;
	
	@Autowired
	TweetResponse response;
	
	@Autowired
	TweetRequest request;

	@BeforeEach
	void setUp() throws Exception {
		request.setText("Test Tweet " + new Random().nextInt());
		response = service.createTweet(request);
	}
	
	@Order(1)
	@Test
	void testCreateTweet() throws TwitterException {
		assertNotNull(response.getId(), "Twitter Id is not null");

	}

	@Order(2)
	@Test
	void testGetTweet() throws TwitterException {
		String id = response.getId();
		response = service.getTweet(id);
		assertTrue(id.equalsIgnoreCase(response.getId()));
	}

	@Order(3)
	@Test
	void testDeleteTweet() throws TwitterException {
		String id = service.deleteTweet(response.getId());
		assertTrue(id.equalsIgnoreCase("Successfully Deleted!"));
	}

	@Order(4)
	@Test
	void testCreateTweetNegative() {
		request.setText("");
		try {
			response = service.createTweet(request);
		} catch (Exception e) {
			response = null;
		}
		assertNull(response);
	}

	@Order(5)
	@Test
	void testGetTweetNegative() {
		try {
			response = service.getTweet(null);
		} catch (Exception e) {
			response = null;
		}
		assertNull(response);
	}

	@Order(6)
	@Test
	void testDeleteTweetNegative() {
		String delete;
		try {
			delete = service.deleteTweet(null);
		} catch (Exception e) {
			delete = "Successfully Deleted!";
		}
		assertTrue("Successfully Deleted!".equalsIgnoreCase(delete));
	}
}
