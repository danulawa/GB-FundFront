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

	public String readUserDetails()
	
	{
		 String output = "";
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading.";
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
		 String query = "select * from users";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String userID = Integer.toString(rs.getInt("userID"));
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
		catch (Exception e)
		 {
			 output = "Error while reading the items.";
			 System.err.println(e.getMessage());
		 }
		return output;
	}
	public String insertDetails(String userCode,String name,String NIC, String email, String phone,String userType, String username, String password) {
    	
	  String output = "";
		try
		 { Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database";
		 }
    		
    	    String sql = "insert into users(`userId`,`userCode`,`name`,`NIC`,`userEmail`,`userPhone`,`userType`,`username`,`password`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	    PreparedStatement preparedStmt = con.prepareStatement(sql);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, userCode);
			 preparedStmt.setString(3, name);
			 preparedStmt.setString(4, NIC);
			 preparedStmt.setString(5, email); 
			 preparedStmt.setString(6, phone);
			 preparedStmt.setString(7, userType);
			 preparedStmt.setString(8, username); 
			 preparedStmt.setString(9, password); 
			//execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Inserted successfully";
    		
    	}
		catch (Exception e)
		 {
		 output = "Error while inserting";
		 System.err.println(e.getMessage());
		 }
		return output;
    	}
  
  
  	
   public String fetchUser(String userId)
	
	{
		 String output = "";
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading.";
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
		 String query = "select * from users where userID=userId";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String userID = Integer.toString(rs.getInt("userID"));
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
		catch (Exception e)
		 {
			 output = "Error while reading the items.";
			 System.err.println(e.getMessage());
		 }
		return output;
	}
}
