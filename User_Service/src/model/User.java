package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	//creating connection
	public Connection connect()
	{
		Connection con = null;

		try
		{
		 Class.forName("com.mysql.jdbc.Driver");
		 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/admindb","root", "");
		 //For testing
		 System.out.print("Successfully connected");
	 	}
		catch(Exception e)
	 	{
	 		e.printStackTrace();
		 }

	return con;
	}

   public String fetchUser(String userID)
	
	{
		 String output = "";
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading";
		 }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>User Code</th>"
		 +"<th>Name</th><th>NIC</th>"
		 + "<th>Email</th>"
		 + "<th>Phone</th>"
		 + "<th>Type</th>"
		 + "<th>UserName</th>"
		 + "<th>Password</th>"
		 + "<th>Update</th><th>Remove</th></tr>";
		 String query = "select * from users where userId=userID";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 { 
			 String userId = Integer.toString(rs.getInt("userId"));
			 if(userID.equals(userId))
				{	
			 
			 
			 String userCode = rs.getString("userCode");
			 String name = rs.getString("name");
			 String NIC = rs.getString("NIC");
			 String userEmail = rs.getString("userEmail");
			 String userPhone = rs.getString("userPhone");
			 String userType = rs.getString("userType");
			 String username = rs.getString("username");
			 String password = rs.getString("password");
			 // Add a row into the html table
			 output += "<tr><td>" + userCode + "</td>";
			 output += "<td>" + name + "</td>";
			 output += "<td>" + NIC + "</td>";
			 output += "<td>" + userEmail + "</td>"; 
			 output += "<td>" + userPhone + "</td>";
			 output += "<td>" + userType + "</td>";
			 output += "<td>" + username + "</td>";
			 output += "<td>" + password + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' "
			 + " type='button' value='Update' class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='Register.jsp'>"
			 + "<input name='btnRemove' "
			 + " type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='itemID' type='hidden' "
			 + " value='" + userID + "'>"
			 + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 }
		catch (Exception e)
		 {
			 output = "Error while reading the user details.";
			 System.err.println(e.getMessage());
		 }
		return output;
	}
   
	

   public String EditUserDetails(String userId,String userCode,String name,String NIC, String email, String phone,String userType, String username, String password)
   {
	   String output = "";
	   try
		   {
		   Connection con = connect();
		   if (con == null)
		   {
			   return "Error while connecting to the database for updating"; 
		   }
		   // create a prepared statement
		   String query = "UPDATE users SET userCode=?,name=?,NIC=?,userEmail=?,userPhone=?,userType=?,username=?,password=?WHERE userId=?";
		   PreparedStatement preparedStmt = con.prepareStatement(query);
		   // binding values
		   preparedStmt.setString(1, userCode);
		   preparedStmt.setString(2, name);
		   preparedStmt.setString(3, NIC);
		   preparedStmt.setString(4, email);
		   preparedStmt.setString(5, phone);
		   preparedStmt.setString(6, userType);
		   preparedStmt.setString(7, username);
		   preparedStmt.setString(8, password);
		   preparedStmt.setInt(9, Integer.parseInt(userId));
		   // execute the statement
		   preparedStmt.execute();
		   con.close();
		   output = "Updated successfully";
		   }
	    catch (Exception e)
		{
		   output = "Error while updating the user";
		   System.err.println(e.getMessage());
		}
	    return output;
	    }
	  
   public String deleteUser(String userId)
   {
	   String output = "";
	   try
	   {
	   Connection con = connect();
	   if (con == null)
	   {return "Error while connecting to the database for deleting."; }
	   // create a prepared statement
	   String query = "delete from users where userId=?";
	   PreparedStatement preparedStmt = con.prepareStatement(query);
	   // binding values
	   preparedStmt.setInt(1, Integer.parseInt(userId));
	   // execute the statement
	   preparedStmt.execute();
	   con.close();
	   output = "Deleted successfully";
	   }
	   catch (Exception e)
	   {
	   output = "Error while deleting the user";
	   System.err.println(e.getMessage());
	   }
	   return output;
	}
   
   
   

}
