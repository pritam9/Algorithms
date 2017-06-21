/*!
 * Start Bootstrap - Agency Bootstrap Theme (http://startbootstrap.com)
 * Code licensed under the Apache License v2.0.
 * For details, see http://www.apache.org/licenses/LICENSE-2.0.
 */

// jQuery for page scrolling feature - requires jQuery Easing plugin
function registerUser(){

	var username = $("#inputUsername").val();
	var password = $("#inputPassword").val();
	var full_name = $("#inputName").val();
	//alert('Hello register! '+full_name);
	if(username && password){
		var user = {};
		user.username = username;
		user.password = password;
		user.name = full_name;
		//alert('B aja!');
		$.ajax(
				{
					url:"signUp",
					type: "POST",
					data: "username="+user.username+"&password="+user.password+"&name="+user.name,
					success: onSuccess,
					error: function(e){
						alert(e.Message);
					},
					dataType: "json",
					contentType: "application/x-www-form-urlencoded"
				} );
	} else {
		alert('Error calling signUp API...');
		$(".error-msg").removeClass("hide");
		$(".error-msg").html("Please enter all the fields");
	}


	return false;
}

function onSuccess(data){
	//var obj = JSON.parse(data.data);
	//alert(data.data.username);
	if(data.status != 0){
		$(".error-msg").html(data.message);
		$(".error-msg").removeClass("hide");
	} else {
		alert("Registration Successfull!!");
		window.location.href = "login.jsp";
	}
}


function getProducts(){

	//var username = $("#inputUsername").val();
	//var password = $("#inputPassword").val();
	//var full_name = $("#inputName").val();
	//alert('Hello register! '+full_name);
	alert('GetProducts!!!');
	$.ajax(
			{
				url:"api/get/getProducts",
				type: "POST",
				data: "region=produce",
				success: onSuccess,
				error: function(e){
					alert(e.Message);
				},
				dataType: "json",
				contentType: "application/x-www-form-urlencoded"
			} );
	return false;
	/*if(username && password){
		var user = {};
		user.username = username;
		user.password = password;
		user.name = full_name;
		//alert('B aja!');
		$.ajax(
				{
					url:"signUp",
					type: "POST",
					data: "username="+user.username+"&password="+user.password+"&name="+user.name,
					success: onSuccess,
					error: function(e){
						alert(e.Message);
					},
					dataType: "json",
					contentType: "application/x-www-form-urlencoded"
				} );
	} else {
		alert('Error calling signUp API...');
		$(".error-msg").removeClass("hide");
		$(".error-msg").html("Please enter all the fields");
	}
*/

	
}