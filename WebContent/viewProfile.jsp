<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Profile</title>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header_resize">
		<h1>View Profile</h1>
	</div>
	<table>
		<tr>
			<td>
				<table >
					<tr>
						<td>Username:</td>
						<td><c:out value="${user.username}" /></td>
					</tr>

					<tr>
						<td>Password:</td>
						<td><c:out value="${user.password}" /></td>
					</tr>

					<tr>
						<td>Last Name:</td>
						<td><c:out value="${user.lastName}" /></td>
					</tr>

					<tr>
						<td>First Name:</td>
						<td><c:out value="${user.firstName}" /></td>
					</tr>

					<tr>
						<td>Phone:</td>
						<td><c:out value="${user.phone}" /></td>
					</tr>

					<tr>
						<td>Email ID:</td>
						<td><c:out value="${user.email}" /></td>
					</tr>

					<tr>
						<td>Room Number:</td>
						<td><c:out value="${user.roomNumber}" /></td>
					</tr>

					<tr>
						<td>Deck Number:</td>
						<td><c:out value="${user.deckNumber}" /></td>
					</tr>

					<tr>
						<td>Membership:</td>
						<td><c:out value="${user.membershipType}" /></td>
					</tr>

					<tr>
						<td></td>
						<td><a href="<c:url value='/editProfile.jsp' />"> <input
								type="submit" value="Modify Profile" id="modify"></a></td>
					</tr>

					<tr>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<a href="<c:url value='/UserController?action=homepage' />"> <input
		type="submit" value="Back" id="back_profile"></a>
</body>
</html>