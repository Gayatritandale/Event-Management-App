package javacode;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public EventManager() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		Connection con=connect.connectData();
		
		String CName,email,address;
		long phoneno;
		
		
		CName=request.getParameter("CName");
		phoneno=Long.parseLong(request.getParameter("phoneno"));
		email=request.getParameter("email");
		address=request.getParameter("address");
		
		PreparedStatement pstmt;
			
			try {
				pstmt = con.prepareStatement("insert into eventmanagers values(?,?,?,?)");
				pstmt.setString(1, CName);
				pstmt.setLong(2, phoneno);
				pstmt.setString(3, email);
				pstmt.setString(4,address);
				
				
				int i= pstmt.executeUpdate();
				if(i>0){
					response.sendRedirect("index.html");
				}
				else{
					response.sendRedirect("register.html");
				}
				
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
		
	}

