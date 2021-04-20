package com;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Product {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/researcher", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

//Insert Function 
	public String insertProject(String pTitle, String pDesc, String resName, String date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into res(`pId`,`pTitle`,`pDesc`,`resName`,`date`) values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pTitle);
			preparedStmt.setString(3, pDesc);
			preparedStmt.setString(4,resName);
			preparedStmt.setString(5, date);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

//Read all Items function
	public String readProject() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Project Title</th>" + "<th>Project Description</th><th>Researcher Name</th>"
					+ "<th>Date</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from res";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String pId = Integer.toString(rs.getInt("pId"));
				String pTitle = rs.getString("pTitle");
				String pDesc = rs.getString("pDesc");
				String resName = rs.getString("resName");
				String date = rs.getString("date");
				// Add a row into the html table
				output += "<tr><td>" + pTitle + "</td>";
				output += "<td>" + pDesc + "</td>";
				output += "<td>" + resName + "</td>";
				output += "<td>" + date + "</td>";
				// buttons
				output += "<td><form method='post' action='Researcher.jsp'>" + "<input name='pId' type='hidden' value='"
						+ pId + "'>" + "<input name='action' value='select' type='hidden'>"
						+ "<input name='btnUpdate' type='submit' value='Update' class='btn btn-danger'>"
						+ "</form></td>" + "<td><form method='post' action='Researcher.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='action' value='delete' type='hidden'>"
						+ "<input name='pId' type='hidden' value='" + pId + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//Update function
		public String updateProject(int ID,String pTitle, String pDesc, String resName, String date)
		{
			System.out.println(ID);
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database";
				}
				// create a prepared statement      
				String query = "update res set pTitle=?,pDesc =?,resName=?,date=? where pId = ?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, pTitle);
				preparedStmt.setString(2, pDesc);
				preparedStmt.setString(2, resName);
				preparedStmt.setString(4, date); 
				preparedStmt.setInt(5, ID);
				//execute the statement
				preparedStmt.executeUpdate();
				con.close();
				output = "Updated successfully";
			}
			catch (Exception e)
			{
				output = "Error while updating a one item";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//Read one item function
		public String readOneProject(int id)
		{
			//test the id
			//System.out.print(id);
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database.";
				}
		 
				String query = "select * from res where pId = ? ";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, id);
				ResultSet rs = preparedStmt.executeQuery();
				// iterate through the rows in the result set
				while (rs.next())
				{
					String pId = Integer.toString(rs.getInt("pId"));
					String pTitle = rs.getString("pTitle");
					String pDesc = rs.getString("pDesc");
					String resName =rs.getString("resName");
					String date = rs.getString("date");
					output += "<form method=post action=Researcher.jsp>"
							+ "		<input name='action' value='update' type='hidden'>"
							+ " 	Item ID: '"+pId+"' <br>"
							+ " 	Item code: <input name=itemCode type=text value='"+pTitle+ "'><br>"
							+ " 	Item name: <input name=itemName type=text value='"+pDesc+ "'><br>"
							+ " 	Item price: <input name=itemPrice type=text value='"+resName+ "'><br>"
							+ " 	Item description: <input name=itemDesc type=text value='"+date+ "'><br>"
							+ "     <input name='itemID' type='hidden' value='" + pId + "'>"
							+ " 	<input name=btnSubmit type=submit value=Update >"
							+ "	</form>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
		 
		}
		catch (Exception e)
		{
			output = "Error while reading.";
			System.err.println(e.getMessage());
		}
		return output;
		}
		
		
		
		//delete item function
		public String deleteProject(int ID) {
			String output = "";
			//test the id
			//System.out.print(ID);
			
			try 
			{
				Connection con = this.connect();
				
				if (con == null) 
				{
					return "Error connecting to database";
				}
				
				//creating prepared statement
				String query = "delete from res where pId = ?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				//binding values to prepared statement			
				preparedStmt.setInt(1,ID);
				
				preparedStmt.execute();
				con.close();
				
				output = "deleted successfully";
			}
			catch (Exception e) 
			{
				output = "Error while deleting ";
				System.err.println(e.getMessage());
			}
			return output;		
		}
		
		
		
	
}