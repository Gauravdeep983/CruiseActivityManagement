<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Event Details</title>
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
				<th class="myTable35">Date</th> 
				<th class="myTable35">Start Time</th> 
				<th class="myTable35">End Time</th> 
				<th class="myTable20">Event Coordinator</th>
				<th class="myTable20">Est. Attendance</th>
				<th class="myTable20">Capacity</th>
				<th class="myTable20">Location</th>
				<th class="myTable30">Duration</th> 
				<th class="myTable30">Type</th> 
			</tr>

 		<c:forEach items="${EVENTS}" var="item" varStatus="status">
			<tr class="myTableRow">
				
			<td class="myTable20 "><c:out value="${item.event_name}" /></td>
			<td class="myTable35 "><c:out value="${item.date}" /></td>
			<td class="myTable35 "><c:out value="${item.startTime}" /></td>
			<td class="myTable35 "><c:out value="${item.endTime}" /></td>
			<td class="myTable20 "><c:out value="${item.eventCoordinator}" /></td>
			<td class="myTable35 "><c:out value="${item.estAttendance}" /></td>
			<td class="myTable35 "><c:out value="${item.capacity}" /></td>
			<td class="myTable30 "><c:out value="${item.location}" /></td>
			<td class="myTable35 "><c:out value="${item.duration}" /></td>
			<td class="myTable30 "><c:out value="${item.type}" /></td>
			</tr>
		</c:forEach>
 </table>

 </form>
</body>
</html>