package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.FundModel;

@Path("/Funding")
public class FundingService {
	
	FundModel Fund = new FundModel();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds()
	 {
		return Fund.readFunds(); 
	 } 
	
	
	@GET
	@Path("/{funder}")
	@Produces(MediaType.TEXT_PLAIN)
	public String readFundsFunder(@PathParam("funder") String funder)
	 {
		return Fund.readFundsFunder(funder); 
	 }
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFunds(@FormParam("researchID") String researchID,
	 @FormParam("description") String description,
	 @FormParam("amount") double amount,@FormParam("funder") String funder)
	{
	 String output = Fund.insertFunds(researchID, description, amount,funder);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFundAmount(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String fundID = itemObject.get("fundID").getAsString();
	 double amount = Double.parseDouble(itemObject.get("amount").getAsString());

	 String output = Fund.updateFundAmount(fundID, amount);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFunds(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String fundID = doc.select("fundID").text();
	 String output = Fund.deleteFund(fundID);
	return output;
	
	
	}

}
