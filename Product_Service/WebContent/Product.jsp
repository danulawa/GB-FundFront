<%@ page import = "com.Product" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<%
    	if (request.getParameter("action") != null)
    { 
    	 Product resObj = new Product ();
    	 
    	  
    	 
    	 //call the insert function
    	 if ( request.getParameter("action").equalsIgnoreCase("insert"))
    	 {
    		 String stsMsg = resObj.insertProject(request.getParameter("pTitle"),request.getParameter("pDesc"),request.getParameter("resName"),request.getParameter("date"));
     		 session.setAttribute("statusMsg", stsMsg);
     	 }
    	//call the update function
    	 else if (request.getParameter("action").equalsIgnoreCase("update"))
    	 {
    		System.out.println("newUpdate");
    	    String stsMsg = resObj.updateProject(Integer.parseInt(request.getParameter("pId")),request.getParameter("pTitle"),request.getParameter("pDesc"),request.getParameter("resName"),request.getParameter("date"));
    	 	session.setAttribute("statusMsg", stsMsg);
    	 }
    	//call the delete function
    	 else if(request.getParameter("action").equalsIgnoreCase("delete"))
    	 {
    		System.out.println("newDelete");
    	 	String stsMsg = resObj.deleteProject(Integer.parseInt(request.getParameter("pId")));
    	    session.setAttribute("statusMsg", stsMsg);
    	 }
     
    }
    %>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Items Management</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<div class="container">
      <div class="row">
           <div class="col">
 
            </div>
     </div>
</div>

<body>
	<h1>Project Management</h1>
	

	<%
			if (request.getParameter("action") != null) {
				//call the select function
				if (request.getParameter("action").toString().equalsIgnoreCase("select")) {
			Product resObj = new Product ();
			out.print(resObj.readOneProject(Integer.parseInt(request.getParameter("id"))));
				} 
				else {
			out.print("<form method='post' action='Researcher.jsp'> " + "<input name='action' value='insert' type='hidden'> "
			+ "Project Title: <input name='pTitle' type='text' class='form-control'><br>"
			+ "Project Description: <input name='pDesc' type='text' class='form-control'><br> "
			+ "Researcher Name: <input name='resName' type='text' class='form-control'><br> "
			+ "Date: <input name='date' type='text' class='form-control'><br> "
			+ "<input name='btnSubmit' type='submit' value='Save' class='btn btn-primary'> " + "</form>");
				}
			}
			else {
				out.print("<form method='post' action='Researcher.jsp'> " + "<input name='action' value='insert' type='hidden'> "
				+ "Project Title: <input name='pTitle' type='text' class='form-control'><br>"
				+ "Project Description: <input name='pDesc' type='text' class='form-control'><br> "
				+ "Researcher Name: <input name='resName' type='text' class='form-control'><br> "
				+ "Date: <input name='date' type='text' class='form-control'><br> "
				+ "<input name='btnSubmit' type='submit' value='Save' class='btn btn-primary'> " + "</form>");
			}
		%>
	
	<div class='alert alert-success'>
	<%
		out.print(session.getAttribute("statusMsg"));
	%>
	</div>
	
	<br>
	<%
		Product resObj = new Product ();
	 	out.print(resObj.readProject());
	%>
</body>	
</html>

