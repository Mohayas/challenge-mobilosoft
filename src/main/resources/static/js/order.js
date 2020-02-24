//render the row html func
function renderTableRow(order) {

	var tr = "<td>" + order.id + "</td>" + "<td>" + order.name + "</td>"
			+ "<td>" + order.type + "</td>" + "<td>"
			+ "<button type='button' to-update-id='" + order.id
			+ "' class='btn btn-link update-btn'>Update</button>"
			+ "<button type='button' to-delete-id='" + order.id+"'  data-toggle='modal' data-target='#deleteOrderConfirmation' class='btn btn-link tr-delete-btn'>"
			+ "<span class='text-danger'>Delete</span></button></td>";
	return tr;
}

// reset update order form
function resetUpdateFormFunc() {
	$("#update-order-btn").hide();
	$("#cancel-update-order-btn").hide();
	$("#add-order-btn").show();
	$("#input-order-name").val("");
	$("#input-order-type").val("");
	//$("#select-customer option:first").attr('selected');
}

// check if a string is empty
function isEmpty(str) {
	return (!str || 0 === str.length);
}
// handle the orders
var Orders = function() {
	
	// set orderId that's gonna be delete in hidden input
	var setToDeleteOrderId = function() {
		$(document.body).on("click", ".tr-delete-btn",function() {
			var orderId = $(this).attr("to-delete-id");
			$("#hidden-order-id-to-delete").val(orderId);
			console.log("order id :;  "+orderId);
			
			
		});
	};
	var hideAlerts = function() {
		$(document).ready(function(event) {

			$("#order-deleted-alert").hide();
			$("#order-updated-alert").hide();
			$("#order-added-alert").hide();
			console.log("ready");

		});
	};
// handle adding new order
	var addNewOrder = function() {
				$("#add-order-btn").on(
				"click",
				function() {

					console.log("clicked");
					// getting the data from form and building the order object
					var orderName = $("#input-order-name").val();
					var orderType = $("#input-order-type").val();
					var orderCustomerId = $("#select-customer").val();
					if (orderName)

						var customer = {
							"id" : orderCustomerId
						};
					console.log(customer);
						
					//verifying the user's input
					if (isEmpty(orderName.trim()) | isEmpty(orderType.trim())
							| isEmpty(orderCustomerId.trim())) {
						alert("All fields are required ");
						return false;
					}
					
					var order = {

						"name" : orderName,
						"type" : orderType,
						"customer" : customer
					};
					console.log(order);
					
					//calling the server to add new order
					$.ajax({
						url : "/order",
						data : JSON.stringify(order),
						type : "POST",
						crossDomain : true,
						contentType : "application/json; charset=utf-8",
						dataType : "json",
						success : function(data, textStatus, jqXHR) {

							console.log(data);
							// rendring the table row and appending it to the orders table
							var tr = renderTableRow(data);

							$('#orders-tbody').append(
									"<tr id='tr-order-id-" + data.id + "'>" + tr
											+ "</tr>");
							// reset the form
							resetUpdateFormFunc();
							//show add order notification and hide it after 5 seconds via setTimeout
							$("#order-added-alert").show();
							setTimeout(function() {
								$("#order-added-alert").fadeOut(3000);
							}, 5000);

						},
						error : function(jqXHR, textStatus, errorThrown) {
							
								console.log('ajax error');
								throw new Error("an unexpected error occured:"+ errorThrown);
								console.log(errorThrown);
								$("#modalPanelForErros").modal("show");
}
					});
					return false;
				});

	};

	// fill update form with the order data
	var fillUpdateOrderForm = function() {
		$(document.body).on("click", ".update-btn", function() {

			var orderId = $(this).attr("to-update-id");

			console.log(orderId);
			// getting all order data from the server
			$.ajax({
				url : "/order/" + orderId,
				type : "GET",
				async : false,
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {

					console.log(data);
					//just filling the form with data coming from server
					$("#input-order-name").val(data.name);
					$("#input-order-type").val(data.type);
					$("#hidden-order-id").val(data.id);
					$('#select-customer option').each(function() {
						if ($(this).val() == data.customer.id) {
							$(this).prop("selected", true);
						}
					});
					
					// show update and cancel buttons,  and hide add button
					$("#update-order-btn").show();
					$("#cancel-update-order-btn").show();
					$("#add-order-btn").hide();

				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(errorThrown);
				}
			});
		});
	};
	// reset add/update form function
	var resetUpdateFormListenner = function() {
		$("#cancel-update-order-btn").on("click", function() {
			resetUpdateFormFunc();
			return false;
		});
	};
// handle the update order event
	var updateOrder = function() {
		$("#update-order-btn").on("click", function() {

			//building the order object
			var orderId = $("#hidden-order-id").val();

			var orderName = $("#input-order-name").val();
			var orderType = $("#input-order-type").val();
			var orderCustomerId = $("#select-customer").val();

			var customer = {
				"id" : orderCustomerId
			};
			
			var order = {
				"id" : orderId,
				"name" : orderName,
				"type" : orderType,
				"customer" : customer
			};

			console.log(order);

			//calling the server to update order
			$.ajax({
				url : "/order",
				type : "PUT",
				data : JSON.stringify(order),
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					console.log(data);
					// reset add/update form
					resetUpdateFormFunc();
					//show update notification
					$("#order-updated-alert").show();
					//hide update notification after 5 seconds
					setTimeout(function() {
						$("#order-updated-alert").fadeOut(3000);
					}, 5000);
					//render the new  table row with updated order data
					var tr = renderTableRow(order);
					//replace the old row with the new updated row 
					$("#tr-order-id-" + orderId).html(tr);

				},
				error : function(jqXHR, textStatus, errorThrown) {

					console.log('ajax error');
					console.log(errorThrown);
					$("#modalPanelForErros").modal("show");
				}
			});
			return false;
		});
	};
	// delete order function
	var deleteOrder = function() {
		$("#confirm-delete-btn").click( function() {
			
			var orderId =$("#hidden-order-id-to-delete").val();

			console.log(orderId);

			$.ajax({
				url : "/order/" + orderId,
				type : "DELETE",
				async : false,
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {

					console.log(data);

					// show delete notificatio
					$("#order-deleted-alert").show();
					// delete order row from tble
					$("#tr-order-id-" + orderId).fadeOut(2000);
					// hide delete notification
					setTimeout(function() {
						$("#order-deleted-alert").fadeOut(3000);
					}, 5000);
					// reset add/update form
					resetUpdateFormFunc();
					console.log("deleted successfully");

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
			addNewOrder();
			fillUpdateOrderForm();
			resetUpdateFormListenner();
			updateOrder();
			deleteOrder();
			setToDeleteOrderId();
		}
	};

}();

// initialize and activate the script
$(function() {
	Orders.init();
});