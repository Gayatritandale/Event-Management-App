package javacode;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class log extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public log() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
		String enteredemail=request.getParameter("email");
		String enteredpassword=request.getParameter("Password");
		
		Connection con=connect.connectData();
		
		try {
			PreparedStatement pstmt;
			pstmt = con.prepareStatement("select email,Password from accdetails where email=?");
			pstmt.setString(1,enteredemail);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				String storedusername = rs.getString("email");
				String storedPassword = rs.getString("Password");
				if(enteredemail.equals(storedusername))
				{
					if (enteredpassword.equals(storedPassword))
					{
						response.sendRedirect("profile.html");
					}
					else
					{
						response.sendRedirect("login.html");
					}
				}
				else
				{
					System.out.println("outer else");
					response.sendRedirect("incorrect.html");
				}

			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	}


