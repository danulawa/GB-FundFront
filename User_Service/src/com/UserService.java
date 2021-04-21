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
		
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	//register user
	public String RegisterUser(@FormParam("userCode") String userCode,
	 @FormParam("name") String name,
	 @FormParam("NIC") String NIC,
	 @FormParam("userEmail") String userEmail,
	 @FormParam("userPhone") String userPhone,
	 @FormParam("userType") String userType,
	 @FormParam("username") String username,
	 @FormParam("password") String password
	 )
	{
	 if(userCode.isEmpty()||name.isEmpty()||userEmail.isEmpty()||userPhone.isEmpty()||userType.isEmpty()||username.isEmpty()||username.isEmpty()||password.isEmpty()) 
	 {
		 return "input fields cannot be empty";
	 }
	 else if(NIC.length()!=10) {
		 return "NIC length must be 10 characters long";
	 }
	 else if(password.length()<5||password.length()>20) {
		 return "password should be less than 20 and more than 5 in length";
	 }
     else if(!password.matches("(.*[@,#,$,%].*$)")) {
    	 return "password must contain at least one special character";
     }
	 String output = userObj.RegisterUser(userCode, name, NIC, userEmail,userPhone,userType,username,password);
	 return output;
    }

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUserDetails()//view all users
	{
		return userObj.readUserDetails();
	}
		
	@GET
	@Path("/getUserbyID/{userId}")//view a specific user
	@Produces(MediaType.TEXT_HTML)
	public String UserProfileDetails(@PathParam("userId") String userId) {

		return userObj.fetchUser(userId);
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)//update user
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
	public String deleteUser(String userData)//delete users
	{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
	
		//Read the value from the element <userId>
		 String userID = doc.select("userId").text();
		 String output = userObj.deleteUser(userID);
		return output;
	}

	
}