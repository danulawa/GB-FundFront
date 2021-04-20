package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Register;

@Path("/Register")
public class RegisterService {
	
	Register reg = new Register();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
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
	 String output = reg.RegisterUser(userCode, name, NIC, userEmail,userPhone,userType,username,password);
	 return output;
    }
}
