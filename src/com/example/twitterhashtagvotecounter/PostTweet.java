package com.example.twitterhashtagvotecounter;

import twitter4j.Twitter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PostTweet extends Activity implements OnClickListener {
	
	private Button mPostButton;
	private EditText mTweetEditText;
	private Twitter mTwitter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_status);
		
		mPostButton = (Button) findViewById(R.id.post_button);
		mTweetEditText = (EditText) findViewById(R.id.tweet_editText);
		
		MyTwitterApp appInstance = MyTwitterApp.getInstance();
		
		mTwitter = appInstance.defaultTwitterFactory.getInstance();
		
		mPostButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		
		String tweet = mTweetEditText.getText().toString();
		String params[] = new String[]{tweet};
		new TweetTask(this,mTwitter).execute(params);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_line, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
		
			case R.id.action_search:
				
				startActivity(new Intent(this,TweetsSearch.class));
				
				break;
		
		}
		
		return super.onOptionsItemSelected(item);
	}
}
