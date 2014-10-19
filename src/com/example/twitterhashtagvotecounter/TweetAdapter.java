package com.example.twitterhashtagvotecounter;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TweetAdapter extends BaseAdapter{
	
	private List<twitter4j.Status> mTweets = new ArrayList<twitter4j.Status>();
	private Context mContext;
	private LayoutInflater inflater;
	
	public TweetAdapter(Context context, List<twitter4j.Status> tweets) {
		mContext = context;
		mTweets = tweets;
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mTweets.size();
	}

	@Override
	public Object getItem(int position) {
		return mTweets.get(position);
	}

	@Override
	public long getItemId(int position) {
		return  mTweets.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		twitter4j.Status status = (Status) getItem(position);
		
		if(convertView==null){
			
			convertView = inflater.inflate(R.layout.tweet_item, parent, false);
			
		}
		
		TextView userView = (TextView) convertView.findViewById(R.id.user);
		userView.setText("@"+status.getUser().getScreenName());
		
		TextView tweetView = (TextView) convertView.findViewById(R.id.tweet);
		tweetView.setText(status.getText());
		
		return convertView;
	}

}
