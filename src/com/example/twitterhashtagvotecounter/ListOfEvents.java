package com.example.twitterhashtagvotecounter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListOfEvents extends Activity implements OnItemClickListener {
	
	private ArrayList<Event> mEvents = new ArrayList<Event>();
	private ListView mEventsListView;
	private EventAdapter mEventAdapter;

	@Override
	protected void onResume() {
		super.onResume();
		
		//read all the tasks
				DBHelper dbHelper = new DBHelper(this);
				mEvents = dbHelper.getAllEvents();
				
				mEventAdapter = new EventAdapter(this, mEvents);
				mEventsListView.setAdapter(mEventAdapter);
				
				//click listener for the list view for viewing the task's details
				mEventsListView.setOnItemClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_of_events);
		
		mEventsListView = (ListView) findViewById(R.id.list_of_events);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_of_events, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		
		case R.id.create_event:
			startActivity(new Intent(this, CreateEvent.class));
			break;
			
		case R.id.action_settings:
			startActivity(new Intent(this, ViewSettings.class));
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		//get the task data
		Event event = (Event) mEventAdapter.getItem(position);
		
		if ( event.getEventStatus().equals("pending") )
		{
			
			Toast.makeText(this, "Please Start This Event", Toast.LENGTH_SHORT).show();
		}
		
	}

}