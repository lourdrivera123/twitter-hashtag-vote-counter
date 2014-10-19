package com.example.twitterhashtagvotecounter;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class TweetsSearch extends Activity implements OnClickListener {
	
	private EditText mKeywordEditText;
	private ImageButton mSearchButton;
	private Twitter mTwitter;
	private ListView tweetsListView;
	private QueryResult twitterResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweets_search);
		
		mKeywordEditText = (EditText) findViewById(R.id.keyword);
		mSearchButton = (ImageButton)findViewById(R.id.search_button);
		tweetsListView = (ListView) findViewById(R.id.tweets_listview);
		
		mSearchButton.setOnClickListener(this);
		
		MyTwitterApp appInstance = MyTwitterApp.getInstance();
		mTwitter = appInstance.defaultTwitterFactory.getInstance();
	}
	
	@Override
	public void onClick(View v) {
		
		String keyword = mKeywordEditText.getText().toString();
		
		new SearchTweetsTask(this).execute(new String[]{keyword});
		
	}
	
	private class SearchTweetsTask extends AsyncTask<String,Void,String>{
		
		private Context mContext;
		
		public SearchTweetsTask(Context context){
			mContext = context;
		}
		
		@Override
		protected String doInBackground(String... str) {
			
			String keyword = str[0];
			
			 Query query = new Query(keyword);
			 
			 twitterResult = null;
			 
			 try {
				 
				 twitterResult = mTwitter.search(query);
				
			} catch (TwitterException e) {
				e.printStackTrace();
			}
			 
			if(twitterResult==null){
				return "No Tweets!";
			}
			else {
				
				for (twitter4j.Status status : twitterResult.getTweets()) {
					
					Log.d("Twitter", status.getText());
					
				}
				
				return "Tweets: "+twitterResult.getTweets().size();
			}
			
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			if(result.equals("No Tweets!")){
				//do nothing
			} else {
				
				TweetAdapter adapter = new TweetAdapter(mContext,twitterResult.getTweets());
				tweetsListView.setAdapter(adapter);
				
			}
		}
		
	}

}
