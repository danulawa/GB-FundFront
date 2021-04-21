package model;

import java.sql.Connection;
import javafx.util.Pair; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import config.DBConnector;

public class Payment {

	public String addPayment(String cardType, 
							 int cardNumber, 
							 String nameOnCard, 
							 int cvc, 
							 Date expireDate,
							 String status, 
							 Date paymentDate,  
							 int orderId) {

		try (Connection con = DBConnector.getConnection()) {
			String insertQuery = " insert into payment values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?)";
			PreparedStatement pstmnt = con.prepareStatement(insertQuery);

			double subAmount = this.calculateSubAmount(orderId, paymentDate);
			int taxId = this.getValidTax(paymentDate).getKey();
			pstmnt.setString(1, cardType);
			pstmnt.setInt(2, cardNumber);
			pstmnt.setString(3, nameOnCard);
			pstmnt.setInt(4, cvc);
			pstmnt.setDate(5, expireDate);
			pstmnt.setString(6, status);
			pstmnt.setDouble(7, subAmount);
			pstmnt.setDate(8, paymentDate);
			pstmnt.setInt(9, taxId);
			pstmnt.setInt(10, orderId);

			pstmnt.execute();
			return "Payment added successfully";
		} catch (SQLException e) {
			return "Error occur during adding\n" + e.getMessage();
		}
	}

	public String getPaymentByCustomer(int id) {
		try (Connection con = DBConnector.getConnection()) {
			String getQuery = "select py.payment_id, c.custName, py.date, py.sub_amount, p.projectName from orders o \n"
					+ "join customer c on o.customer_customer_id = c.custId \n"
					+ "join payment py on o.order_id = py.order_order_id \n"
					+ "join project p on o.project_project_id = p.projectID \n" 
					+ "where custId = ?;";
			PreparedStatement pstmnt = con.prepareStatement(getQuery);
			pstmnt.setInt(1, id);
			String output = "<table>" + 
							"<tr>" 
							+ "<th>Payment ID</th>" 
							+ "<th>Customer Name</th>"
							+ "<th>Payment Date</th>" 
							+ "<th>Amount</th>"  
							+ "<th>Project</th>";
			ResultSet rs = pstmnt.executeQuery();

			while (rs.next()) {
				int payId = rs.getInt("payment_id");
				String customerName = rs.getString("custName");
				Date paymentDate = rs.getDate("date");
				double subAmount = rs.getDouble("sub_amount");
				String projectName = rs.getString("projectName");

				output += "<tr><td>" + payId + "</td>";
				output += "<td>" + customerName + "</td>";
				output += "<td>" + paymentDate + "</td>";
				output += "<td>" + subAmount + "</td>";
				output += "<td>" + projectName + "</td>";

			}
			output += "</table>";
			return output;
		} catch (SQLException e) {
			return "Error occur during retrieving \n" + e.getMessage();
		}
	}
	
