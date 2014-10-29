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
import android.widget.Toast;

public class CreateEvent extends Activity implements OnClickListener {
	Button add_nominee_btn;
	EditText var_event_name, nominee_name, nominee_hashtag;
	Long eventID;
	ListView nominee_list;

	private DBHelper mDBHelper;
	private ArrayList<String> nominees_list = new ArrayList<String>();
	private ArrayList<String> hashtags = new ArrayList<String>();
	private ArrayList<String> names = new ArrayList<String>();

	String eventName, eventStartDate, eventEndDate, status;
	String nomineeName, nomineeHashtag, nominee_entry;
	int x;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_event);

		eventStartDate = "";
		eventEndDate = "";
		status = "pending";
		var_event_name = (EditText) findViewById(R.id.event_name);
		nominee_name = (EditText) findViewById(R.id.nominee_name);
		nominee_hashtag = (EditText) findViewById(R.id.nominee_hashtag);
		add_nominee_btn = (Button) findViewById(R.id.add_nominee_btn);
		nominee_list = (ListView) findViewById(R.id.nominee_list);

		add_nominee_btn.setOnClickListener(this);
		mDBHelper =  new DBHelper(this);
	}

	@Override
	public void onClick(View v) {
		if(checkIfEmpty(var_event_name, nominee_name, nominee_hashtag)) {
			
		}
		else {
			checkVariables(var_event_name, nominee_name, nominee_hashtag);
		}
	}

	public boolean checkIfEmpty(EditText eName, EditText eNominee, EditText eHashtag) {
		String hash = eHashtag.getText().toString();
		String str = hash.substring(0, 1);
		
		if(eName.getText().toString().matches("")) {
			Toast.makeText(this, "Event Name is required", Toast.LENGTH_SHORT).show();
			return true;
		}
		else if(eNominee.getText().toString().matches("")) {
			Toast.makeText(this, "Nominee Name is required", Toast.LENGTH_SHORT).show();
			return true;
		}
		else if(eHashtag.getText().toString().matches("")) {
			Toast.makeText(this, "Hashtag is required", Toast.LENGTH_SHORT).show();
			return true;
		}
		else if(!str.equals("#")) {
			Toast.makeText(this, "Invalid Hashtag", Toast.LENGTH_SHORT).show();
			return true;
		}
		else if(str.matches("#")) {
			return false;
		}
		else {
			return false;
		}
	}

	public void checkVariables(EditText eName, EditText eNominee, EditText eHashtag) {
		nomineeName = eNominee.getText().toString();
		nomineeHashtag = eHashtag.getText().toString();
		eventName = eName.getText().toString();

		nominee_entry = nomineeName + " - " + nomineeHashtag;

		if(hashtags.size() >= 1) {			
			if(checkNominee(nomineeHashtag)) {
				Toast.makeText(this, "entry must be unique", Toast.LENGTH_SHORT).show();
			}
			else if(checkName(nomineeName)) {
				Toast.makeText(this, "entry must be unique", Toast.LENGTH_SHORT).show();
			}
			else {
				hashtags.add(nomineeHashtag);
				names.add(nomineeName);
				nominees_list.add(nominee_entry);

				ArrayAdapter<String> nominee_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nominees_list);
				nominee_list.setAdapter(nominee_adapter);
			}
		}
		else {
			hashtags.add(nomineeHashtag);
			names.add(nomineeName);
			nominees_list.add(nominee_entry);

			ArrayAdapter<String> nominee_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nominees_list);
			nominee_list.setAdapter(nominee_adapter);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.create_event, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.action_save:
			if(checkIfEmpty(var_event_name, nominee_name, nominee_hashtag)) {
				
			}
			else {
				eventID = mDBHelper.insertEvent(eventName, eventStartDate, eventEndDate, status);

				for(x=0; x < nominees_list.size(); x++) {
					mDBHelper.insertNominee( eventID, names.get(x), hashtags.get(x) );
				}

				startActivity(new Intent(this, ListOfEvents.class));
			}
		}
		return super.onOptionsItemSelected(item);
	}
}