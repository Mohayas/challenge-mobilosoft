
	
var Orders = function() {
	
	var addNewOrder = function() {
		$("#add-order-btn")
				.on("click",
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
							
							var url = "/order";
							var mtd = "POST";
								
							$.ajax({
										url : url,
										data : JSON.stringify(order),
										type : mtd,
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
												+ "</td></td>" +
														"<td>" +
														"<button type='button' class='btn btn-link'>Update</button>" +
														"<button type='button' class='btn btn-link'>Delete</button></td>";
														
														$('#orders-tbody').append(tr);
										
										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											if (jqXHR.status === 401
													|| jqXHR.status === 403) {
												console.log('ajax error');
												alert(4);
											} else {
												 throw new Error("an unexpected error occured:" + errorThrown);
												console.log(errorThrown);
												alert(5);
											}
										}
									});
							return false;
						});
		
	};
		var deleteOrder = function() {
		$("#delete-prof-btn").on("click", function() {
			
			$.ajax({
				url : "/order/"+toDeleteProfId,
				type : "DELETE",
				crossDomain : true,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				headers : createAuthorizationTokenHeader(),
				success : function(data, textStatus, jqXHR) {
					console.log(data);
					$("#prof-row-"+toDeleteProfId).addClass('deleted-row');
					$("#prof-row-"+toDeleteProfId).fadeOut(3000);

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

		});
	};

	
	return {
		init : function() {
			
			addNewOrder();
			deleteOrder();
		}
	};

}();

// initialize and activate the script
$(function() {
	Orders.init();
});