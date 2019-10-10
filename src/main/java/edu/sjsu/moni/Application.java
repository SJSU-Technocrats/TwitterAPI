package edu.sjsu.moni;

import edu.sjsu.moni.config.TwitterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private TwitterConfig twitterConfig;

	@Bean
	Twitter getTwitter() {
		Twitter twitter = TwitterFactory.getSingleton();
		AccessToken token = new AccessToken(twitterConfig.getAccessToken(), twitterConfig.getAccessTokenSecret());
		twitter.setOAuthConsumer(twitterConfig.getConsumerKey(), twitterConfig.getConsumerSecret());
		twitter.setOAuthAccessToken(token);
		return twitter;
	}
}