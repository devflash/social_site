package com.java.social;

import java.sql.Blob;

import javax.servlet.http.Part;

public class group {
	int grpid;
	String grpName;
	String adminName;
	int adminId;
	String description;
	Part grpPhoto;
	int followers;
	public int getGrpid() {
		return grpid;
	}
	public void setGrpid(int grpid) {
		this.grpid = grpid;
	}
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public String getAdminName() {
		return adminName;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Part getGrpPhoto() {
		return grpPhoto;
	}
	public void setGrpPhoto(Part grpPhoto) {
		this.grpPhoto = grpPhoto;
	}
	public group(int grpid, String grpName, String adminName, int adminId, String description, Part grpPhoto,
			int followers) {
	
		this.grpid = grpid;
		this.grpName = grpName;
		this.adminName = adminName;
		this.adminId = adminId;
		this.description = description;
		this.grpPhoto = grpPhoto;
		this.followers = followers;
	}
	@Override
	public String toString() {
		return "group [grpid=" + grpid + ", grpName=" + grpName + ", adminName=" + adminName + ", adminId=" + adminId
				+ ", description=" + description + ", grpPhoto=" + grpPhoto + ", followers=" + followers + "]";
	}
	public group(int grpid,String grpName, String description) {
		this.grpid=grpid;
		this.grpName = grpName;
		this.description = description;
	}
	public group(String grpName, String adminName, int adminId, String description, Part grpPhoto, int followers) {
		
		this.grpName = grpName;
		this.adminName = adminName;
		this.adminId = adminId;
		this.description = description;
		this.grpPhoto = grpPhoto;
		this.followers = followers;
	}
	public group(String grpName) {
		
		this.grpName = grpName;
	}
	
}
