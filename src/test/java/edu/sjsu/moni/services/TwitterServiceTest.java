package edu.sjsu.moni.services;


import edu.sjsu.moni.models.Geo;
import edu.sjsu.moni.models.TweetRequest;
import edu.sjsu.moni.models.TweetResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import static edu.sjsu.moni.services.TwitterService.DELETE_SUCCESS_MSG;
import static edu.sjsu.moni.services.TwitterService.ERROR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

/**
 * @author monica dommaraju
 */
public class TwitterServiceTest {

	private static final String TWEET_ID = "123";
	private static final String SOME_EXCEPTION = "exception";

	@Mock
	private Status status;
	@Mock
	private Twitter twitter;

	private TwitterService twitterService;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		twitterService = new TwitterService(twitter);
	}

	@Test
	public void testCreateTweet() throws Exception {
		TweetRequest request = getTweetRequest();
		Status status = TwitterObjectFactory.createStatus(NEW_STATUS_RESPONSE);
		doReturn(status).when(twitter).updateStatus(any(StatusUpdate.class));

		TweetResponse response = twitterService.createTweet(request);

		assertNotNull(response);
		assertEquals(response.getId(), TWEET_ID);
		assertEquals(response.getText(), "newTestRun");
		assertEquals(response.getUser().getName(), "Monica");
	}

	@Test(expected = TwitterException.class)
	public void testCreateTweetThrowsException() throws Exception {
		TweetRequest request = getTweetRequest();
		doThrow(new TwitterException(SOME_EXCEPTION)).when(twitter).updateStatus(any(StatusUpdate.class));

		TweetResponse response = twitterService.createTweet(request);

		assertNotNull(response);
	}

	// Author Sruthi Chilukuri
	@Test
	public void testGetTweet() throws  Exception {
		Status status = TwitterObjectFactory.createStatus(STATUS_RESPONSE);
		doReturn(status).when(twitter).showStatus(anyLong());
		TweetResponse response = twitterService.getTweet(TWEET_ID);
		assertNotNull(response);
		assertEquals(response.getId(), TWEET_ID);
		assertEquals(response.getText(), "testRun");
		assertEquals(response.getUser().getName(), "Monica");
	}

	// Author Sruthi Chilukuri
	@Test(expected = TwitterException.class)
	public void testGetTweetThrowsException() throws  Exception {
		doThrow(new TwitterException(SOME_EXCEPTION)).when(twitter).showStatus(anyLong());
		twitterService.getTweet(TWEET_ID);
	}

	// Author Sruthi Chilukuri
	@Test
	public void testDelete() throws TwitterException {
		long deleteIdLong = 123;
		doReturn(status).when(twitter).destroyStatus(anyLong());
		doReturn(deleteIdLong).when(status).getId();
		String res = twitterService.deleteTweet(TWEET_ID);
		assertEquals(res, DELETE_SUCCESS_MSG);
	}

	// Author Sruthi Chilukuri
	@Test
	public void testDeleteError() throws TwitterException {
		doReturn(null).when(twitter).destroyStatus(anyLong());
		String res = twitterService.deleteTweet(TWEET_ID);
		assertEquals(res, ERROR);
	}

	// Author Sruthi Chilukuri
	@Test(expected = TwitterException.class)
	public void testDeleteThrowsException() throws TwitterException {
		doThrow(new TwitterException(SOME_EXCEPTION)).when(twitter).destroyStatus(anyLong());
		twitterService.deleteTweet(TWEET_ID);
	}

	// Author Sruthi Chilukuri
	private TweetRequest getTweetRequest() {
		Geo geo = new Geo();
		geo.setLat(0.000000D);
		geo.setLon(0.000000D);
		TweetRequest request = new TweetRequest();
		request.setText("NewRequest");
		request.setGeo(geo);
		return request;
	}

	private static final String STATUS_RESPONSE = "{\n" +
			"    \"created_at\": \"Sun Oct 20 19:39:24 +0000 2019\",\n" +
			"    \"id\": 123,\n" +
			"    \"id_str\": \"123\",\n" +
			"    \"text\": \"testRun\",\n" +
			"    \"truncated\": false,\n" +
			"    \"entities\": {\n" +
			"        \"hashtags\": [],\n" +
			"        \"symbols\": [],\n" +
			"        \"user_mentions\": [],\n" +
			"        \"urls\": []\n" +
			"    },\n" +
			"    \"source\": \"<a href=\\\"https://monivarma.ml\\\" rel=\\\"nofollow\\\">monitwitterapi</a>\",\n" +
			"    \"in_reply_to_status_id\": null,\n" +
			"    \"in_reply_to_status_id_str\": null,\n" +
			"    \"in_reply_to_user_id\": null,\n" +
			"    \"in_reply_to_user_id_str\": null,\n" +
			"    \"in_reply_to_screen_name\": null,\n" +
			"    \"user\": {\n" +
			"        \"id\": 12345,\n" +
			"        \"id_str\": \"12345\",\n" +
			"        \"name\": \"Monica\",\n" +
			"        \"screen_name\": \"Monica71421795\",\n" +
			"        \"location\": \"\",\n" +
			"        \"description\": \"\",\n" +
			"        \"url\": null,\n" +
			"        \"entities\": {\n" +
			"            \"description\": {\n" +
			"                \"urls\": []\n" +
			"            }\n" +
			"        },\n" +
			"        \"protected\": false,\n" +
			"        \"followers_count\": 0,\n" +
			"        \"friends_count\": 0,\n" +
			"        \"listed_count\": 0,\n" +
			"        \"created_at\": \"Sun Sep 29 07:42:14 +0000 2010\",\n" +
			"        \"favourites_count\": 0,\n" +
			"        \"utc_offset\": null,\n" +
			"        \"time_zone\": null,\n" +
			"        \"geo_enabled\": false,\n" +
			"        \"verified\": false,\n" +
			"        \"statuses_count\": 21,\n" +
			"        \"lang\": null,\n" +
			"        \"contributors_enabled\": false,\n" +
			"        \"is_translator\": false,\n" +
			"        \"is_translation_enabled\": false,\n" +
			"        \"profile_background_color\": \"F5F8FA\",\n" +
			"        \"profile_background_image_url\": null,\n" +
			"        \"profile_background_image_url_https\": null,\n" +
			"        \"profile_background_tile\": false,\n" +
			"        \"profile_image_url\": \"http://abs.twimg.com/profile_images/default_profile_normal.png\",\n" +
			"        \"profile_image_url_https\": \"https://abs.twimg.com/sticky/defaul_normal.png\",\n" +
			"        \"profile_link_color\": \"1DA1F2\",\n" +
			"        \"profile_sidebar_border_color\": \"C0DEED\",\n" +
			"        \"profile_sidebar_fill_color\": \"DDEEF6\",\n" +
			"        \"profile_text_color\": \"333333\",\n" +
			"        \"profile_use_background_image\": true,\n" +
			"        \"has_extended_profile\": true,\n" +
			"        \"default_profile\": true,\n" +
			"        \"default_profile_image\": true,\n" +
			"        \"following\": false,\n" +
			"        \"follow_request_sent\": false,\n" +
			"        \"notifications\": false,\n" +
			"        \"translator_type\": \"none\"\n" +
			"    },\n" +
			"    \"geo\": null,\n" +
			"    \"coordinates\": null,\n" +
			"    \"place\": null,\n" +
			"    \"contributors\": null,\n" +
			"    \"is_quote_status\": false,\n" +
			"    \"retweet_count\": 0,\n" +
			"    \"favorite_count\": 0,\n" +
			"    \"favorited\": false,\n" +
			"    \"retweeted\": false,\n" +
			"    \"lang\": \"hu\"\n" +
			"}";

	private static final String NEW_STATUS_RESPONSE = "{\n" +
			"    \"created_at\": \"Sun Oct 20 19:39:24 +0000 2019\",\n" +
			"    \"id\": 123,\n" +
			"    \"id_str\": \"123\",\n" +
			"    \"text\": \"newTestRun\",\n" +
			"    \"user\": {\n" +
			"        \"id\": 12345,\n" +
			"        \"id_str\": \"12345\",\n" +
			"        \"name\": \"Monica\",\n" +
			"        \"screen_name\": \"Monica71421795\",\n" +
			"        \"follow_request_sent\": false,\n" +
			"        \"notifications\": false,\n" +
			"        \"translator_type\": \"none\"\n" +
			"    },\n" +
			"    \"geo\": null,\n" +
			"    \"coordinates\": null,\n" +
			"    \"lang\": \"hu\"\n" +
			"}";
}
