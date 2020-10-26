<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header_resize">
		<div class="logo">
			<h1>
				<a href="<c:url value='/' />">Register User</a>
			</h1>
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
							<td><input id="phone" name="phone" type="number">
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
						<!--  <tr>
 <td> Deck Number: </td>
 <td>
  <select name="deck_number" style="width: 153px; height: 27px; " >
    <option value="1">Deck 1</option>
    <option value="2">Deck 2</option>
    <option value="3">Deck 3</option>   
    <option value="4">Deck 4</option>
    <option value="5">Deck 5</option>
    <option value="6">Deck 6</option>   
    <option value="7">Deck 7</option>
    <option value="8">Deck 8</option>
    <option value="9">Deck 9</option>   
    <option value="10">Deck 10</option>
    <option value="11">Deck 11</option>
    <option value="12">Deck 12</option>   
    <option value="13">Deck 13</option>
    <option value="14">Deck 14</option>
    <option value="15">Deck 15</option>   
    </select> 		
      	<td><label class="errorPane"><c:out value='${error.deckNumberError}'/></label></td>	
</td> </tr> -->

						<tr>
							<td>Membership Type:</td>
							<td><select name="membership_type"
								style="width: 153px; height: 27px;">
									<option value="NONE">None</option>
									<option value="STANDARD">Standard</option>
									<option value="SUPERIOR">Superior</option>
									<option value="PREMIUM">Premium</option>
							</select>
							<td><label class="errorPane"><c:out
										value='${error.membershipTypeError}' /></label></td>
							</td>
						</tr>


					</table>

					<input name="Submit" type="submit" value="Submit">

					<!-- <input name="reset" value="Reset" type="hidden">
	<input name="Reset" type="reset" value="Reset"> -->
				</form></td>
		</tr>
	</table>
</body>
</html>