<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
  <div class="header_resize">
      <div class="logo"><h1><a href="<c:url value='/' />">Register User</a></h1></div>
  </div>
<table>
 <tr>
 <td>
 
 <form name="ViewProfile" action="<c:url value='/UserController' />" method="post">
 <table>
 <c:forEach items="${user}" var="item" varStatus="status">
			<tr class="myTableRow">
				
			<td class="myTable20 "><c:out value="${item.event_name}" /></td>
			<td class="myTable35 "><c:out value="${item.capacity}" /></td>
			<td class="myTable20 "><c:out value="${item.location}" /></td>
			<td class="myTable30 "><c:out value="${item.duration}" /></td>
			<td class="myTable30 "><c:out value="${item.type}" /></td>
            
			</tr>

 <tr>
 <td> Username: </td>
 <td class="myTable20 "><c:out value="${user.username}" /></td>
 </tr>
 
 <tr>
 <td> First Name: </td>
 <td class="myTable20 "><c:out value="${{user.fname}" /></td> </tr>

 
 <tr>
 <td> Last Name: </td>
<td class="myTable20 "><c:out value="${{user.lname}" /></td></tr>
  
  
  <tr>
 <td> Phone: </td>
 <td class="myTable20 "><c:out value="${{user.phone}" /></td></tr>

 
 <tr>
 <td> Email: </td>
<td class="myTable20 "><c:out value="${{user.email}" /></td>
 </tr>
 
 <tr>
 <td> Room Number: </td>
<td class="myTable20 "><c:out value="${{user.room_number}" /></td> </tr>
  
 <tr>
 <td> Deck Number: </td>
<td class="myTable20 "><c:out value="${{user.location}" /></td> </tr>
  
 <tr>
 <td> Membership Type: </td>
 <td class="myTable20 "><c:out value="${{user.membership_type}" /></td> </tr>


</c:forEach>
 
 </table>

	<input name="back" type="submit" value="Back" action="/passengerHome.jsp">

	
</form>
</td>
</tr>
</table>
</body>
</html>