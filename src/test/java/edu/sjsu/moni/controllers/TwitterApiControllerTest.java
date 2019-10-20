package edu.sjsu.moni.controllers;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Random;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sjsu.moni.models.TweetRequest;
import edu.sjsu.moni.models.TweetResponse;
import twitter4j.TwitterException;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TwitterApiControllerTest {

	@Autowired
	TwitterApiController controller;

	@Autowired
	TweetResponse response;

	@Autowired
	TweetRequest request;

	@Test
	@Order(1)
	void testCreateTweet() throws TwitterException {
		request.setText("Test Tweet " + new Random().nextInt());
		response = controller.createTweet(request);
		assertNotNull(response.getId(), "Twitter Id is not null");
	}

	@Test
	@Order(2)
	void testGetTweet() {
		String id = response.getId();		
		response = controller.getTweet(id);
		assertTrue(id.equalsIgnoreCase(response.getId()));
	}

	@Test
	@Order(3)
	void testDeleteTweet() throws TwitterException {
		String deleteTweet = controller.deleteTweet(response.getId());
		assertTrue("Successfully Deleted!".equalsIgnoreCase(deleteTweet));
	}	
	@Test
	@Order(4)
	void testCreateTweetNegative(){
		request.setText("");
	    try{response = controller.createTweet(request);}
	    catch (Exception e) {
	    	response = null;
	    }
	    assertNull(response);
		
		}
	}

