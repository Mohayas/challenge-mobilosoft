
// reset update order form
function resetUpdateFormFunc() {
	 $("#hidden-customer-id").val("");
	 $("#input-customer-fname").val("");
	 $("#input-customer-lname").val("");
	 $("#input-customer-phone").val("");
	 $("#input-customer-email").val("");	
}

// check if a string is empty
function isEmpty(str) {
	return (!str || 0 === str.length);
}
// handle the Customers
var Customers = function() {

	var hideAlerts = function() {
		$(document).ready(function(event) {
			$("#customer-deleted-alert").hide();
			$("#customer-submiteded-alert").hide();
			console.log("ready");
		});
	};
	var setToDeleteCustomerId = function() {
		$(".delete-customer-btn").mouseover(function() {
			var customerId = $(this).attr("to-delete-id");
			$("#to-delete-customer-id-hidden").val(customerId);
			console.log("customerId  "+customerId);
			
		});
	};
	var submitForm = function() {
		$("#customer-form").submit(function() {
			
			alert("in sumit event");
			console.log("submit customer clicked");
			// getting the data from form and building the order object
			var customerId = $("#hidden-customer-id").val();
			var fname = $("#input-customer-fname").val();
			var lname = $("#input-customer-lname").val();
			var phone = $("#input-customer-phone").val();
			var email = $("#input-customer-email").val();
	
			//verifying the data nullability
			if (isEmpty(fname.trim()) | isEmpty(lname.trim())
					| isEmpty(phone.trim()) | isEmpty(email.trim())) {
				alert("All fields are required! ");
				return false;
			}
			var customer = {
				"id" : customerId,
				"firstName" : fname,
				"lastName" : lname,
				"email" : email,
				"tel" : phone
			};
			console.log(customer);
			
			//calling the server to add submit customer
			$.ajax({
				url : "/customer",
				data : JSON.stringify(customer),
				type : "POST",
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {

					console.log(data);
					// reset the form
					resetUpdateFormFunc();
					//show add order notification and hide it after 5 seconds via setTimeout
					$("#customer-submiteded-alert").show();
					setTimeout(function() {
						$("#customer-submiteded-alert").fadeOut(3000);
					}, 5000);

				},
				error : function(jqXHR, textStatus, errorThrown) {
					
						console.log('ajax error');
						throw new Error("an unexpected error occured:"+ errorThrown);
						console.log(errorThrown);
						}
			});
			return false;
		});
	};
	// delete customer function
	var deleteCustomer = function() {
		$("#confirm-delete-btn").on("click", function() {
			
			var customerId = $("#to-delete-customer-id-hidden").val();

			console.log(customerId);

			$.ajax({
				url : "/customer/" + customerId+"?forceDelete=false",
				type : "DELETE",
				async : false,
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					console.log(data)
					// the customer's been  deleted successfully
					if(data==2) {
						alert("in 2 ");
					console.log(data);
				
					// delete customer row from table
					$("#tr-customer-id-" + customerId).fadeOut(2000);
					// show delete notification
					$("#customer-deleted-alert").show();
					// hide delete notification
					setTimeout(function() {
						$("#customer-deleted-alert").fadeOut(3000);
					}, 5000);

					console.log("customer deleted successfully");
					}
					//the customer has some orders
					if(data==3){
						alert("in 3 ");
						$("#deleteCustomerWithOrdersConfirmation").modal("show");
					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log('ajax error');
					console.log(errorThrown);
				}
			});

		});
	};
	// delete customer function with orders
	var deleteCustomerWithOrders = function() {
		$("#confirm-force-delete-btn").on("click", function() {
			
			var customerId = $("#to-delete-customer-id-hidden").val();

			console.log(customerId);

			$.ajax({
				url : "/customer/" + customerId+"?forceDelete=true",
				type : "DELETE",
				async : false,
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					console.log(data)
					// the customer's been  deleted successfully
					if(data===2) {
					console.log(data);
				
					// delete customer row from table
					$("#tr-customer-id-" + customerId).fadeOut(2000);
					// show delete notification
					$("#customer-deleted-alert").show();
					// hide delete notification
					setTimeout(function() {
						$("#customer-deleted-alert").fadeOut(3000);
					}, 5000);

					console.log("customer deleted successfully");
					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log('ajax error');
					console.log(errorThrown);
				}
			});

		});
	};
	// handle submitting Customer
	var submitCustomer = function() {
		$("#confirm-submit-btn").on(
				"click",
				function() {

					console.log("submit customer clicked");
					// getting the data from form and building the order object
					var customerId = $("#hidden-customer-id").val();
					var fname = $("#input-customer-fname").val();
					var lname = $("#input-customer-lname").val();
					var phone = $("#input-customer-phone").val();
					var email = $("#input-customer-email").val();
			
					//verifying the data nullability
					if (isEmpty(fname.trim()) | isEmpty(lname.trim())
							| isEmpty(phone.trim()) | isEmpty(email.trim())) {
						alert("All fields are required! ");
						return false;
					}
					var customer = {
						"id" : customerId,
						"firstName" : fname,
						"lastName" : lname,
						"email" : email,
						"tel" : phone
					};
					console.log(customer);
					
					//calling the server to add submit customer
					$.ajax({
						url : "/customer",
						data : JSON.stringify(customer),
						type : "POST",
						crossDomain : true,
						contentType : "application/json; charset=utf-8",
						dataType : "json",
						success : function(data, textStatus, jqXHR) {

							console.log(data);
							// reset the form
							resetUpdateFormFunc();
							//show add order notification and hide it after 5 seconds via setTimeout
							$("#customer-submiteded-alert").show();
							setTimeout(function() {
								$("#customer-submiteded-alert").fadeOut(3000);
							}, 5000);
							
							$('#submitCustomerConfirmation').modal('toggle');
						},
						error : function(jqXHR, textStatus, errorThrown) {
							
								console.log('ajax error');
								throw new Error("an unexpected error occured:"+ errorThrown);
								console.log(errorThrown);
								}
					});
					return false;
				});

	};

	return {
		init : function() {
			hideAlerts();
			setToDeleteCustomerId();
			deleteCustomer();
			deleteCustomerWithOrders();
			submitCustomer();
			submitForm();
		}
	};

}();

// initialize and activate the script
$(function() {
	Customers.init();
});