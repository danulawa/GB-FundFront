$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateFundForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "FundService", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onFundSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onFundSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}

$(document).on("click", ".btnUpdate", function(event)
{ 
$("#hidFundIDSave").val($(this).data("itemid")); 
 $("#researcherId").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#description").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#fundingAmount").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#funderName").val($(this).closest("tr").find('td:eq(3)').text()); 
});

$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "FundService", 
 type : "DELETE", 
 data : "fundId=" + $(this).data("fundId"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});

function onItemDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
function validateItemForm() 
{ 
// CODE
if ($("#researcherId").val().trim() == "") 
 { 
 return "Insert Researcher Id"; 
 } 
// NAME
if ($("#description").val().trim() == "") 
 { 
 return "Insert Description."; 
 }
// PRICE-------------------------------
if ($("#fundingAmount").val().trim() == "") 
 { 
 return "Insert Funding Amount."; 
 } 
// is numerical value
var fundingAmount = $("#fundingAmount").val().trim(); 
if (!$.isNumeric(fundingAmount)) 
 { 
 return "Insert a numerical value for Funding Amount."; 
 } 
// convert to decimal price
 $("#fundingAmount").val(parseFloat(fundingAmount).toFixed(2)); 
// DESCRIPTION------------------------
if ($("#funderName").val().trim() == "") 
 { 
 return "Insert Funder Name."; 
 } 
return true; 
}
