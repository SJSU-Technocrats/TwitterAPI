package edu.sjsu.moni.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.moni.config.TwitterConfig;
import edu.sjsu.moni.models.TweetRequest;
import edu.sjsu.moni.models.TweetResponse;
import edu.sjsu.moni.services.TwitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import twitter4j.TwitterException;

import static edu.sjsu.moni.controllers.TwitterApiController.SERVER_ERROR_MSG;
import static edu.sjsu.moni.services.TwitterService.DELETE_SUCCESS_MSG;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;


// Author Geethu Padachery
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = TwitterApiController.class, secure = false)
@EnableConfigurationProperties(value = { TwitterConfig.class })
public class TwitterApiControllerTest {

	private static final String TWEET_ID = "123";
	private static final String TWEET_TEXT = "testTweet";
	private static final String SOME_EXCEPTION = "exception";

	@MockBean
	TwitterService service;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testCreateTweet() throws Exception {
		TweetRequest tweetRequest = new TweetRequest();
		tweetRequest.setText(TWEET_TEXT);
		TweetResponse tweetResponse = new TweetResponse();
		tweetResponse.setId(TWEET_ID);
		tweetResponse.setText(TWEET_TEXT);
		doReturn(tweetResponse).when(service).createTweet(any(TweetRequest.class));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/tweet")
				.content(asJsonString(tweetRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200,  result.getResponse().getStatus());
		ObjectMapper mapper = new ObjectMapper();
		TweetResponse actualResponse = mapper.readValue(result.getResponse().getContentAsString(), TweetResponse.class);
		assertEquals(actualResponse.getId(), TWEET_ID);
		assertEquals(actualResponse.getText(), TWEET_TEXT);
	}

	@Test
	public void testCreateTweetThrowsException() throws Exception {
		TweetRequest tweetRequest = new TweetRequest();
		tweetRequest.setText(TWEET_TEXT);
		doThrow(new TwitterException(SOME_EXCEPTION)).when(service).createTweet(any(TweetRequest.class));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/tweet")
				.content(asJsonString(tweetRequest))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse actualResponse = result.getResponse();
		assertEquals(500, actualResponse.getStatus());
		assertTrue(SERVER_ERROR_MSG.equalsIgnoreCase(result.getResponse().getErrorMessage()));
	}

	@Test
	public void testGetTweet() throws Exception {
		TweetResponse response = new TweetResponse();
		response.setId(TWEET_ID);
		response.setText(TWEET_TEXT);

		doReturn(response).when(service).getTweet(anyString());
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/tweet")
				.param("id", TWEET_ID)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		ObjectMapper mapper = new ObjectMapper();
		TweetResponse actualResponse = mapper.readValue(result.getResponse().getContentAsString(), TweetResponse.class);
		assertEquals(actualResponse.getId(), TWEET_ID);
		assertEquals(actualResponse.getText(), TWEET_TEXT);
	}

	@Test
	public void testGetTweetThrowsException() throws Exception {
		doThrow(new TwitterException(SOME_EXCEPTION)).when(service).getTweet(anyString());
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/tweet")
				.param("id", TWEET_ID)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse actualResponse = result.getResponse();
		assertEquals(500, actualResponse.getStatus());
		assertTrue(SERVER_ERROR_MSG.equalsIgnoreCase(result.getResponse().getErrorMessage()));
	}

	@Test
	public void testDeleteTweet() throws Exception {
		doReturn(DELETE_SUCCESS_MSG).when(service).deleteTweet(anyString());
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/tweet")
				.param("id", TWEET_ID)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		assertTrue(DELETE_SUCCESS_MSG.equalsIgnoreCase(result.getResponse().getContentAsString()));

	}

	@Test
	public void testDeleteTweetThrowsException() throws Exception {
		doThrow(new TwitterException(SOME_EXCEPTION)).when(service).deleteTweet(anyString());
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/tweet")
				.param("id", TWEET_ID)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse actualResponse = result.getResponse();
		assertEquals(500, actualResponse.getStatus());
		assertTrue(SERVER_ERROR_MSG.equalsIgnoreCase(result.getResponse().getErrorMessage()));
	}

	private String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
