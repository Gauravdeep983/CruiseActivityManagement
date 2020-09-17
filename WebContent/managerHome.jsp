<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Manager Dashboard</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<body>

  <div class="header_resize">
      
      <div class="logo"><h1><a href="<c:url value='/' />">Hello Manager!</a></h1></div>
      <form action="<c:url value='/UserController?action=logout' />" method="post">
      	<input type="submit" value="Logout">
      </form>
      
  </div>
  
         
 <form action="<c:url value='/events.jsp' />" method="post">          
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTable30"><input name="createEvent" type="submit" action="/events.jsp" value="Create Events"></th>							
			</tr>		
 </table>
 </form>
 <form action="<c:url value='/UserController' />" method="post">     
 <input type="hidden" name="action" value="listCreatedEvents">
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
			 <th class="myTable35"><input name="viewReservedEvents" type="submit" action="listEvent" value="View Created Events"></th>						
			</tr>		
 </table>
 </form>

 
</body>
</html>