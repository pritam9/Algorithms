<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link href="resources/css/login.css" rel="stylesheet">

</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!--script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script-->
	<script src="js/login.js"></script>
		
	<div class="container">
		<!-- <table> -->
			<form
				class="form-signin" method="post" data-toggle="validator" role="form">
				<h2 class="form-signin-heading">Please login</h2>
				<label for="inputUsername" class="sr-only">Username</label>
				<input id="inputUsername"
					class="form-control" placeholder="Username" required/>
				<label for="inputPassword" class="sr-only">Password</label>
				<input type="password" id="inputPassword"
					class="form-control" placeholder="Password" required/>
				<button class="btn btn-lg btn-primary btn-block" onclick="return verifyLogin();">Login</button>
				<h4 class="error-msg hide">Please enter all the fields</h4>
			</form>
			<% if (request.getAttribute("Error")==null) {}else{%>
			    <label class="has-error control-label col-sm-3" for="usr7"></label>
			    <div class="col-sm-6">
			      <label id="usr7" style="color: red;"><%=request.getAttribute("Error") %></label>
			    </div>
			  <%} %>
		<!-- </table> -->
	</div>

</body>
</html>