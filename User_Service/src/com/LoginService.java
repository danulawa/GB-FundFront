package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Login;

@Path("/Login")
public class LoginService {
	Login login = new Login();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String validateLogin(@FormParam("Username") String Username, 
							    @FormParam("Password") String Password) 
	{
		String output = login.validateLogin(Username, Password);
		return output;
	}
}
