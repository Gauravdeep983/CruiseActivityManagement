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
      <div class="logo"><h1><a href="<c:url value='/' />">Create Events</a></h1></div>
  </div>
<table>
 <tr>
 <td>
 
 <form name="eventForm" action="<c:url value='/eventController' />" method="post">
 <table>
 
 <tr>
 <td> Event Name: </td>
 <td>
 
 <select id="eventName:" name="eventName:" style="width: 153px; height: 27px; ">
    <option value="Bowling1">Bowling 1</option>
    <option value="Bowling2">Bowling2</option>
    <option value="Movie 1">Movie 1</option>
    <option value="Movie 2">Movie 2</option>
    <option value="Extreme Zipline">Extreme Zipline</option>
    <option value="Skycourse Ropes">Skycourse Ropes</option>
    <option value="Ice Skating">Ice Skating</option>
    <option value="Go Karting">Go Karting</option>
    <option value="Movie 2">Broadway Show</option>
    <option value="Planetarium">Planetarium</option>
  </select>	 
 
 </tr>
  </td>
 
 <tr>
 <td> Date: </td>
 <td>
 <input name="date" > 
 </tr>
 </td>
 
 <tr>
 <td> Start Time: </td>
 <td>
 <input name="startTime" >  	
 </tr>
  </td>
 
 <tr>
 <td> End Time: </td>
 <td>
 <input name="endTime"> 	
 </tr>
  </td>
  
  <tr>
 <td> Duration: </td>
 <td>
 <input name="duration"> 	
 </tr>
  </td>
 
 <tr>
 <td> Capacity: </td>
 <td>
 <input name="capacity"> 	
 </tr>
  </td>
 
 <tr>
 <td> Location: </td>
 <td>
  	<input name="location"> 
 </tr>
  </td>
  
 <tr>
 <td> Event Type: </td>
 <td>
  <select id="location" name="location" style="width: 153px; height: 27px; ">
    <option value="Athletic">Athletic</option>
    <option value="Show">Show</option>   
    </select> 		
 </tr>
  </td>
  
 <tr>
 <td> Event Coordinator: </td>
 <td>
 <select id="location" name="location" style="width: 153px; height: 27px; ">
    <option value="Coordinator 1">Coordinator 1</option>
    <option value="Coordinator 2">Coordinator 2</option>
    <option value="Coordinator 3">Coordinator 3</option>
    <option value="Coordinator 4">Coordinator 4</option>
    <option value="Coordinator 5">Coordinator 5</option>
    <option value="Coordinator 6">Coordinator 6</option>
    <option value="Coordinator 7">Coordinator 7</option>
    <option value="Coordinator 8">Coordinator 8</option>
    <option value="Coordinator 9">Coordinator 9</option>
    <option value="Coordinator 10">Coordinator 10</option>
    </select>  	
 </tr>
  </td>
 
 <tr>
 <td> Estimated Attendees: </td>
 <td>
 <input name="estimatedAttendees"> 	
 </tr>
  </td>
 
 </table>
 
    <input name="save" value="save" type="hidden">
	<input name="update" type="submit" value="Save">
	
	<input name="update" value="update" type="hidden">
	<input name="doneButton" type="submit" value="Update">
	
	<input name="delete" value="delete" type="hidden">
    <input name="delete" type="submit" value="Delete">
    
    <input name="action" value="discard" type="hidden">
    <input name="discard" type="submit" value="Discard">
</form>
</td>
</tr>
</table>
</body>
</html>