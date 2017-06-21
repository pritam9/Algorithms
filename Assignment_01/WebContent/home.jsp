<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!--script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script-->
	<script src="js/signUp.js"></script>
<h1>
	Hello world!  
</h1>

	<div class="container">
		<!-- <table> -->
			<form
				class="form-signin" method="post" data-toggle="validator" role="form">
				<h2 class="form-signin-heading">Please SignUp</h2>
				<label for="inputName" class="sr-only">Full Name</label>
				<input id="inputName"
					class="form-control" placeholder="inputName" required/>
				<label for="inputUsername" class="sr-only">Username</label>
				<input id="inputUsername"
					class="form-control" placeholder="Username" required/>
				<label for="inputPassword" class="sr-only">Password</label>
				<input type="password" id="inputPassword" 
					class="form-control" placeholder="Password" required/>
				<button class="btn btn-lg btn-primary btn-block" onclick="return registerUser();">SignUp</button>
				<h4 class="error-msg hide">Please enter all the fields</h4>
			</form>
			<% if (request.getAttribute("signUpError")==null) {}else{%>
			    <label class="has-error control-label col-sm-3" for="usr7"></label>
			    <div class="col-sm-6">
			      <label id="usr7" style="color: red;"><%=request.getAttribute("signUpError") %></label>
			    </div>
			  <%} %>
		<!-- </table> -->
	</div>
<a href="login.jsp">Login</a>
<a href="api/get/myDetails">GetProducts</a>
<form
				class="form-signin" method="post" data-toggle="validator" role="form">
				<button class="btn btn-lg btn-primary btn-block" onclick="return getProducts();">GetProducts</button>
</form>

</body>
</html>
