package com.example.twitterhashtagvotecounter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter{
	
	private ArrayList<Event> mEvents = new ArrayList<Event>();
	private Context mContext;
	private LayoutInflater mInflater;
	
	public EventAdapter(Context context, ArrayList<Event> events){
		mContext = context;
		mEvents = events;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);	
	}
	
	@Override
	public int getCount() {
		return mEvents.size();
	}

	@Override
	public Object getItem(int position) {
		return mEvents.get(position);
	}

	@Override
	public long getItemId(int position) {
		return mEvents.get(position).getID();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Event event = (Event) getItem(position);
		ViewHolder holder;
		
		if(convertView==null){
			
			//load the desired ui for each item
			convertView = mInflater.inflate(R.layout.tweet_item, parent, false);
			
			holder = new ViewHolder();
			
			holder.eventName = (TextView) convertView.findViewById(R.id.eventName);
			holder.eventStatus = (TextView) convertView.findViewById(R.id.eventStatus);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.event = event;
		
		switch(event.getEventStatus()){
			
			case "pending":
				
				holder.eventName.setText(event.getName());
				holder.eventStatus.setText("Haven't Started Yet");
				holder.eventName.setTextColor(Color.GRAY);
				
				break;
				
			case "started":
				
				holder.eventName.setText(event.getName());
				holder.eventStatus.setText(event.getStartEvent());
				holder.eventName.setTextColor(Color.GREEN);
				
				break;
				
			case "ended": 
				
				holder.eventName.setText(event.getName());
				holder.eventStatus.setText(event.getEndEvent());
				holder.eventName.setTextColor(Color.RED);
				
				break;
		}
		
		return convertView;
	}
	
	public void removeItem(int position){
		mEvents.remove(position);
	}
	
	static class ViewHolder {
		
		public TextView eventName;
		public TextView eventStatus;
		public Event event;
		
	}
}