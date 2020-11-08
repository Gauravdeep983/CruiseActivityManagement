<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header_resize">
		<div class="logo">
			<h1>Register User</h1>
		</div>
	</div>
	<table>
		<tr>
			<td><label class="errorPane"><c:out
						value='${error.errorMsg}' /></label>
				<form name="RegisterForm"
					action="<c:url value='/UserController?action=register' />"
					method="post">
					<table>

						<tr>
							<td>Username:</td>
							<td><input name="username" /></td>
							<td><label class="errorPane"><c:out
										value='${error.usernameError}' /></label></td>
						</tr>

						<tr>
							<td>Password</td>
							<td><input type="password" name="password">
							<td><label class="errorPane"><c:out
										value='${error.passwordError}' /></label></td>
							</td>
						</tr>

						<tr>
							<td>First Name:</td>
							<td><input id="firstname" name="firstname">
							<td><label class="errorPane"><c:out
										value='${error.firstNameError}' /></label></td>
							</td>
						</tr>


						<tr>
							<td>Last Name:</td>
							<td><input id="lastname" name="lastname">
							<td><label class="errorPane"><c:out
										value='${error.lastNameError}' /></label></td>
							</td>
						</tr>


						<tr>
							<td>Phone:</td>
							<td><input id="phone" name="phone" type="text">
							<td><label class="errorPane"><c:out
										value='${error.phoneError}' /></label></td>
							</td>
						</tr>


						<tr>
							<td>Email:</td>
							<td><input id="email" name="email">
							<td><label class="errorPane"><c:out
										value='${error.emailError}' /></label></td>
							</td>
						</tr>

						<tr>
							<td>Room Number:</td>
							<td><input id="room_number" name="room_number" type="text">
							<td><label class="errorPane"><c:out
										value='${error.roomNumberError}' /></label></td>
							</td>
						</tr>

						<tr>
							<td>Deck Number:</td>
							<td><input id="deck_number" name="deck_number" type="text">
							<td><label class="errorPane"><c:out
										value='${error.deckNumberError}' /></label></td>
							</td>
						</tr>

						<tr>
							<td>Membership Type:</td>
							<td><select name="membership_type" id="membership_type"
								style="width: 153px; height: 27px;">
									<option value="NONE">None</option>
									<option value="STANDARD">Standard</option>
									<option value="SUPERIOR">Superior</option>
									<option value="PREMIUM">Premium</option>
							</select>
						</tr>


					</table>

					<input id="submit" name="Submit" type="submit" value="Submit">
					<a href="<c:url value='/' />">Back</a>
					<!-- <input name="reset" value="Reset" type="hidden">
	<input name="Reset" type="reset" value="Reset"> -->
				</form></td>
		</tr>
	</table>
</body>
</html>