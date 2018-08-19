package com.java.social;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

/**
 * Servlet implementation class socialController
 */
@WebServlet("/socialController")
@MultipartConfig(maxFileSize=16177216)
public class socialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userDbUtil userDbUtil;
	@Resource(name="jdbc/social_site")
	private DataSource dataSource;
	HttpSession session;
	social userDetails=null;  
    /**
     * @see HttpServlet#HttpServlet()
     */
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
		System.out.println("Inside post");
		String command=request.getParameter("command");
		System.out.println("command="+command);
		switch(command)
		{
		case "validate":
			validateUser(request,response);
			break;
		case "addUser":
			addUser(request,response);
			break;
		case "addPost":
			addPost(request,response);
			break;
		case "newGroup":
			newGroup(request,response);
			break;
		case "newPost":
			newPost(request,response);
			break;
		case "myPost":
			myOwnPost(request,response);
			break;
		case "newMeeting":
			newMeeting(request,response);
			break;
		case "saveMeeting":
			saveMeeting(request,response);
			break;
		case "notification":
			notification(request,response);
			break;
		case "direction":
			direction(request,response);
			break;
		case "searchGroup":
			findGroup(request,response);
			break;
		case "addGroup":
			addGroup(request,response);
			break;
		case "signOut":
			userSignOut(request,response);
			break;
		case "remove":
			removeMeeting(request,response);
			break;
		default:
			System.out.println("Default case");
			break;
				
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}







	private void removeMeeting(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		int meid=Integer.parseInt(request.getParameter("meetingId"));
		userDbUtil.removeMeeting(meid);
		response.sendRedirect("notification.jsp");
		
		
		
	}







	private void userSignOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		session.setAttribute("SESSION_STATUS",0);
		response.sendRedirect("index.jsp");
	}







	private void addGroup(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int stdid=Integer.parseInt(request.getParameter("stuId"));
		int grpid=Integer.parseInt(request.getParameter("grpId"));
		int flag=userDbUtil.addUserGroup(stdid,grpid);
		request.setAttribute("ERROR", flag);
		int adminId=userDetails.getUid();
		System.out.println(adminId);
		List<group> groupList=userDbUtil.groupList(adminId);
		request.setAttribute("GROUP_LIST",groupList);
		RequestDispatcher dispatcher=request.getRequestDispatcher("addGroup.jsp");
		dispatcher.forward(request, response);
			
	
	}







	private void findGroup(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		int adminId=userDetails.getUid();
		System.out.println(adminId);
		List<group> groupList=userDbUtil.groupList(adminId);
		request.setAttribute("GROUP_LIST",groupList);
		request.setAttribute("ERROR", 0);
		RequestDispatcher dispatcher=request.getRequestDispatcher("addGroup.jsp");
		dispatcher.forward(request, response);
		
		
		
	}







	private void direction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String meid=request.getParameter("meetingId");
		String destination=request.getParameter("address");
		request.setAttribute("DESTDETAILS",destination);
		RequestDispatcher dispatcher=request.getRequestDispatcher("direction.jsp");
		dispatcher.forward(request, response);
		
	}







	private void notification(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List<meeting> myMeeting=userDbUtil.myMeetings(userDetails.getUid());
		if(myMeeting.isEmpty())
		{
			request.setAttribute("NO_MEETING_ERROR",1);
			RequestDispatcher dispatcher=request.getRequestDispatcher("notification.jsp");
			dispatcher.forward(request, response);
			
		}
		else
		{
		request.setAttribute("NO_MEETING_ERROR",0);
		request.setAttribute("MEETINGDETAIL", myMeeting);
		RequestDispatcher dispatcher=request.getRequestDispatcher("notification.jsp");
		dispatcher.forward(request, response);
		}
		
	}







	private void saveMeeting(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		String purpose=request.getParameter("purpose");
		String group=request.getParameter("group");
		String time=request.getParameter("time");
		String place=request.getParameter("place");
		String date=request.getParameter("date");
		System.out.println(date);
		userDbUtil.saveMeeting(purpose,group,time,place,date);
	}







	private void newMeeting(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<String> userGroups=userDbUtil.getUserGroups(userDetails.getUid());
		request.setAttribute("USER_GROUPS",userGroups);
		RequestDispatcher dispatcher=request.getRequestDispatcher("setMeeting.jsp");
		dispatcher.forward(request, response);
		
	}







	private void myOwnPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
			List<post> userPosts=userDbUtil.myOwnPost(userDetails.getUid());
			if(userPosts.isEmpty())
			{
				System.out.println("No posts");
				request.setAttribute("NO_POST_ERROR",1);
				RequestDispatcher dispatcher=request.getRequestDispatcher("myPost.jsp");
				dispatcher.forward(request, response);
			
			}
			else
			{
				request.setAttribute("USER_POSTS",userPosts);
				request.setAttribute("NO_POST_ERROR",0);
				RequestDispatcher dispatcher=request.getRequestDispatcher("myPost.jsp");
				dispatcher.forward(request, response);
			
			}
						
		
	}







	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doPost(req, resp);
		
	}







	private void newPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<String> userGroups=userDbUtil.getUserGroups(userDetails.getUid());
		request.setAttribute("USER_GROUPS",userGroups);
		RequestDispatcher dispatcher=request.getRequestDispatcher("addPost.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

	private void newGroup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		System.out.println("Inside new group");
		String groupName=request.getParameter("grp-name");
		Part part=request.getPart("grp-image");
		String groupInfo=request.getParameter("grp-description");
		social temp=(social) session.getAttribute("USER_DETAILS");
		int adminId=temp.getUid();
		String adminName=temp.getFirst_name()+" "+temp.getLast_name();
		int follower=0;
		group newGroup=new group(groupName, adminName, adminId, groupInfo, part, follower);
		userDbUtil.addGroup(newGroup);
		response.sendRedirect("createGroup.jsp");
}

	private void addPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		String title=request.getParameter("post-title");
		Part part=request.getPart("image");
		String content=request.getParameter("post-content");
		String groupName=request.getParameter("grpName");
		int grpid=userDbUtil.getGroupId(groupName);
		post p=new post(title, content, part,grpid);
		userDbUtil.addPost(p,userDetails.getUid());
		response.sendRedirect("addPost.jsp");
		
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String fName=request.getParameter("first-name");
		String lName=request.getParameter("last-name");
		String email=request.getParameter("email");
		String password=request.getParameter("setPassword");
		String confirmPassword=request.getParameter("confirmPassword");
		if(!password.equals(confirmPassword))
		{
			request.setAttribute("ERROR_STATUS",1);
			request.setAttribute("ACC_STATUS",0);
			System.out.println("Password mismatch");
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			
		}
		else if(!userDbUtil.checkDuplicatemail(email))
		{
			request.setAttribute("ERROR_STATUS",1);
			request.setAttribute("ACC_STATUS",1);
			System.out.println("duplicate email");
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			
			
		}
		else
		{
			
			social s=new social(fName, lName, email, password);
			userDbUtil.addUser(s);
			session=request.getSession();
			session.setAttribute("ACC_STATUS",2);
			session.setAttribute("ERROR_STATUS",0);
			response.sendRedirect("index.jsp");
		}
	}

	private void validateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			String emailAddress=request.getParameter("email");
			String password=request.getParameter("password");
			System.out.println("validating");
			boolean status=userDbUtil.validateUser(emailAddress,password);
			System.out.println("Status="+status);
			if(status)
			{
				userDetails=userDbUtil.getInfo(emailAddress);
				RequestDispatcher dispatcher;
				request.setAttribute("STATUS",status);
				session=request.getSession();
				session.setAttribute("SESSION_STATUS",1);
				session.setAttribute("USER_DETAILS",userDetails);
				List<post> userPosts=userDbUtil.myGroupPost(userDetails.getUid());
				if(userPosts.isEmpty())
				{
					findGroup(request, response);
				}
				else
				{
					session.setAttribute("USER_POST", userPosts);
					dispatcher=request.getRequestDispatcher("welcome.jsp");
					dispatcher.forward(request, response);	
				}
				
			}
			else
			{
				request.setAttribute("STATUS",status);
				request.setAttribute("ERROR_STATUS", 1);
				RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
				
				dispatcher.forward(request, response);
			}
			
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try
		{
			userDbUtil=new userDbUtil(dataSource);
		}
		catch(Exception e)
		{
			
		}
	}

}