	public String getAllPayment(){
        try(Connection con  = DBConnector.getConnection()) {
            String getQuery = "select * from payment";
            PreparedStatement pstmt = con.prepareStatement(getQuery);

            String output = "<table border=\"1\">" +
                    "<tr>" +
					"<th>Payment Id</th>" +
                    "<th>Card Type</th>" +
					"<th>Card Number</th>" +
					"<th>Name On Card</th>" +
					"<th>cvc</th>" +
					"<th>Expire Date</th>" +
					"<th>Status</th>" +
					"<th>Sub Amount</th>" +
                    "<th>Payment Date</th>" +
					"<th>Tax Id</th>" +
					"<th>Order Id</th>" ;
					
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
				int paymentId = rs.getInt("payment_id");
				String cardType = rs.getString("card_type");
                int cardNumber = rs.getInt("card_number");
				String nameOnCard = rs.getString("name_on_card");
				int cvc = rs.getInt("cvc");
				Date expireDate = rs.getDate("expire_date");
				String status = rs.getString("status");
				float subAmount = rs.getFloat("sub_amount");
				Date paymentDate = rs.getDate("date");
				int taxId = rs.getInt("tax_tax_id");
                int orderId = rs.getInt("order_order_id");

                output += "<tr><td>" + paymentId + "</td>";
                output += "<td>" + cardType + "</td>";
                output += "<td>" + cardNumber + "</td>";
				output += "<td>" + nameOnCard + "</td>";
				output += "<td>" + cvc + "</td>";
				output += "<td>" + expireDate + "</td>";
				output += "<td>" + status + "</td>";
				output += "<td>" + subAmount + "</td>";
				output += "<td>" + paymentDate + "</td>";
				output += "<td>" + taxId + "</td>";
				output += "<td>" + orderId + "</td>";

            }
            output += "</table>";
            con.close();
            return output;
        }
        catch (SQLException e){
            return "Error occur during retrieving \n" +
                    e.getMessage();
        }
    }

	public double calculateSubAmount(int orderId, Date paymentDate) {
		double subAmount = 0;
		try (Connection con = DBConnector.getConnection()) {
			String getQuery = "select p.amount\n" 
					+ "from orders o\n"
					+ "join project p on p.projectID = o.project_project_id \n" 
					+ "where o.order_id = ?;";
			
			PreparedStatement pstmt = con.prepareStatement(getQuery);
			pstmt.setInt(1, orderId);
			ResultSet rs = pstmt.executeQuery();

			float amount = 0;
			float taxAmount = getValidTax(paymentDate).getValue();
			while (rs.next()) {
				
				amount = rs.getFloat("amount");
			}
			con.close();
			subAmount = amount + taxAmount;
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return subAmount;

	}
	
	@SuppressWarnings("restriction")
	public Pair<Integer, Float> getValidTax(Date today) {
		float taxAmount = 0;
		int taxId = 0;
		Pair<Integer, Float> pair = new Pair(taxId, taxAmount);
		try(Connection con = DBConnector.getConnection()) {
			String searchQuey = "select tax_id, tax_amount from tax "
					+ "where valid_from < ?"
					+ "and valid_to > ?";
			PreparedStatement pstmt = con.prepareStatement(searchQuey);
			pstmt.setDate(1, today);
			pstmt.setDate(2, today);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				taxId = rs.getInt("tax_id");
				taxAmount = rs.getFloat("tax_amount");
				pair = new Pair(taxId, taxAmount);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pair;
		
	}

	public String updatePayment(int paymentId,
								String cardType,
								int cardNumber,
								String nameOnCard,
								int cvc,
								Date expireDate,
								String status,
								Date paymentDate,
								int orderId){
			
			try(Connection con = DBConnector.getConnection()) {
				
				
			String updateQuery = "update payment set card_type=?,card_number=?,name_on_card=?,cvc=?,expire_date=?,status=? ,sub_amount =? ,date=?,tax_tax_id=?,order_order_id=? where payment_id =?" ;
			
			
						
			PreparedStatement pstmt = con.prepareStatement(updateQuery);
			
			
			double subAmount = this.calculateSubAmount(orderId, paymentDate);
			@SuppressWarnings("restriction")
			int taxId = this.getValidTax(paymentDate).getKey();
			pstmt.setString(1,cardType);
			pstmt.setInt(2,cardNumber);
			pstmt.setString(3,nameOnCard);
			pstmt.setInt(4,cvc);
			pstmt.setDate(5,expireDate);
			pstmt.setString(6,status);
			pstmt.setDouble(7,subAmount);
			pstmt.setDate(8,paymentDate);
			pstmt.setInt(9,taxId);
			pstmt.setInt(10,orderId);
			pstmt.setInt(11,paymentId);
			pstmt.execute();
			con.close();
			System.out.println(paymentId);
	
			return "Payment updated successfully 123";
		
			}
			catch(Exception e){
				return "Error occur during updating \n" +
						e.getMessage();
			}

	}
	
	public String DeletePayment(int paymentId) 
	 { 
	 String output = ""; 
	 try
	 {  
		 Connection con  = DBConnector.getConnection(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from payment where payment_id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1,paymentId); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the payment."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

}
