package com.example.twitterhashtagvotecounter;

import java.io.Serializable;

public class Event implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int mID = -1;
	private String mEventName = "";
	private String mStartEvent = "";
	private String mEndEvent = "";
	private String mEventStatus = "";
	
	public Event() {
		// TODO Auto-generated constructor stub
	}
	
	public Event(int id, String eventName, String startEvent, String endEvent, String eventStatus) {
		
		mID = id;
		mEventName = eventName;
		mStartEvent = startEvent;
		mEndEvent = endEvent;
		mEventStatus = eventStatus;
		
	}

	public int getID() {
		return mID;
	}

	public void setID(int id) {
		this.mID = id;
	}

	public String getName() {
		return mEventName;
	}

	public void setName(String name) {
		this.mEventName = name;
	}

	public String getStartEvent() {
		return mStartEvent;
	}

	public void setStartEvent(String startEvent) {
		this.mStartEvent = startEvent;
	}

	public String getEndEvent() {
		return mEndEvent;
	}
	
	public void setEndEvent(String endEvent) {
		this.mEndEvent = endEvent;
	}
	
	public String getEventStatus(){
		return mEventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.mEventStatus = eventStatus;
	}

}