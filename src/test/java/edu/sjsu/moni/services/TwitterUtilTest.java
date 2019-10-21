package edu.sjsu.moni.services;

import edu.sjsu.moni.models.TweetResponse;
import org.junit.Test;
import twitter4j.Status;
import twitter4j.TwitterObjectFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


// Author Geethu Padachery
public class TwitterUtilTest {

	@Test
	public void testGetTweetResponse() throws Exception {
		Status status = TwitterObjectFactory.createStatus(STATUS_RESPONSE);

		TweetResponse response = TwitterUtil.getTweetResponse(status);

		assertNotNull(response);
		assertEquals(response.getText(), "newStatus");
		assertEquals(response.getId(), "123");
		assertNotNull(response.getUser());
		assertEquals(response.getUser().getName(), "Monica");
		assertEquals(response.getUser().getScreenName(), "MonicaTest");
		assertEquals(response.getUser().getFollowersCount(), 100);
		assertEquals(response.getUser().getFriendsCount(), 200);
		assertEquals(response.getGeo().getLat(), 40.74118764D, 0.01);
		assertEquals(response.getGeo().getLon(), -73.9998279D, 0.01);
	}

	private static final String STATUS_RESPONSE = "{\n" +
			"  \"created_at\": \"Sun Oct 20 19:39:24 +0000 2019\",\n" +
			"  \"id\": 123,\n" +
			"  \"id_str\": \"123\",\n" +
			"  \"text\": \"newStatus\",\n" +
			"  \"truncated\": false,\n" +
			"  \"entities\": {\n" +
			"    \"hashtags\": [],\n" +
			"    \"symbols\": [],\n" +
			"    \"user_mentions\": [],\n" +
			"    \"urls\": []\n" +
			"  },\n" +
			"  \"in_reply_to_status_id\": null,\n" +
			"  \"in_reply_to_status_id_str\": null,\n" +
			"  \"in_reply_to_user_id\": null,\n" +
			"  \"in_reply_to_user_id_str\": null,\n" +
			"  \"in_reply_to_screen_name\": null,\n" +
			"  \"user\": {\n" +
			"    \"id\": 12345,\n" +
			"    \"id_str\": \"12345\",\n" +
			"    \"name\": \"Monica\",\n" +
			"    \"screen_name\": \"MonicaTest\",\n" +
			"    \"location\": \"\",\n" +
			"    \"description\": \"\",\n" +
			"    \"url\": null,\n" +
			"    \"entities\": {\n" +
			"      \"description\": {\n" +
			"        \"urls\": []\n" +
			"      }\n" +
			"    },\n" +
			"    \"protected\": false,\n" +
			"    \"followers_count\": 100,\n" +
			"    \"friends_count\": 200,\n" +
			"    \"listed_count\": 0,\n" +
			"    \"created_at\": \"Sun Sep 29 07:42:14 +0000 2019\",\n" +
			"    \"favourites_count\": 0,\n" +
			"    \"utc_offset\": null,\n" +
			"    \"time_zone\": null,\n" +
			"    \"geo_enabled\": false,\n" +
			"    \"verified\": false,\n" +
			"    \"statuses_count\": 21,\n" +
			"    \"lang\": null,\n" +
			"    \"contributors_enabled\": false,\n" +
			"    \"is_translator\": false,\n" +
			"    \"is_translation_enabled\": false,\n" +
			"    \"profile_background_color\": \"F5F8FA\",\n" +
			"    \"profile_background_image_url\": null,\n" +
			"    \"profile_background_image_url_https\": null,\n" +
			"    \"profile_background_tile\": false,\n" +
			"    \"profile_image_url\": \"http://abs.twimgenormal.png\",\n" +
			"    \"profile_image_url_https\": \"https://abs.twimrmal.png\",\n" +
			"    \"profile_link_color\": \"1DA1F2\",\n" +
			"    \"profile_sidebar_border_color\": \"C0DEED\",\n" +
			"    \"profile_sidebar_fill_color\": \"DDEEF6\",\n" +
			"    \"profile_text_color\": \"333333\",\n" +
			"    \"profile_use_background_image\": true,\n" +
			"    \"has_extended_profile\": true,\n" +
			"    \"default_profile\": true,\n" +
			"    \"default_profile_image\": true,\n" +
			"    \"following\": false,\n" +
			"    \"follow_request_sent\": false,\n" +
			"    \"notifications\": false,\n" +
			"    \"translator_type\": \"none\"\n" +
			"  },\n" +
			"  \"geo\": {\n" +
			"    \"type\": \"Point\",\n" +
			"    \"coordinates\": [\n" +
			"      40.74118764,\n" +
			"      -73.9998279\n" +
			"    ]\n" +
			"  },\n" +
			"  \"coordinates\": {\n" +
			"    \"type\": \"Point\",\n" +
			"    \"coordinates\": [\n" +
			"      -73.9998279,\n" +
			"      40.74118764\n" +
			"    ]\n" +
			"  },\n" +
			"  \"place\": {\n" +
			"    \"id\": \"01a9a39529b27f36\",\n" +
			"    \"place_type\": \"city\",\n" +
			"    \"name\": \"SunnyvaleTest\",\n" +
			"    \"full_name\": \"SunnyvaleTest, CA\",\n" +
			"    \"country_code\": \"US\",\n" +
			"    \"country\": \"United States\",\n" +
			"    \"bounding_box\": {\n" +
			"      \"type\": \"Polygon\",\n" +
			"      \"coordinates\": [\n" +
			"        [\n" +
			"          [\n" +
			"            -74.026675,\n" +
			"            40.683935\n" +
			"          ],\n" +
			"          [\n" +
			"            -74.026675,\n" +
			"            40.877483\n" +
			"          ],\n" +
			"          [\n" +
			"            -73.910408,\n" +
			"            40.877483\n" +
			"          ],\n" +
			"          [\n" +
			"            -73.910408,\n" +
			"            40.683935\n" +
			"          ]\n" +
			"        ]\n" +
			"      ]\n" +
			"    }\n" +
			"  },\n" +
			"  \"contributors\": null,\n" +
			"  \"is_quote_status\": false,\n" +
			"  \"retweet_count\": 0,\n" +
			"  \"favorite_count\": 0,\n" +
			"  \"favorited\": false,\n" +
			"  \"retweeted\": false,\n" +
			"  \"lang\": \"hu\"\n" +
			"}";
}
