package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import config.DBConnector;

public class Order {

	public String addOrder(int cid, int pid, Date day) {
		try (Connection con = DBConnector.getConnection()) {

			// Query for count AppId for duplicate date & time for same doctor and hospital
			String checkQuery = "select count(order_id)  from orders where project_project_id = ? and date = ?";
			PreparedStatement preparedstatement = con.prepareStatement(checkQuery);

			preparedstatement.setInt(1, pid);
			preparedstatement.setDate(2, day);
			ResultSet newresultset = preparedstatement.executeQuery();
			newresultset.next();

			// convert count Appointment ids to integer
			int value = Integer.parseInt(newresultset.getObject(1).toString());

			if (value != 0) {
				return "The particular project  has been ordered please choose another project.";

			} else {
				// check date is before current date
				SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date(System.currentTimeMillis());

				if (day.compareTo(date) < 0) {

					return "You cannot request past dates as order dates please select a future date";
				}

				else {
					String insertOrdQuery = " insert into orders values (NULL,?, ?, ?)";
					PreparedStatement pstmnt = con.prepareStatement(insertOrdQuery);

					pstmnt.setInt(1, cid);
					pstmnt.setInt(2, pid);
					pstmnt.setDate(3, day);
					pstmnt.execute();

					return "Order added successfully...";
				}
			}
		} catch (SQLException e) {

			return "Error occured during adding an Order\n" + e.getMessage();
		}

	}

	public String getOrderByCustomer(int id) {

		try (Connection con = DBConnector.getConnection()) {

			String getOrdeQuery = "select o.order_id, c.custName," + " o.date, p.projectName  " + "from orders o "
					+ "join customer c on o.customer_customer_id = c.custId "
					+ "join project p on o.project_project_id = p.projectID where custID = ?";

			PreparedStatement pstmnt = con.prepareStatement(getOrdeQuery);

			pstmnt.setInt(1, id);

			String output = "<table border=\"1\"><tr><th>Order ID</th>" + "<th>Order Date</th> "
					+ "<th>Customer Name </th>" + "<th>Project Name</th>";

			ResultSet rs = pstmnt.executeQuery();
			while (rs.next()) {

				int OrdID = rs.getInt("order_id");
				Date day = rs.getDate("date");
				String customerName = rs.getString("custName");
				String projectName = rs.getString("projectName");

				output += "<tr><td>" + OrdID + "</td>";
				output += "<td>" + day + "</td>";
				output += "<td>" + customerName + "</td>";
				output += "<td>" + projectName + "</td>";

			}
			output += "</table>";

			return output;

		} catch (SQLException e) {

			return "Error occur during retrieving \n" +

					e.getMessage();
		}

	}

	public String ReadOrders() {

		try (Connection con = DBConnector.getConnection()) {

			LocalDate prvPaymentDate = null;
			String readQuery = "select * from orders";

			PreparedStatement pstmt = con.prepareStatement(readQuery);

			String output = "<table border=\"1\"><tr><th>Order ID</th>" + "<th>Customer ID</th> "
					+ "<th>Project ID</th>" + "<th>Date</th></tr>";

			ResultSet rs = pstmt.executeQuery(readQuery);

			while (rs.next()) {
				int OrdID = rs.getInt("order_id");
				int cid = rs.getInt("customer_customer_id");
				int pid = rs.getInt("project_project_id");
				Date day = rs.getDate("date");

				output += "<tr><td>" + OrdID + "</td>";
				output += "<td>" + cid + "</td>";
				output += "<td>" + pid + "</td>";
				output += "<td>" + day + "</td>";

			}

			output += "</table>";
			return output;
		} catch (SQLException e) {

			e.printStackTrace();
			return "Error occured during retrieving data";
		}

	}

	public String UpdateOrder(Date day, int OrdID) {

		try (Connection con = DBConnector.getConnection()) {

			// get doctor id and hospital id for given appointment id
			String getproIDQuery = "SELECT project_project_id  FROM orders WHERE order_id = ?";

			PreparedStatement preparedstatement = con.prepareStatement(getproIDQuery);

			preparedstatement.setInt(1, OrdID);

			ResultSet newresultset = preparedstatement.executeQuery();

			newresultset.next();

			// Assign into variable

			int pid = newresultset.getInt("project_project_id");

			// get Count of given info
			String checkQuery = "select count(order_id)  from orders where date = ?   and project_project_id = ?";
			PreparedStatement prstmnt = con.prepareStatement(checkQuery);

			prstmnt.setDate(1, day);
			prstmnt.setInt(2, pid);

			ResultSet newresultset2 = prstmnt.executeQuery();

			newresultset2.next();

			// convert count into integer
			int value = Integer.parseInt(newresultset2.getObject(1).toString());

			if (value != 0) {
				return "The particular project has been reserved please choose another project.";

			}

			else {

				String updateAppQuery = "UPDATE orders SET date=? WHERE order_id=?";

				PreparedStatement pstmnt = con.prepareStatement(updateAppQuery);
				pstmnt.setDate(1, day);
				pstmnt.setInt(2, OrdID);

				pstmnt.execute();
				return "Order Updated successfully...";
			}
		} catch (SQLException e) {
			return "Error occured during Updating an Order\n" + e.getMessage();
		}
	}
	public String DeleteOrder(int OrdID) 
	 { 
	 String output = ""; 
	 try
	 {  
		 Connection con  = DBConnector.getConnection(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from orders where order_id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1,OrdID); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = " Order Deleted successfully."; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error occur during deleting the order."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
}
		