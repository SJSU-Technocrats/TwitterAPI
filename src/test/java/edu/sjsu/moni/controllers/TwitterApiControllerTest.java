package edu.sjsu.moni.controllers;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

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

	@BeforeEach
	void setUp() throws Exception {
		request.setText("Test Tweet " + new Random().nextInt());
		response = controller.createTweet(request);
	}

	@Test
	@Order(1)
	void testCreateTweetPositiveTestCase() throws TwitterException {
		assertNotNull(response.getId(), "Twitter Id is not null");
	}

	@Test
	@Order(2)
	void testGetTweetPositiveTestCase() {
		String id = response.getId();
		response = controller.getTweet(id);
		assertTrue(id.equalsIgnoreCase(response.getId()));
	}

	@Test
	@Order(3)
	void testDeleteTweetPositiveTestCase() throws TwitterException {
		String deleteTweet = controller.deleteTweet(response.getId());
		assertTrue("Successfully Deleted!".equalsIgnoreCase(deleteTweet));
	}

	@Test
	@Order(4)
	void testCreateTweetNegativeTestCase() {
		request.setText("");
		try {
			response = controller.createTweet(request);
		} catch (Exception e) {
			response = null;
		}
		assertNull(response);

	}

	@Test
	@Order(5)
	void testGetTweetNegativeTestCase() {
		try {
			response = controller.getTweet(null);
		} catch (Exception e) {
			response = null;
		}
		assertNull(response);
	}

	@Test
	@Order(6)
	void testDeleteTweetNegativeTestCase() {
		String delete;
		try {
			delete = controller.deleteTweet(null);
		} catch (Exception e) {
			delete = "Successfully Deleted!";
		}
		assertTrue("Successfully Deleted!".equalsIgnoreCase(delete));
	}
}
