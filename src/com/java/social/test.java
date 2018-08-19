package com.java.social;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")



public class test extends HttpServlet {
	@Resource(name="jdbc/social_site")
	private DataSource datasource;
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public test() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		PrintWriter out=response.getWriter();
		try
		{
			con=datasource.getConnection();
			System.out.println("test");
			String sql="select * from userlogin";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				String email=rs.getString(1);
				out.println(email);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
