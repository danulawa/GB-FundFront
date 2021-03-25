<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.user.User" %>

	<%
		//Insert item----------------------------------
		
		if (request.getParameter("userCode") != null)
		 {
			 User user = new User();
			 String stsMsg = user.insertDetails(request.getParameter("userCode"),
			 request.getParameter("Name"),
			 request.getParameter("NIC"),
			 request.getParameter("email"),
			 request.getParameter("phone"),
			 request.getParameter("userType"),
			 request.getParameter("username"),
			 request.getParameter("password"));
			 
			 session.setAttribute("statusMsg", stsMsg);
		 }
	
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
</head>
<body>
<body>

<div class="container">
	<h1>User Management</h1>
	<form method="post" action="Register.jsp">
	 <div class="row">
		 <div class="col">
	
			 User code: <input name="userCode" type="text"  class="form-control" placeholder="Please provide your user code"><br></div> </div>
			 full Name: <input name="Name" type="text"  class="form-control" class="form-control" placeholder="Please provide your Full Name"><br>
			 NIC: <input name="NIC" type="text" id="NIC" class="form-control" placeholder="Please provide your NIC number" name="NIC"><br>
			 Email: <input name="email" type="email" class="form-control" placeholder="Please provide your email"><br>
			 Contact Number: <input name="phone" type="number" class="form-control" placeholder="Please provide your Contact Number"><br>
			 User Type: <select name="userType" id="type">
			 					<option value="admin">Admin</option>
      							<option value="researcher">Researcher</option>
      							<option value="Customer">Customer</option>
      					 </select>
			 UserName: <input name="username" type="text" class="form-control" placeholder="Please provide your User Name"><br>
			 Password: <input name="password" type="text" class="form-control" placeholder="Please provide your password"><br>
			 <input name="btnSubmit" type="submit" value="Save" class="btn btn-primary">
		</form>
		 <% out.print(session.getAttribute("statusMsg"));%> 
		<br>
		<%
			User user = new User();
		out.print(user.readItems());
		%>

</body>
</html>