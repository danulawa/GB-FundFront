package com;

import model.Product; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Product") 
public class ProductService 
{ 
Product productObj = new Product(); 
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readProducts() 
{     
return productObj.readProducts(); 
} 
@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertProduct(@FormParam("pTitle") String title, 
@FormParam("pDesc") String desc, 
@FormParam("pPrice") String price, 
@FormParam("resName") String name,
@FormParam("date") String date)

{ 
String output = productObj.insertProduct(title, desc, price, name, date); 
return output; 
}


@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateProduct(String productData) 
{ 
//Convert the input string to a JSON object 
JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject(); 
//Read the values from the JSON object
String pId = productObject.get("pId").getAsString(); 
String pTitle = productObject.get("pTitle").getAsString(); 
String pDesc = productObject.get("pDesc").getAsString(); 
String pPrice = productObject.get("pPrice").getAsString(); 
String resName = productObject.get("resName").getAsString();
String date = productObject.get("date").getAsString(); 

String output = productObj.updateProduct(pId, pTitle, pDesc,pPrice, resName, date); 
return output; 
}

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteProduct(String productData) 
{ 
//Convert the input string to an XML document
Document doc = Jsoup.parse(productData, "", Parser.xmlParser()); 

//Read the value from the element <itemID>
String pId = doc.select("pId").text(); 
String output = productObj.deleteProduct(pId); 
return output; 
}

}