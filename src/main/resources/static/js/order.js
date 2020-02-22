
var Orders = function() {

	var addNewOrder = function() {
		$("#add-order-btn")
				.on(
						"click",
						function() {

							console.log("clicked");

							var orderName = $("#input-order-name").val();
							var orderType = $("#input-order-type").val();
							var orderCustomerId = $("#select-customer").val();

							var customer = {
								"id" : orderCustomerId
							};
							console.log(customer);
							var order = {

								"name" : orderName,
								"type" : orderType,
								"customer" : customer
							};
							console.log(order);

							$.ajax({
										url : "/order",
										data : JSON.stringify(order),
										type : "POST",
										crossDomain : true,
										contentType : "application/json; charset=utf-8",
										dataType : "json",
										success : function(data, textStatus,
												jqXHR) {

											console.log(data);
											var tr = "<tr>"
													+ "<td>"
													+ data.id
													+ "</td>"
													+ "<td>"
													+ data.name
													+ "</td>"
													+ "<td>"
													+ data.type
													+ "</td></td>"
													+ "<td>"
													+ "<button type='button' class='btn btn-link'>Update</button>"
													+ "<button type='button' class='btn btn-link'>Delete</button></td>";

											$('#orders-tbody').append(tr);

										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											if (jqXHR.status === 401
													|| jqXHR.status === 403) {
												console.log('ajax error');
												alert(4);
											} else {
												throw new Error(
														"an unexpected error occured:"
																+ errorThrown);
												console.log(errorThrown);
												alert(5);
											}
										}
									});
							return false;
						});

	};
	var updateOrder = function() {
		$(".update-btn").on("click", function() {

			var orderId = $(this).attr("to-update-id");

			console.log(orderId);
			var succes = false;
			$.ajax({
				url : "/order/" + orderId,
				type : "PUT",
				async : false,
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					console.log(data);

					succes = true;

				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(errorThrown);
				}

			});

		});
	};
	var fillUpdateOrderForm = function() {
		$(".update-btn").on("click", function() {

			var orderId = $(this).attr("to-update-id");

			console.log(orderId);

			$.ajax({
				url : "/order/" + orderId,
				type : "GET",
				async : false,
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {

					console.log(data);
					$("#input-order-name").val(data.name);
					$("#input-order-type").val(data.type);
					$("#hidden-order-id").val(data.id);
					$('#select-customer option').each(function() {
						if ($(this).val() == data.customer.id) {
							$(this).prop("selected", true);
						}
					});

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
	var resetUpdateForm = function() {
		$("#cancel-update-order-btn").on("click", function() {

			$("#update-order-btn").hide();
			$("#cancel-update-order-btn").hide();
			$("#add-order-btn").show();
			$("#input-order-name").val("");
			$("#input-order-type").val("");
			$("#select-customer option:first").attr('selected',true);
			return false;

		});
	};
	var updateOrder = function() {
		$("#update-order-btn").on("click", function() {
			
			var orderId = 	$("#hidden-order-id").val();;			
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

			var succes = false;
			$.ajax({
				url : "/order/",
				type : "PUT",
				data : JSON.stringify(order),
				async : false,
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					console.log(data);

					succes = true;

				},
				error : function(jqXHR, textStatus, errorThrown) {
					
						console.log('ajax error');				
						console.log(errorThrown);					
				}
			});

			return false;
		});
	};
	
	var deleteOrder = function() {
		$(".delete-btn").on("click", function() {

			var orderId = $(this).attr("to-delete-id");

			console.log(orderId);
			var succes = false;
			$.ajax({
				url : "/order/" + orderId,
				type : "DELETE",
				async : false,
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					console.log(data);

					succes = true;

				},
				error : function(jqXHR, textStatus, errorThrown) {
					if (jqXHR.status === 401 || jqXHR.status === 403) {
						console.log('ajax error');
						$('#auth-error-box').show();
					} else {
						// throw new Error("an unexpected error occured:
						// " +
						// errorThrown);
						console.log(errorThrown);
					}
				}
			});

			if (succes === true) {

				$(this).closest("tr").fadeOut(3000);
			}
			console.log(succes);
		});
	};

	return {
		init : function() {

			addNewOrder();
			fillUpdateOrderForm();
			resetUpdateForm();
			updateOrder();
			deleteOrder();
		}
	};

}();

// initialize and activate the script
$(function() {
	Orders.init();
});