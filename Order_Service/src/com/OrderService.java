package com;
import java.sql.Date;

import java.sql.Date;
import java.text.SimpleDateFormat;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Order;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Order;


@Path("/Orders")
public class OrderService {
	
	Order Ord1 = new Order();
	
	//read orders service
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String ReadOrders() {
		
		return Ord1.ReadOrders();
	}
	
	
	//get order by ID service
	@GET
	@Path("/getOrderbyID/Ord1/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String getPayment(@PathParam("id") int id) {
		System.out.println(id);

		return this.Ord1.getOrderByCustomer(id);
	}	
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addAppointment(
			@FormParam("customer_customer_id") int cid, 
			@FormParam("project_project_id") int pid,
			@FormParam("date") Date day) 
	{
		String output = Ord1.addOrder(cid,pid,day);
		return output;
	}
	//update service
	@PUT
	@Path("/update/Ord1/{order_id}") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateOrder(
			
			@FormParam("date") Date day, 
			@PathParam("order_id") int OrdID
			) 
	{
		String output = Ord1.UpdateOrder(day,OrdID);
         
		return output;
	}
	
	@DELETE
	@Path("/delete/Ord1/{order_id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String DeleteOrder(
			@PathParam ("order_id") int OrdID )
	{
	
	//Read the value from the element <AppID>
		String output = Ord1.DeleteOrder(OrdID);
        return output;

	
	
	}
	
}