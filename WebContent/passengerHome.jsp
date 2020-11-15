<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Passenger Homepage</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="myStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="content">
		<table>
			<tr>
				<td><h1>Hello <c:out value='${user.firstName}' /></h1></td>
				<td>(Passenger Homepage)</td>
				<td><a href="<c:url value='/UserController?action=logout' />">
						<input type="submit" value="Logout" id="logout">
				</a></td>
			</tr>
			<tr>
				<td><label class="errorPane"><c:out value='${msg}' /></label></td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>

							<td><a
								href="<c:url value='/EventController?action=searchEventsPassenger' />">
									<input type="submit" value="View Available Events" id="available_events">
							</a></td>
							<td><a
								href="<c:url value='/EventController?action=userReservedEvents' />">
									<input type="submit" value="View Reserved Events" id="reserved_events">
							</a></td>

							<td><a href="<c:url value='/viewProfile.jsp' />"> <input
									type="submit" value="View Profile" id="profile"></a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>