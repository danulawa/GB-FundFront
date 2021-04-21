package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FundModel {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			//Database Details
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbdatabase", "root", "Dan@617");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertFunds(String researchID, String description, double amount, String funder) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into funds(`researchID`,`description`,`amount`,`funder`)"+ "values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, researchID);
			preparedStmt.setString(2, description);
			preparedStmt.setDouble(3, amount);
			preparedStmt.setString(4, funder);	
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted Successfully!!";
		} catch (Exception e) {
			output = "Error while inserting the fund!!";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String readFunds() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Fund ID</th><th>Research ID</th>" + "<th>Description</th>"
					+ "<th>Amount</th>" +"<th>Funder</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from fund";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String fundID = Integer.toString(rs.getInt("fundID"));
				String researchID = rs.getString("researchID");
				String description = rs.getString("description");
				String amount = Double.toString(rs.getDouble("amount"));
				String funder = rs.getString("funder");
				
				

				// Add into the html table
				output += "<tr><td>" + fundID + "</td>";
				output += "<td>" + researchID + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + funder + "</td>";


				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='orderID' type='hidden' value='" + fundID + "'>" + "</form></td></tr>";
			}
			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the funds.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readFundsFunder(String ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Fund ID</th><th>Research ID</th>" + "<th>Description</th>"
					+ "<th>Amount</th>" +"<th>Funder</th>" + "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from fund where funderName =?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, ID);
			ResultSet rs = preparedStmt.executeQuery();

			// iterate through the rows in the result set
			while (rs.next()) {
				String fundID = Integer.toString(rs.getInt("fundID"));
				String researchID = rs.getString("researchID");
				String description = rs.getString("description");
				String amount = Double.toString(rs.getDouble("amount"));
				String funder = rs.getString("funderName");
				

				// Add into the html table
				output += "<tr><td>" + fundID + "</td>";
				output += "<td>" + researchID + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + funder + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='orderID' type='hidden' value='" + fundID + "'>" + "</form></td></tr>";
			}
			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the funds.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateFundAmount(String fundID, double amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE fund SET amount=? WHERE fundID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//Binding Values
			preparedStmt.setDouble(1, amount);
			preparedStmt.setString(2, fundID);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the fund.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String deleteFund(String ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from fund where fundID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the fund.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
