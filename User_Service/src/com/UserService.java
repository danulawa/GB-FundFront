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
	public String insertUser(@FormParam("userCode") String userCode,
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

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
	//Convert the input string to a JSON object
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String userId = userObject.get("userId").getAsString();
	 String userCode = userObject.get("userCode").getAsString();
	 String name = userObject.get("name").getAsString();
	 String NIC = userObject.get("NIC").getAsString();
	 String userEmail = userObject.get("userEmail").getAsString();
	 String userPhone = userObject.get("userPhone").getAsString();
	 String userType = userObject.get("userType").getAsString();
	 String username = userObject.get("username").getAsString();
	 String password = userObject.get("password").getAsString();
	 String output = userObj.EditUserDetails(userId,userCode, name, NIC, userEmail,userPhone,userType,username,password);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
	
		//Read the value from the element <userId>
		 String userID = doc.select("userId").text();
		 String output = userObj.deleteUser(userID);
		return output;
	}

}