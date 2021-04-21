package model;
import java.sql.*;

import com.google.gson.JsonElement; 
public class Product 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/productservice", "root", ""); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 
public String insertProduct(String title, String desc, String price, String name,  String date) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into product(`pId`,`pTitle`,`pDesc`,`pPrice`,`resName`, `date` )" + " values (?, ?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, title); 
 preparedStmt.setString(3, desc); 
 preparedStmt.setDouble(4, Double.parseDouble(price)); 
 preparedStmt.setString(5, name); 
 preparedStmt.setString(6, date);
// execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String readProducts() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Project ID</th><th>Project Title</th><th>Project Description</th>" +
 "<th>Project Price</th>" + 
 "<th>Researcher Name</th>" +
 "<th>Date</th>";

 
 String query = "select * from product"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String pId = Integer.toString(rs.getInt("pId")); 
 String pTitle = rs.getString("pTitle"); 
 String pDesc = rs.getString("pDesc"); 
 String pPrice = Double.toString(rs.getDouble("pPrice")); 
 String resName = rs.getString("resName"); 
 String date = rs.getString("date"); 
 // Add into the html table
 
 output += "<tr><td>" + pId + "</td>";
 output += "<td>" + pTitle + "</td>"; 
 output += "<td>" + pDesc + "</td>"; 
 output += "<td>" + pPrice + "</td>"; 
 output += "<td>" + resName + "</td>";
 output += "<td>" + date + "</td>";
 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='Product.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='pId' type='hidden' value='" +pId 
 + "'>" + "</form></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the products."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String updateProduct(String pId, String pTitle, String pDesc, String pPrice, String resName, String date)
{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE product SET pTitle=?,pDesc=?,pPrice=?,resName=?, date=?  WHERE pId=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, pTitle); 
	 preparedStmt.setString(2, pDesc); 
	 preparedStmt.setDouble(3, Double.parseDouble(pPrice)); 
	 preparedStmt.setString(4, resName); 
	 preparedStmt.setString(5, date);
	 preparedStmt.setInt(6, Integer.parseInt(pId)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the product."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String deleteProduct(String pId) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from product where pId=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(pId)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the product."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
}