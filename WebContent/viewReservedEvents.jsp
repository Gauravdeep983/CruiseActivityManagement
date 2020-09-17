<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Event Summary</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<body>
    <div class="header_resize">
    
      <div class="menu_nav">
      </div>
  </div>


     <div class="mainbar"><div class="submb"></div></div>
      
 <form  method="post">         
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				
				<th class="myTable20">Event Name</th>
				<th class="myTable35">Event Date</th> 
				<th class="myTable20">Start Time</th>
				<th class="myTable20">End Time</th>
				<th class="myTable20">Details</th>
				

			</tr>

 		<c:forEach items="${EVENTS}" var="item" varStatus="status">
			<tr class="myTableRow">
				
			<td class="myTable20 "><c:out value="${item.event.event_name}" /></td>
			<td class="myTable35 "><c:out value="${item.event.date}" /></td>
			<td class="myTable20 "><c:out value="${item.event.startTime}" /></td>
			<td class="myTable20 "><c:out value="${item.event.endTime}" /></td>	
			<td> <a href="<c:url value='/EventController?action=eventDetails&id=${item.event.id}' />">
			Details</a>
			</td></tr>
		</c:forEach>
 </table>

 </form>
</body>
</html>
