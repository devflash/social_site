package com.java.social;

import java.sql.Blob;

import javax.servlet.http.Part;

public class social {

	int uid;
	String first_name;
	String last_name;
	String email;
	String password;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	int phone;
	String address;
	String post_name;
	Part photo;
	String displayPicture;
	public social(String first_name, String last_name, String email, String password) {
		
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
	}

	public social(int uid, String first_name, String last_name, String email,String displayPicture) {
		
		this.uid = uid;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.displayPicture=displayPicture;
	}

	public String getDisplayPicture() {
		return displayPicture;
	}

	public void setDisplayPicture(String displayPicture) {
		this.displayPicture = displayPicture;
	}

	public social(String first_name, String last_name, String email, int phone, String address, String post_name,
			Part photo) {

		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.post_name = post_name;
		this.photo = photo;
	}
	public social(int uid, String first_name, String last_name, String email, int phone, String address,
			String post_name, Part photo) {
		
		this.uid = uid;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.post_name = post_name;
		this.photo = photo;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPost_name() {
		return post_name;
	}
	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}
	public Part getPhoto() {
		return photo;
	}
	public void setPhoto(Part photo) {
		this.photo = photo;
	}
	
}
