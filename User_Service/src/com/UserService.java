package com;
import model.User;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Users")
public class UserService
{
	User userObj = new User();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUserDetails()
	{
		return userObj.readUserDetails();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("userCode") String userCode,
	 @FormParam("name") String name,
	 @FormParam("NIC") String NIC,
	 @FormParam("userEmail") String userEmail,
	 @FormParam("userPhone") String userPhone,
	 @FormParam("userType") String userType,
	 @FormParam("username") String username,
	 @FormParam("password") String password
	 )
	{
	 String output = userObj.insertDetails(userCode, name, NIC, userEmail,userPhone,userType,username,password);
	return output;
}

}