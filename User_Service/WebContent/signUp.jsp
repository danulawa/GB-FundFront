<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/railway.css">
	<script src="javascript.js"></script>
	<meta charset="ISO-8859-1">
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
  
	<title>Sign Up</title>
</head>
<section>
<body>
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
	<div class ="background">
	<form action="sign" method ="post" class="bgcolor" onSubmit = "return checkPassword(this)">
	<h1 style="color:white;text-align:center;background:#145A32 ;height:10%;font-size:50px;">Enter Details</h1>
	<div class="signup">
	<div class="input-fields">
		<label for="fname">Full Name </label>
		<input type="text" name="name" placeholder="Your full name" required><br>
		
		<label for="gender">Gender </label><br>
		<input type="radio" name="gender" value="Male">Male
		<input type="radio" id="female" name="gender" value="female">Female<br><br>
		
		<label for="email">Email</label>
		<input type="email" name="email" placeholder="Your Email"><br>
		
		<label for="phone">Phone Number </label>
		<input type="text" name="phone" placeholder="Your Phone number"><br>
		
		<label for="nationality">Nationality </label>
		<input type="text" name="nation" placeholder="Your nationality"><br>
		
		<label for="city">City </label>
		<input type="text" name="city" placeholder="Your city"><br>
	</div>
	<div class="confidential">
		<label for="un">User Name </label><br>
		<input type="text" name="UID" placeholder="Your user name"><br>
		
		<label for="pass">Password</label>
    	<input type="password" id="pass" name="password1"pattern="(?=.*[a-z])(?=.*[A-Z]).{6,}" placeholder="Include at least one uppercase & lowercase letter,6 or more characters"/>
    
    	<label for="c_pass">Confirm Password</label>
   	  	<input type="password" id="c_pass" name="password2"placeholder="Re-type password" />
		
		<input type="checkbox" id="check" onclick="enable()" required>
		<label for="check">I accept the<a href="termsAndConditions.html">terms and conditions</a> provided  </label><br><br>
			
		<input type="submit" name="submit" value="Sign Up" id="subbutton" disabled>
	</div>
	</div>
	</form>
	</div>
	<div>
		<footer>
  		 	<a href="#" title="To Top" style="float:right">
   	 		<span class="glyphicon glyphicon-chevron-up">GO TO TOP</span><br>
    
  			</a>
  			<p class="text-center"><b>TR Railway system Made By  <a href="https://www.google.com" >  Kandy Crew</a></b></p>

 		</footer>
 	</div>
</body>
</section>
</html>