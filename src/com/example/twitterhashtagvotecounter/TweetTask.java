package com.example.twitterhashtagvotecounter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class TweetTask extends AsyncTask<String, String, String>{
	
	Context context;
	Twitter twitter;
	
	public TweetTask(Context context, Twitter twitter){
		this.context = context;
		this.twitter = twitter;
	}
	
	@Override
	protected String doInBackground(String... params) {
		
		String tweet = params[0];
		
		if(tweet==null || tweet.equals(""))
			return "Tweet not posted, since tweet is empty!";
		
		twitter4j.Status status = null;
		
		try {
			status = twitter.updateStatus(tweet);
		} catch (TwitterException e) {
			e.printStackTrace();
			return e.getErrorMessage();
		}
		
		return "Tweet is posted!";
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	}

}
