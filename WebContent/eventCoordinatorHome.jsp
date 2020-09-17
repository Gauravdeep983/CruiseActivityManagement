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
<td>(EC Homepage)</td>
<td><a href="<c:url value='/UserController?action=logout' />">
<input type="submit" value="Logout"></a></td>
</tr>
<tr>
<td>
<table>
<tr>
<td><input type="submit" value="View Event Summary"></td>


</tr> 
</table>
</td>
</tr>
</table>
</div>
</body>
</html>