package com.java.social;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import sun.misc.BASE64Encoder;




public class userDbUtil {
private DataSource datasource;
public userDbUtil(DataSource thedataSource)
{
	datasource=thedataSource;
}
public boolean validateUser(String emailAddress, String password) throws SQLException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	try
	{
		con=datasource.getConnection();
		System.out.println("test");
		String sql="select * from userlogin where email=? AND password=?";
		ps=con.prepareStatement(sql);
		ps.setString(1, emailAddress);
		ps.setString(2, password);
		rs=ps.executeQuery();
		if(rs.next())
			return true;
		else
			return false;
	}
	finally
	{
		close(con,ps,rs);
	}
	
}
private void close(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
		if(rs!=null)
		{
			rs.close();
		}
		if(ps!=null)
		{
			ps.close();
		}
		if(con!=null)
		{
			con.close();
		}
	
}
public void addUser(social s) throws SQLException, IOException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	BufferedImage image=null;
	File f=null;
	try
	{
		
		con=datasource.getConnection();
		f=new File("D:\\temp\\mountain.jpg");
		
		
		String sql="Insert into userinfo(firstname,lastname,email,profilephoto) values(?,?,?,?)";
		ps=con.prepareStatement(sql);
		ps.setString(1,s.getFirst_name());
		ps.setString(2,s.getLast_name());
		ps.setString(3,s.getEmail());
		ps.setBlob(4,new FileInputStream(f),f.length());
		ps.execute();
		String sql1="Insert into userlogin(email,password) values(?,?)";
		ps=con.prepareStatement(sql1);
		ps.setString(1,s.getEmail());
		ps.setString(2,s.getPassword());
		ps.execute();
	}
	finally
	{
		close(con, ps, rs);
	}
}
public void addPost(post p,int uid) throws SQLException, IOException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	try
	{
		con=datasource.getConnection();
		String sql="insert into postdetails(title,picture,content,grpid) values(?,?,?,?)";
		ps=con.prepareStatement(sql);
		InputStream is=p.getPostimage().getInputStream();
		ps.setString(1,p.getTitle());
		ps.setBlob(2,is);
		ps.setString(3, p.getContent());
		ps.setInt(4,p.getGrpid());
		ps.execute();
		String sql1="select postid from postdetails where postid=(select max(postid) from postdetails)";
		ps=con.prepareStatement(sql1);
		rs=ps.executeQuery();
		if(rs.next())
		{
			String sql2="insert into stu_post(stdid,pstid) values(?,?)";
			ps=con.prepareStatement(sql2);
			System.out.println("test");
			ps.setInt(1,uid);
			ps.setInt(2,rs.getInt(1));
			ps.execute();

		}
		
	}
	finally
	{
		close(con, ps, rs);
	}
	
}
public social getInfo(String emailAddress) throws SQLException, IOException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	try
	{
		social temp=null;
		int userId;
		String userFirst;
		String userLast;
		Blob userPhoto;
		con=datasource.getConnection();
		String sql="select userid,firstname,lastname,profilephoto from userinfo where email=?";
		ps=con.prepareStatement(sql);
		ps.setString(1,emailAddress);
		rs=ps.executeQuery();
		
		if(rs.next())
		{
			userId=Integer.parseInt(rs.getString("userid"));
			userFirst=rs.getString("firstname");
			userLast=rs.getString("lastname");
			userPhoto= rs.getBlob("profilephoto");
			InputStream inputStream=userPhoto.getBinaryStream();
			System.out.println("Getting info");
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			byte buffer[]=new byte[4096];
			int byteRead=-1;
			 while ((byteRead = inputStream.read(buffer)) != -1) {
                 outputStream.write(buffer, 0, byteRead);                  
             }
			 byte imageBytes[]=outputStream.toByteArray();
			 String imageString=Base64.getEncoder().encodeToString(imageBytes);
			temp=new social(userId,userFirst,userLast, emailAddress,imageString);
			
			inputStream.close();
			outputStream.close();
		}
		return temp;
		
	}
	finally
	{
		close(con, ps, rs);
	}
	
}
public void addGroup(group newGroup) throws SQLException, IOException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	try
	{
		
		con=datasource.getConnection();
		String sql="insert into groupinfo(grpname,adminname,description,profilephoto,userid,followers) values(?,?,?,?,?,?)";
		ps=con.prepareStatement(sql);
		ps.setString(1,newGroup.getGrpName());
		ps.setString(2, newGroup.getAdminName());
		ps.setString(3,newGroup.getDescription());
		InputStream is=newGroup.getGrpPhoto().getInputStream();
		ps.setBlob(4,is);
		ps.setInt(5,newGroup.getAdminId());
		ps.setInt(6,newGroup.getFollowers());
		ps.execute();
		String sql1="select grpid from groupinfo where grpid=(select max(grpid) from groupinfo)";
		ps=con.prepareStatement(sql1);
		rs=ps.executeQuery();
		if(rs.next())
		{
			String sql2="insert into stu_grp(sid,gid) values(?,?)";
			ps=con.prepareStatement(sql2);
			System.out.println("test");
			ps.setInt(1,newGroup.getAdminId());
			ps.setInt(2,rs.getInt(1));
			ps.execute();
		}
	}
	finally
	{
		close(con, ps, rs);
	}
	
}
public List<String> getUserGroups(int uid) throws SQLException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	List<String> temp=new ArrayList<>();
	try
	{
		con=datasource.getConnection();
		String sql="select grpname from groupinfo where grpid in(select gid from stu_grp where sid=?)";
		ps=con.prepareStatement(sql);
		ps.setInt(1, uid);
		rs=ps.executeQuery();
		
		while(rs.next())
		{
			temp.add(rs.getString("grpname"));
		}
		return temp;
	}
	finally
	{
		close(con, ps, rs);
	}

}
public int getGroupId(String groupName) throws SQLException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	int grpid=0;
	try
	{
		con=datasource.getConnection();
		String sql="select grpid from groupinfo where grpname=?";
		ps=con.prepareStatement(sql);
		ps.setString(1,groupName);
		rs=ps.executeQuery();
		if(rs.next())
		{
			grpid=rs.getInt(1);
		}
		return grpid;
	}
	finally
	{
		close(con, ps, rs);
	}

	
}
public List<post> myGroupPost(int uid) throws SQLException, IOException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	Blob postDisplayImage;
	List<post> temp=new ArrayList<>();
	try
	{
		con=datasource.getConnection();
		System.out.println("In mypost method user id="+uid);
		String sql="select * from postdetails where grpid in(select gid from stu_grp where sid=?)";
		ps=con.prepareStatement(sql);
		ps.setInt(1,uid);
		rs=ps.executeQuery();
		while(rs.next())
		{
			postDisplayImage=rs.getBlob("picture");
			InputStream inputStream=postDisplayImage.getBinaryStream();
			System.out.println("Getting info");
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			byte buffer[]=new byte[4096];
			int byteRead=-1;
			 while ((byteRead = inputStream.read(buffer)) != -1) {
                 outputStream.write(buffer, 0, byteRead);                  
             }
			 byte imageBytes[]=outputStream.toByteArray();
			 String imageString=Base64.getEncoder().encodeToString(imageBytes);
			post tempPost=new post(rs.getInt("postid"),rs.getString("title"),rs.getString("content"),imageString,rs.getInt("grpid"));
			temp.add(tempPost);
		}
	}
	finally
	{
		
	}
	return temp;
}
public void saveMeeting(String purpose, String group, String time, String place,String date) throws SQLException {
	Connection con=null;
	PreparedStatement ps=null;
	try
	{
		con=datasource.getConnection();
		String sql="insert into meetings(purpose,groupName,meetTime,place,meetDate) values(?,?,?,?,?)";
		ps=con.prepareStatement(sql);
		ps.setString(1, purpose);
		ps.setString(2, group);
		ps.setString(3, time);
		ps.setString(4,place);
		ps.setString(5, date);
		ps.execute();
	}
	finally
	{
		close(con, ps, null);
	}
	
}
public List<meeting> myMeetings(int uid) throws SQLException, IOException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	List<meeting> temp=new ArrayList<>();
	String groupImg;
	try
	{
		con=datasource.getConnection();
		List<String> groupName=getUserGroups(uid);
		for(String name:groupName)
		{
			groupImg=getGroupImg(name);
		String sql="select * from meetings where groupName=?";
		ps=con.prepareStatement(sql);
		ps.setString(1,name);
		rs=ps.executeQuery();
		while(rs.next())
		{
			meeting m=new meeting(rs.getString("meid"), rs.getString("purpose"), rs.getString("groupName"), rs.getString("meetTime"), rs.getString("meetDate"), rs.getString("place"), groupImg);
			temp.add(m);
		}
		}
		return temp;
	}
	finally
	{
		close(con,ps,rs);
	}
	
}
private String getGroupImg(String name) throws SQLException, IOException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String img=null;
	Blob blobImg;
	try
	{
		con=datasource.getConnection();
		String sql="select profilephoto from groupinfo where grpname=?";
		ps=con.prepareStatement(sql);
		ps.setString(1, name);
		rs=ps.executeQuery();
		if(rs.next())
		{
			blobImg=rs.getBlob(1);
			InputStream inputStream=blobImg.getBinaryStream();
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			byte buffer[]=new byte[4096];
			int byteRead=-1;
			 while ((byteRead = inputStream.read(buffer)) != -1) {
                 outputStream.write(buffer, 0, byteRead);                  
             }
			 byte imageBytes[]=outputStream.toByteArray();
			 img=Base64.getEncoder().encodeToString(imageBytes);
			
		}
		return img;
	}
	finally
	{
		close(con, ps, rs);
	}
	
}
public List<group> groupList(int adminId) throws SQLException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	List<group> tempGroupList=new ArrayList<>();
	try
	{
		con=datasource.getConnection();
		System.out.println(adminId);
		String sql="select * from groupinfo where userid!=?";
		
		
		ps=con.prepareStatement(sql);
		ps.setInt(1, adminId);
		rs=ps.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getInt("grpid"));
			group tempGroup=new group(rs.getInt("grpid"),rs.getString("grpname"),rs.getString("description") );
			System.out.println(tempGroup);
			tempGroupList.add(tempGroup);
		}
		return tempGroupList;
	}
	finally
	{
		
	}

}
public int addUserGroup(int stdid, int grpid) throws SQLException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	int flag=0;
	try
	{
		con=datasource.getConnection();
		String sql="select * from stu_grp where gid=? and sid=?";
		ps=con.prepareStatement(sql);
		ps.setInt(2, stdid);
		ps.setInt(1, grpid);
		rs=ps.executeQuery();
		if(rs.next())
		{
			flag=1;
		}
		else
		{
			flag=0;
		String sql1="insert into stu_grp(sid,gid) values(?,?)";
		ps=con.prepareStatement(sql1);
		ps.setInt(1, stdid);
		ps.setInt(2, grpid);
		ps.execute();
		}
		return flag;
	}
	finally
	{
		
	}
	
}
public boolean checkDuplicatemail(String email) throws SQLException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	boolean flag=true;
	try
	{
		con=datasource.getConnection();
		String sql="SELECT * FROM userinfo where email=?";
		ps=con.prepareStatement(sql);
		ps.setString(1, email);
		rs=ps.executeQuery();
		if(rs.next())
		{
			flag=false;
		}
		
	}
	finally
	{
		
	}
	return flag;
}
public List<post> myOwnPost(int uid) throws SQLException, IOException {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	Blob postDisplayImage;
	List<post> temp=new ArrayList<>();
	try
	{
		con=datasource.getConnection();
		System.out.println("In mypost method user id="+uid);
		String sql="select * from postdetails where postid In (select pstid from stu_post where stdid=? )";
		ps=con.prepareStatement(sql);
		ps.setInt(1,uid);
		rs=ps.executeQuery();
		while(rs.next())
		{
			postDisplayImage=rs.getBlob("picture");
			InputStream inputStream=postDisplayImage.getBinaryStream();
			System.out.println("Getting info");
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			byte buffer[]=new byte[4096];
			int byteRead=-1;
			 while ((byteRead = inputStream.read(buffer)) != -1) {
                 outputStream.write(buffer, 0, byteRead);                  
             }
			 byte imageBytes[]=outputStream.toByteArray();
			 String imageString=Base64.getEncoder().encodeToString(imageBytes);
			post tempPost=new post(rs.getInt("postid"),rs.getString("title"),rs.getString("content"),imageString,rs.getInt("grpid"));
			temp.add(tempPost);
		}
	}
	finally
	{
		
	}
	return temp;

	
	
	}
public void removeMeeting(int meid) throws SQLException {
	Connection con=null;
	PreparedStatement ps=null;
	try
	{
		con=datasource.getConnection();
		String sql="delete from meetings where meid=?";
		ps=con.prepareStatement(sql);
		ps.setInt(1, meid);
		ps.execute();
		
	}
	finally
	{
		close(con, ps, null);
	}
	}

}
