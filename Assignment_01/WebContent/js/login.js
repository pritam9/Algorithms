/**
 * 
 */
function verifyLogin(){

	var username = $("#inputUsername").val();
	var password = $("#inputPassword").val();
	//alert('Hello! '+username);
	if(username && password){
		var user = {};
		user.username = username;
		user.password = password;
		alert('B aja!');
		$.ajax(
				{
					url:"login",
					type: "POST",
					data: "username="+user.username+"&password="+user.password,
					success: onSuccess,
					error: function(e){
						alert(e.Message);
					},
					dataType: "json",
					contentType: "application/x-www-form-urlencoded"
				} );
	} else {
		alert('Error calling login API...');
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
		alert(data.data);
		window.location.href = "todo.jsp?id="+data.data;
	}
}