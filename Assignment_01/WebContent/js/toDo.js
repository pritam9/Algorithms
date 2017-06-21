function loadProfileDetails(){

	var _token = getParameterByName('id');
	//alert (_token);
		$.ajax(
				{
					url:"api/get/myDetails",
					type: "POST",
					data: "token="+_token,
					success: onSuccess,
					error: function(e){
						alert(e.Message);
					},
					dataType: "json",
					contentType: "application/x-www-form-urlencoded"
				} );
}

function onSuccess(data){
	var obj=data.data;
	//alert(obj);
	//alert(obj.length);
	if(data.status != 0){
		alert(data.message)
		$(".error-msg").html(data.message);
		$(".error-msg").removeClass("hide");
		window.location.href = "login.jsp";
	} else {
	var html="<table class='table'><thead><th>Username</th><th>Full Name</th></thead>" +
			"<tbody><tr><td>"+obj.username+"</td><td>"+obj.name+"</td></tr></tbody></table>";
	$(".tasks").html(html);
	}
	
}

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}