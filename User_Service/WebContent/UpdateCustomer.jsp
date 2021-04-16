<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/railway.css">
	<link rel="stylesheet" type="text/css" href="css/railway.css">
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  	<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  	
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
 	<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  	
<title>Update Details</title>
</head>
<body class="background">
	<div class="top-wall" style="position: relative">
  		
  			<div class="text-block"> 
    			<h4><b>TR Railway</b></h4>
  			</div>
    			
	</div>
	<nav class="navbar navbar-inverse">
  		<div class="container-fluid">
    		<div class="navbar-header">
     			 <a class="navbar-brand" href="#">TR RAILWAY</a>
    		</div>
    		
    		<ul class="nav navbar-nav">
      			<li class="active"><a href="#">HOME</a></li>
      			<li><a href="#">TRAINS</a></li>
      			<li class="dropdown">
        			<a class="dropdown-toggle" data-toggle="dropdown" href="#">SERVICES
        				<span class="caret"></span>
        			</a>
        					<ul class="dropdown-menu">
          						<li><a href="#">TICKET RESERVATION</a></li>
        						<li><a href="#">TRAIN RESERVATION</a></li>
          						<li><a href="#">PASSENGER SERVICES</a></li>
        					</ul>					
      			</li>
      			<li><a href="#">ABOUT US</a></li>
      			<li><a href="#">CONTACT US</a></li>
    		</ul>
    		<ul class="nav navbar-nav navbar-right">
           		<li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> LOGIN</a></li>
    		</ul>
    		
    	<form class="navbar-form " action="/action_page.php" >
      		
      		<div class="input-group" >
        		<input type="text" class="form-control" placeholder="Search" name="search">
        		<div class="input-group-btn">
         			 <button class="btn btn-default" type="submit">
           				 <i class="glyphicon glyphicon-search"></i>
         			</button>
        		</div>
      		</div>
    	</form>  	
  		</div>
	</nav>
	<form action="update" method="post" class="bgcolor">
	<h1 style="color:white;text-align:center;background:#145A32 ;height:10%;font-size:50px;">Update Profile </h1>	
	<div class="signup">
	<div class="input-fields">	
	<%
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String nationality = request.getParameter("nationality");
		String city = request.getParameter("city");
		String username = request.getParameter("username");
		String password = request.getParameter("pass");
	%>
	
			
			Customer ID<input type = "text" name ="custId" value="<%= id %>" style="color:#c5ecfd;" readonly>
			<label for="fname">Full Name </label><input type = "text" name ="name" value="<%= name %>" style="color:#c5ecfd;"><br>
			Gender<input type = "text" name ="gender" value="<%= gender %>" style="color:#c5ecfd;"><br>
			Email<input type = "text" name ="email" value="<%= email %>" style="color:#c5ecfd;"><br>
			Phone<input type = "text" name ="phone" value="<%= phone %>" style="color:#c5ecfd;"><br>
			Nationality :<input type="text" name="nation" value="<%= nationality %>" style="color:#c5ecfd;"><br>
			City :<input type="text" name="city" value="<%= city %>" style="color:#c5ecfd;"><br>
			</div>
			<div class="confidential">
			User Name :<input type="text" name="UID" value="<%= username %>" style="color:#c5ecfd;"><br>
			Password :<input type="password" name="pass" value="<%= password %>" style="color:#c5ecfd;"><br>
		
		<input type="submit" name="submit" value="Update data">
		</div>
		</div>
		
	</form>
	<footer>
  <a href="#" title="To Top" style="float:right">
    <span class="glyphicon glyphicon-chevron-up">GO TO TOP</span><br>
    
  </a>
  <p class="text-center"><b>TR Railway system Made By  <a href="https://www.google.com" >  Kandy Crew</a></b></p>
</footer>
</body>
</html>