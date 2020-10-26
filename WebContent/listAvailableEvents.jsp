<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Available Events</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script>

</head>
<body>

 <form  method="post">         
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				
				<th class="myTable20">Event Name</th>
				<th class="myTable35">Date</th> 
				<th class="myTable35">Start Time</th> 
				<th class="myTable35">End Time</th> 
				<th class="myTable20">Event Coordinator</th>
				<th class="myTable30">Type</th> 
			</tr>

 		<c:forEach items="${EVENTS}" var="item" varStatus="status">
			<tr class="myTableRow">
				
			<td class="myTable20 "><c:out value="${item.event_name}" /></td>
			<td class="myTable35 "><c:out value="${item.date}" /></td>
			<td class="myTable20 "><c:out value="${item.startTime}" /></td>
			<td class="myTable30 "><c:out value="${item.endTime}" /></td>
			<td class="myTable30 "><c:out value="${item.eventCoordinator}" /></td>
			<td class="myTable30 "><c:out value="${item.type}" /></td>
            <td class="myTable30 "><td> <a href="<c:url value='/EventController?action=viewEvent&id=${item.id}' />">View</a></td>
			</tr>
		</c:forEach>
 </table>

 </form>
</body>
</html>