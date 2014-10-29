package com.example.twitterhashtagvotecounter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddNominee extends Activity implements OnClickListener {
	EditText var_nominee_name, var_nominee_hashtag;
	Button add_nominee;	
	ListView nominees;
	TextView view_eventName, view_eventDescription;

	String nominee_name, nominee_hashtag, nominee_entry;
	String eventName, date;
	String pastName, pastHashtag;
	Long eventID, rowID;
	int x;

	private DBHelper dbHelper;
	private ArrayList<String> nominees_list = new ArrayList<String>();
	private ArrayList<String> hashtags = new ArrayList<String>();
	private ArrayList<String> names = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_nominee);

		var_nominee_name = (EditText) findViewById(R.id.nominee_name);
		var_nominee_hashtag = (EditText) findViewById(R.id.nominee_hashtag);
		add_nominee = (Button) findViewById(R.id.add_nominee_btn);
		view_eventName = (TextView) findViewById(R.id.view_eventName);
		view_eventDescription = (TextView) findViewById(R.id.view_eventDescription);
		nominees = (ListView) findViewById(R.id.nominee_list);

		add_nominee.setOnClickListener(this);

		Intent intent = getIntent();

		eventName = intent.getStringExtra("event_name");
		eventID = intent.getLongExtra("event_ID", 0);
		date = intent.getStringExtra("date");

		view_eventName.setText(eventName);
		view_eventDescription.setText(date);

		dbHelper =  new DBHelper(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.addnominee, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.save_nominee:
			for(x=0; x < nominees_list.size(); x++) {
				dbHelper.insertNominee( eventID, names.get(x), hashtags.get(x) );
			}
			startActivity(new Intent(this, ListOfEvents.class));
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View arg0) {
		nominee_name = var_nominee_name.getText().toString();
		nominee_hashtag = var_nominee_hashtag.getText().toString();

		nominee_entry = nominee_name + " - " + nominee_hashtag;
		
		if(hashtags.size() >= 1) {			
			if(checkNominee(nominee_hashtag)) {
				Toast.makeText(this, "entry must be unique", Toast.LENGTH_SHORT).show();
			}
			else if(checkName(nominee_name)) {
				Toast.makeText(this, "entry must be unique", Toast.LENGTH_SHORT).show();
			}
			else {
				hashtags.add(nominee_hashtag);
				names.add(nominee_name);
				nominees_list.add(nominee_entry);

				ArrayAdapter<String> nominee_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nominees_list);
				nominees.setAdapter(nominee_adapter);
			}
		}
		else {
			hashtags.add(nominee_hashtag);
			names.add(nominee_name);
			nominees_list.add(nominee_entry);

			ArrayAdapter<String> nominee_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nominees_list);
			nominees.setAdapter(nominee_adapter);

		}
		
	}

	public boolean checkNominee(String nominee_hashtag) {
		for(x=0; x < hashtags.size(); x++) {
			if(nominee_hashtag.matches(hashtags.get(x))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkName(String nominee_name) {
		for(x=0; x < names.size(); x++) {
			if(nominee_name.matches(names.get(x))) {
				return true;
			}
		}
		return false;
	}
}