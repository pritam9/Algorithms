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
	<!-- script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	<script src="resources/js/login.js"></script-->
		
	<div class="container">
		<!-- <table> -->
			<form
				class="form-signin" method="post" data-toggle="validator" role="form" action="login">
				<h2 class="form-signin-heading">Please login</h2>
				<label for="inputUsername" class="sr-only">Username</label>
				<input id="inputUsername" name="inputUsername"
					class="form-control" placeholder="Username" required/>
				<label for="inputPassword" class="sr-only">Password</label>
				<input type="password" id="inputPassword" name ="inputPassword"
					class="form-control" placeholder="Password" required/>
				<button class="btn btn-lg btn-primary btn-block" >Login</button>
				<h4 class="error-msg hide">Please enter all the fields</h4>
			</form>
		<!-- </table> -->
	</div>

</body>
</html>