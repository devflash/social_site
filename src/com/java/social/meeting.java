package com.java.social;

public class meeting {
	String meid;
	String purpose;
	String groupName;
	String time;
	String meetDate;
	String place;
	String groupImg;
	public meeting(String meid, String purpose, String groupName, String time, String meetDate, String place,
			String groupImg) {
		super();
		this.meid = meid;
		this.purpose = purpose;
		this.groupName = groupName;
		this.time = time;
		this.meetDate = meetDate;
		this.place = place;
		this.groupImg = groupImg;
	}
	public String getMeid() {
		return meid;
	}
	public void setMeid(String meid) {
		this.meid = meid;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMeetDate() {
		return meetDate;
	}
	public void setMeetDate(String meetDate) {
		this.meetDate = meetDate;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getGroupImg() {
		return groupImg;
	}
	public void setGroupImg(String groupImg) {
		this.groupImg = groupImg;
	}
	
}
