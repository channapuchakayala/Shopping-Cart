package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginController {


	public String login(HttpServletRequest request) {

		String user =(String)request.getParameter("user");
		String password =(String)request.getParameter("password");

		System.out.println(" User : "+user);
		System.out.println(" Password : "+password);

		if(user == null) {
			System.out.println("first time login");
			return "Login";
		}
		
		Connection conn= null;
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://10.131.81.99;user=sa;password=P@ssword01;database=shopping_cart");
			
			Statement sta = conn.createStatement();
			String Sql = "select * from login where username='"+user+"'";
	
			ResultSet rs = sta.executeQuery(Sql);
			while (rs.next()) {
				if(password.equals(rs.getString("password").trim())){
					HttpSession session = request.getSession();
					HashMap<Integer,Integer> cartMap = new HashMap<Integer,Integer>();
					session.setAttribute("cart", cartMap);
					return "browse";
				}
				
				System.out.println("Invalid Password : "+password);
				request.setAttribute("errorMessage", "Invalid Password");
				return "Login";
			}
			
			
			System.out.println(" Invalid User : "+user);
			request.setAttribute("errorMessage", "Invalid User");

			return "Login";
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("fetalMessage", e.getMessage());
			return "somethingWrong";
		}
	}

}
