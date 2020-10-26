<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Modify '${event.event_name}'</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
</head>
<body>
	<div class="header_resize">
		<h1>Modify Event</h1>
	</div>
	<br />
	<label class="errorPane"><c:out value='${error.errorMsg}' /></label>
	<form action="<c:url value='/EventController?action=updateEvent' />"
		method="post">
		<input type="hidden" name="ogId" value="<c:out value="${event.id}" />">
		<table>
			<tr>
				<td>Event Name:</td>
				<td><select name="event_name"
					style="width: 153px; height: 27px;">
						<option selected="<c:out value='${event.event_name}'/>"
							value="<c:out value='${event.event_name}'/>"><c:out
								value='${event.event_name}' /></option>
						<option value="Bowling 1">Bowling 1</option>
						<option value="Bowling 2">Bowling 2</option>
						<option value="Movie 1">Movie 1</option>
						<option value="Movie 2">Movie 2</option>
						<option value="Extreme Zipline">Extreme Zipline</option>
						<option value="Skycourse Ropes">Skycourse Ropes</option>
						<option value="Ice Skating">Ice Skating</option>
						<option value="Go Karting">Go Karting</option>
						<option value="Broadway Show">Broadway Show</option>
						<option value="Planetarium">Planetarium</option>
				</select></td>
				<td><label class="errorPane"><c:out
							value='${error.event_nameError}' /></label></td>
			</tr>

			<tr>
				<td>Capacity:</td>
				<td><input type="number" name="capacity"
					value="<c:out value="${event.capacity}"/>"></input></td>
				<td><label class="errorPane"><c:out
							value='${error.capacityError}' /></label></td>
			</tr>

			<tr>
				<td>Location:</td>
				<td><input name="location"
					value="<c:out value="${event.location}"/>"></input></td>
				<td><label class="errorPane"><c:out
							value='${error.locationError}' /></label></td>
			</tr>

			<tr>
				<td>Duration:</td>
				<td><select name="duration" style="width: 153px; height: 27px;">
						<option selected="<c:out value='${event.duration}'/>"
							value="<c:out value='${event.duration}'/>"><c:out
								value='${event.duration}' /></option>
						<option value="30">30</option>
						<option value="60">60</option>
						<option value="120">120</option>
				</select></td>
				<td><label class="errorPane"><c:out
							value='${error.durationError}' /></label></td>
			</tr>

			<tr>
				<td>Type:</td>
				<td><select name="type" style="width: 153px; height: 27px;">
						<option selected="<c:out value='${event.type}'/>"
							value="<c:out value='${event.type}'/>"><c:out
								value='${event.type}' /></option>
						<option value="Athletic">Athletic</option>
						<option value="Show">Show</option>
				</select>
				<td><label class="errorPane"><c:out
							value='${error.typeError}' /></label></td>
			</tr>


			<tr>
				<td>Date:</td>
				<td><input type="text" id="datepicker" name="datepicker"
					value="<c:out value="${event.date}"/>"> <label
					class="errorPane"><c:out value='${error.dateError}' /></label></td>
				

			</tr>

			<tr>
				<td>Start Time:</td>
				<td><input type="text" id="timepicker" name="timepickerFrom">
					<label class="errorPane"><c:out
							value='${error.startTimeError}' /></label></td>
				
			</tr>

			<tr>
				<td>End Time:</td>
				<td><input type="text" id="timepicker" name="timepickerTo">
					<label class="errorPane"><c:out
							value='${error.startTimeError}' /></label></td>
				
			</tr>

			<tr>
				<td>Event Coordinator:</td>
				<td><select name="event_coordinator"
					style="width: 153px; height: 27px;">
					<option selected="<c:out value='${event.eventCoordinator.toLowerCase()}'/>"
							value="<c:out value='${event.eventCoordinator.toLowerCase()}'/>"><c:out
								value='${event.eventCoordinator.toLowerCase()}' /></option>
						<c:forEach items="${coordinators}" var="item">
							<option value="<c:out value="${item.username}" />">${item.username}
							</option>
						</c:forEach>
				</select>
				<td><label class="errorPane"><c:out
							value='${error.eventCoordinatorError}' /></label></td>
			</tr>

			<tr>
				<td>Estimate Attendance:</td>
				<td><input name="est_attendance" type="number"
					value="<c:out value="${event.estAttendance}"/>"></input></td>
				<td><label class="errorPane"><c:out
							value='${error.estAttendanceError}' /></label></td>
			</tr>

			<tr>
				<td><input type="submit" value="Save"
					onclick="if (confirm('Are you sure you want to update?')) form.action='<c:url value='/EventController?action=updateEvent'/>'; else return false;" />
				</td>
				<td></td>
			</tr>

		</table>
	</form>
	<a href="<c:url value='/EventController?action=listCreatedEvents' />"> <input
		type="submit" value="Back">
	</a>
	<script>
		flatpickr("#datepicker", {
			enableTime : false,
			dateFormat : "m/j/y",
			altInput : true,
			altFormat : "Y-m-d",
		});
		
		flatpickr("#timepicker", {
			enableTime : true,
			noCalendar : true,
			dateFormat : "H:i",
			defaultHour : new Date().getHours(),
			defaultMinute : new Date().getMinutes(),
			altInput : true,
			altFormat : "h:i K",
		});
</script>

</body>
</html>