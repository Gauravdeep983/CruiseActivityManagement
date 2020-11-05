	package model;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import CruiseActivityManagement.model.User;
import CruiseActivityManagement.model.UserErrors;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserTest {
	private User user;
	private UserErrors userErrors;

	@Before
	public void setUp() throws Exception {
		user = new User();
		userErrors = new UserErrors();
	}

	@Test
	@FileParameters("test/model/User_test_cases1.csv")
	public void registrationTest(int testcaseNo, String username, String password, String firstName, String lastName,
			String phone, String email, String roomNumber, String deckNumber, String membershipType, String role,
			String errorMsg, String usernameError, String passwordError, String firstNameError, String lastNameError,
			String phoneError, String emailError, String roomNumberError, String deckNumberError) { //20 args

		user.setUser(username, password, firstName, lastName, phone, email, roomNumber, deckNumber, membershipType,	role);

		user.validateRegistration(user, userErrors);

		assertTrue(errorMsg.equals(userErrors.getErrorMsg()));
		assertTrue(usernameError.equals(userErrors.getUsernameError()));
		assertTrue(passwordError.equals(userErrors.getPasswordError()));
		assertTrue(firstNameError.equals(userErrors.getFirstNameError()));
		assertTrue(lastNameError.equals(userErrors.getLastNameError()));
		assertTrue(phoneError.equals(userErrors.getPhoneError()));
		assertTrue(emailError.equals(userErrors.getEmailError()));
		assertTrue(roomNumberError.equals(userErrors.getRoomNumberError()));
		assertTrue(deckNumberError.equals(userErrors.getDeckNumberError()));
	}

	@Test
	@FileParameters("test/model/login_test.csv") 
	public void loginTest(int testcaseNo, String username, String password, String errorMsg, String usernameError,
			String passwordError) {
		user.setUser(username, password);
		user.validateLogin(user, userErrors);

		assertTrue(errorMsg.equals(userErrors.getErrorMsg()));
		assertTrue(usernameError.equals(userErrors.getUsernameError()));
		assertTrue(passwordError.equals(userErrors.getPasswordError()));
	}

	@Test
	@FileParameters("test/model/User_test_cases1.csv") 
	public void updateProfileTest(int testcaseNo, String username, String password, String firstName, String lastName,
			String phone, String email, String roomNumber, String deckNumber, String membershipType, String role,
			String errorMsg, String usernameError, String passwordError, String firstNameError, String lastNameError,
			String phoneError, String emailError, String roomNumberError, String deckNumberError) {
		
		user.setUser(username, password, firstName, lastName, phone, email, roomNumber, deckNumber, membershipType,	role);
		
		user.validateUpdateProfile(user, userErrors);

		assertTrue(errorMsg.equals(userErrors.getErrorMsg()));
		assertTrue(usernameError.equals(userErrors.getUsernameError()));
		assertTrue(passwordError.equals(userErrors.getPasswordError()));
		assertTrue(firstNameError.equals(userErrors.getFirstNameError()));
		assertTrue(lastNameError.equals(userErrors.getLastNameError()));
		assertTrue(phoneError.equals(userErrors.getPhoneError()));
		assertTrue(emailError.equals(userErrors.getEmailError()));
		assertTrue(roomNumberError.equals(userErrors.getRoomNumberError()));
		assertTrue(deckNumberError.equals(userErrors.getDeckNumberError()));
	}
}
