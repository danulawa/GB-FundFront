<%@page import="model.FundModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h3>Fund Service</h3>
<form id="formItem" name="formItem">
Researcher Id: 
 <input id="researcherId" name="researcherId" type="text" 
 class="form-control form-control-sm">
 <br> Description: 
 <input id="description" name="description" type="text" 
 class="form-control form-control-sm">
 <br> Funding Amount: 
 <input id="fundingAmount" name="fundingAmount" type="text" 
 class="form-control form-control-sm">
 <br> Funder Name: 
 <input id="funderName" name="funderName" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidFundIdSave" 
 name="hidFundIdSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 FundModel fundObj = new FundModel(); 
 out.print(fundObj.readFunds()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>