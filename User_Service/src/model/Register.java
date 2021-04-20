package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Register {
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
		
		public String RegisterUser(String userCode,String name,String NIC, String email, String phone,String userType, String username, String password) {
		   	
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
}
