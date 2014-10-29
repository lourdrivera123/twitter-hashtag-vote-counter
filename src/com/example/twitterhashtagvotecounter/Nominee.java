package com.example.twitterhashtagvotecounter;

import java.io.Serializable;

//implement serializable so that the object instance of this class can be passed via intent
public class Nominee implements Serializable{
	private static final long serialVersionUID = 1L;
	private int mID = -1;
	private Long mEventID = null;
	private String mName = "";
	private String mHashtag = "";
	
	public Nominee(){
		
	}
	
	public Nominee(int id, Long mEventI, String name, String hashtag) {
		mID = id;
		mName = name;
		mHashtag = hashtag;	
	}
	
	public int getID() {
		return mID;
	}

	public void setID(int id) {
		this.mID = id;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}
	
	public String getHashtag() {
		return mHashtag;
	}

	public void setHashtag(String name) {
		this.mHashtag = name;
	}
	
	public Long getEventID() {
		return mEventID;
	}
	
}
