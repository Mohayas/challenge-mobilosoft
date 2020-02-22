//render the row html func
function renderTableRow(order) {

	
}

// reset update order form
function resetUpdateFormFunc() {
	
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
			console.log("ready");
		});
	};
	var settoDeletecustomerId = function() {
		$(".delete-customer-btn").mouseover(function() {
			var customerId = $(this).attr("to-delete-id");
			$("#to-delete-customer-id-hidden").val(customerId);
			console.log("customerId  "+customerId);
			
		});
	};
	
	// delete customer function
	var deleteCustomer = function() {
		$("#confirm-delete-btn").on("click", function() {
			
			var customerId = $("#to-delete-customer-id-hidden").val();

			console.log(customerId);

			$.ajax({
				url : "/customer/" + customerId,
				type : "DELETE",
				async : false,
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {

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

				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log('ajax error');
					console.log(errorThrown);
				}
			});

		});
	};

	return {
		init : function() {
			hideAlerts();
			settoDeletecustomerId();
			deleteCustomer();
		}
	};

}();

// initialize and activate the script
$(function() {
	Customers.init();
});