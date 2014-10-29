package com.example.twitterhashtagvotecounter;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class ViewSettings extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Display the fragment as the main content.
        getFragmentManager().beginTransaction()
        		.replace(android.R.id.content, new SettingsFragment())
                .commit();
	}
	
	public static class SettingsFragment extends PreferenceFragment {
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        addPreferencesFromResource(R.xml.hashvote_preferences);
	    }
	   
	}

}
