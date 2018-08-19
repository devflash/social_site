package com.java.social;

import java.sql.Blob;

import javax.servlet.http.Part;

public class post {
	int postid;
	String title;
	String content;
	Part postimage;
	String post64image;
	int grpid;
	public int getGrpid() {
		return grpid;
	}
	public void setGrpid(int grpid) {
		this.grpid = grpid;
	}
	public String getPost64image() {
		return post64image;
	}
	public void setPost64image(String post64image) {
		this.post64image = post64image;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Part getPostimage() {
		return postimage;
	}
	public void setPostimage(Part postimage) {
		this.postimage = postimage;
	}
	public post(String title, String content, Part postimage,int grpid) {
		
		this.title = title;
		this.content = content;
		this.postimage = postimage;
		this.grpid=grpid;
	}
	public post(int postid, String title, String content, Part postimage,int grpid) {
		
		this.postid = postid;
		this.title = title;
		this.content = content;
		this.postimage = postimage;
		this.grpid=grpid;
	}
	public post(int postid, String title, String content, String post64image, int grpid) {
		super();
		this.postid = postid;
		this.title = title;
		this.content = content;
		this.post64image = post64image;
		this.grpid = grpid;
	}
	
	
}
