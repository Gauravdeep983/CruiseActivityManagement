<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="header">
	<h1>Cruise Ship Activity Coordination System</h1>
</div>
<div class="content">
<label class="errorPane"><c:out value='${errorMsgs.errorMsg}'/></label>
	<form action="<c:url value='/UserController' />" method="POST">
		<input type="hidden" name="action" value="login">
		<label>Username: </label>
		<input type="text" value="" name="username">
		<label class="errorPane"><c:out value='${errorMsgs.usernameError}'/></label>
		<br><br>
		<label>Password: </label>
		<input type="password" value="" name="password">
		<label class="errorPane"><c:out value='${errorMsgs.passwordError}'/></label>
		<br><br>
		<input type="submit" value="Login">	   
	</form>
	<br>
	<a href="<c:url value='/register.jsp' />"><span>Register</span></a>
	<br>
	<hr>
	
	
</div>

</body>
</html>