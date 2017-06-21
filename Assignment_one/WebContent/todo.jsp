<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <c:if test="${username == null }">
    String address = websiteContext + "/login.jsp";
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
    dispatcher.forward(request,response);

</c:if>-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ToDo List</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link href="resources/css/login.css" rel="stylesheet">
<script src="resources/js/ToDo.js"></script>

</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	<!-- script src="resources/js/login.js"></script-->
	<div class="container">
		<button type="button" class="btn btn-default btn-sm" onclick="return logout();">
          <span class="glyphicon glyphicon-log-out"></span> Log out
        </button>
	</div>	
	<div class="container">
		
		<h4 class="error-msg hide">Response Data</h4>
		<div class="row">
			<form class="form-inline">
				<div class="form-group">
					<label for="task">New Task</label>
					<input id="task" class="form-control"/>
				</div>
				<div class="form-group">
					<button class='btn-primary btn-block' onclick='return addTask();'>Add Task</button>
				</div>
			</form>
		
		</div>
		<div class="tasks">
		
		</div>

		
	</div>

</body>
<script>
$(document).ready( function () {
	  getToDoList();
	});
</script>

</html>