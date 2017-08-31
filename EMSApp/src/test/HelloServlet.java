package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    PrintWriter out=response.getWriter();
	String u=request.getParameter("uid");
	String p=request.getParameter("pwd");
try {	
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","");
PreparedStatement ps=con.prepareStatement("select * from login where userid=? and password=?");
ps.setString(1,u);
ps.setString(2,p);
    ResultSet rs= ps.executeQuery();
     int x=0;
    if(rs.next())
     {
    	  x=1;
     }
	if(x==1)
    out.println("<h1>Login Success</h1>");
	else
		out.println("<h1>Login Fail, try again</h1>");
}catch(SQLException | ClassNotFoundException e)
{
	out.println(e);
}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
