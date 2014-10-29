package com.example.twitterhashtagvotecounter;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	public static final String TAG = "DBHelper";

	public static final String DB_NAME = "hashtovote";
	public static final int DB_VERSION = 3;

	public static final String EVENT_TABLE_NAME = "event";
	public static final String COL_ID = "_id";
	public static final String COL_NAME = "name";
	public static final String COL_START_EVENT = "start_event";
	public static final String COL_END_EVENT = "end_event";
	public static final String COL_STATUS = "status";

	public static final String NOMINEE_TABLE_NAME = "nominees";
	public static final String COL_NOM_ID = "_id";
	public static final String COL_EVENT_ID = "event_id";
	public static final String COL_NOM_NAME = "name";
	public static final String COL_HASHTAG = "description";

	public static final String sql = String
			.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s DATE, %s DATE, %s TEXT )",
					EVENT_TABLE_NAME, COL_ID, COL_NAME, COL_START_EVENT,
					COL_END_EVENT, COL_STATUS);

	public static final String sql1 = String
			.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s Long, %s TEXT, %s TEXT )",
					NOMINEE_TABLE_NAME, COL_NOM_ID, COL_EVENT_ID, COL_NOM_NAME,
					COL_HASHTAG);

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(sql);

		db.execSQL(sql1);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		Log.d(TAG, "onUpgrade!");
		db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE_NAME);

		onCreate(db);
	}

	public long insertEvent(String eventName, String startEvent,
			String endEvent, String eventStatus) {
		// insert statement
		ContentValues values = new ContentValues();
		values.put(COL_NAME, eventName);
		values.put(COL_START_EVENT, startEvent);
		values.put(COL_END_EVENT, endEvent);
		values.put(COL_STATUS, eventStatus);

		SQLiteDatabase db = getWritableDatabase();

		long rowID = db.insert(EVENT_TABLE_NAME, null, values);

		db.close();

		return rowID;
	}

	public Long insertNominee(Long eventID, String nomineeName,
			String nomineeHashtag) {
		// insert statement
		ContentValues values = new ContentValues();
		values.put(COL_EVENT_ID, eventID);
		values.put(COL_NAME, nomineeName);
		values.put(COL_HASHTAG, nomineeHashtag);

		SQLiteDatabase db = getWritableDatabase();

		long rowID = db.insert(NOMINEE_TABLE_NAME, null, values);

		db.close();

		return rowID;
	}

	public ArrayList<Event> getAllEvents() {

		ArrayList<Event> events = new ArrayList<Event>();

		SQLiteDatabase db = getWritableDatabase();

		String sql = "SELECT * FROM " + EVENT_TABLE_NAME;

		Cursor cur = db.rawQuery(sql, null);

		while (cur.moveToNext()) {

			int id = cur.getInt(0);
			String eventName = cur.getString(1);
			String startEvent = cur.getString(2);
			String endEvent = cur.getString(3);
			String eventStatus = cur.getString(4);
			
			Event event = new Event();
			
			event.setID(id);
			event.setName(eventName);
			event.setStartEvent(startEvent);
			event.setEndEvent(endEvent);
			event.setEventStatus(eventStatus);

			events.add(event);
		}

		cur.close();
		db.close();

		return events;
	}

}
