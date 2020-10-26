<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<title>Edit Profile</title>
</head>
<body>
	<div class="header_resize">
		<h1>Modify Profile</h1>
	</div>
	<br />
	<label class="errorPane"><c:out value='${error.errorMsg}' /></label>
	<form action="<c:url value='/UserController?action=updateProfile' />"
		method="post">
		<input type="hidden" name="ogUsername"
			value="<c:out value="${user.username}" />">
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="username"
					value="<c:out value="${user.username}" />"></input></td>
				<td><label class="errorPane"><c:out
							value='${error.usernameError}' /></label></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><input type="password" name="password"
					value="<c:out value="${user.password}"/>"></input></td>
				<td><label class="errorPane"><c:out
							value='${error.passwordError}' /></label></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastname"
					value="<c:out value="${user.lastName}"/>"></input></td>
				<td><label class="errorPane"><c:out
							value='${error.lastNameError}' /></label></td>
			</tr>

			<tr>
				<td>First Name:</td>
				<td><input name="firstname"
					value="<c:out value="${user.firstName}"/>"></input></td>
				<td><label class="errorPane"><c:out
							value='${error.firstNameError}' /></label></td>
			</tr>

			<tr>
				<td>Phone:</td>
				<td><input name="phone" type="number"
					value="<c:out value="${user.phone}"/>" /></td>

				<td><label class="errorPane"><c:out
							value='${error.phoneError}' /></label></td>
			</tr>


			<tr>
				<td>Email:</td>
				<td><input id="email" name="email"
					value="<c:out value="${user.email}"/>" /></td>
				<td><label class="errorPane"><c:out
							value='${error.emailError}' /></label></td>

			</tr>

			<tr>
				<td>Room Number:</td>
				<td><input name="room_number" type="number"
					value="<c:out value="${user.roomNumber}"/>"></input></td>
				<td><label class="errorPane"><c:out
							value='${error.roomNumberError}' /></label></td>
			</tr>

			<tr>
				<td>Deck Number:</td>
				<td><input name="deck_number" type="number"
					value="<c:out value="${user.deckNumber}"/>"></input></td>
				<td><label class="errorPane"><c:out
							value='${error.deckNumberError}' /></label></td>
			</tr>

			<tr>
				<td>Membership Type:</td>
				<td><select name="membership_type"
					style="width: 153px; height: 27px;">
						<option selected="<c:out value='${user.membershipType}'/>"
							value="<c:out value='${user.membershipType}'/>"><c:out
								value='${user.membershipType}' /></option>
						<option value="NONE">NONE</option>
						<option value="STANDARD">STANDARD</option>
						<option value="SUPERIOR">SUPERIOR</option>
						<option value="PREMIUM">PREMIUM</option>
				</select></td>
				<td><label class="errorPane"><c:out
							value='${error.membershipTypeError}' /></label></td>
			</tr>

			<tr>
				<td><input type="submit" value="Save"
					onclick="if (confirm('Are you sure you want to update?')) form.action='<c:url value='/UserController?action=updateProfile'/>'; else return false;" />
				</td>
				<td><a href="<c:url value='/UserController?action=homepage' />">
						<input type="submit" value="Back">
				</a></td>
			</tr>

		</table>
	</form>
</body>
</html>