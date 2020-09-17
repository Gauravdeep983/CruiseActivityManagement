<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event Coordinator</title>
</head>
<body>
<div class="content">
<table>
<tr>
<td><h1>Hello ${user.firstName}</h1></td>
<td>(Passenger Homepage)</td>
<td><a href="<c:url value='/UserController?action=logout' />">
<input type="submit" value="Logout"></a></td>
</tr>
<tr>
<td>
<table>
<tr>

<td><a href="<c:url value='/EventController?action=viewAllEvents' />">
<input type="submit" value="View Available Events"></a>
</td>

<td><a href="<c:url value='/EventController?action=userReservedEvents' />">
<input type="submit" value="View Reserved Events"></a>
</td>

<td><a href="<c:url value='/UserController?action=ViewProfile' />">
<input type="submit" value="View Profile"></a></td>
</tr> 
</table>
</td>
</tr>
</table>
</div>
</body>
</html>