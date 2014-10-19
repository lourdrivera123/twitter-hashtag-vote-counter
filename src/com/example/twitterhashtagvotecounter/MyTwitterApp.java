package com.example.twitterhashtagvotecounter;

import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Application;

public class MyTwitterApp extends Application{
	
	private static MyTwitterApp singleton;
	
	//Returns the application instance
	public static MyTwitterApp getInstance(){
		return singleton;
	}
	
	public static final String CONSUMER_KEY = "Xpoi0r8h58EArIPiHxKnSPBeZ";
	public static final String CONSUMER_SECRET = "gMqpYxHx2b1NlEmCAIixnlLKO2V2ZTSgoQn8hragXx9Bg1Odnd";
	public static final String ACCESS_TOKEN = "2548015796-tm5Um7zvDzwKLsSwcJctrPGBY8wwFC3SLtXviY8";
	public static final String ACCESS_TOKEN_SECRET = "xfYkiQrskvzDJw7ms9HiD4TrG7NQw46zFjovk2GxkVlgV";
	
	public ConfigurationBuilder configBuilder;
	public TwitterFactory defaultTwitterFactory;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		singleton = this;
		
		//set up the configuration 
		configBuilder = new ConfigurationBuilder();
		configBuilder.setDebugEnabled(true);
		configBuilder.setOAuthConsumerKey(CONSUMER_KEY);
		configBuilder.setOAuthConsumerSecret(CONSUMER_SECRET);
		configBuilder.setOAuthAccessToken(ACCESS_TOKEN);
		configBuilder.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		defaultTwitterFactory = new TwitterFactory(configBuilder.build());
	}
	
}
