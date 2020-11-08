package CruiseActivityManagement.model;

public class UserErrors {
	private String errorMsg = "";
	private String usernameError = "";
	private String passwordError = "";
	private String firstNameError = "";
	private String lastNameError = "";
	private String phoneError = "";
	private String emailError = "";
	private String roomNumberError = "";
	private String deckNumberError = "";

	// Default constructor
	public UserErrors() {
		this.errorMsg = "";
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg() {
		if (!usernameError.equals("") || !passwordError.equals("") || !firstNameError.equals("")
				|| !lastNameError.equals("") || !phoneError.equals("") || !emailError.equals("")
				|| !roomNumberError.equals("") || !deckNumberError.equals("")) {
			errorMsg = "Please correct the following errors";
		}
	}

	public String getUsernameError() {
		return usernameError;
	}

	public void setUsernameError(String usernameError) {
		this.usernameError = usernameError;
	}

	public String getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}

	public String getFirstNameError() {
		return firstNameError;
	}

	public void setFirstNameError(String firstNameError) {
		this.firstNameError = firstNameError;
	}

	public String getLastNameError() {
		return lastNameError;
	}

	public void setLastNameError(String lastNameError) {
		this.lastNameError = lastNameError;
	}

	public String getPhoneError() {
		return phoneError;
	}

	public void setPhoneError(String phoneError) {
		this.phoneError = phoneError;
	}

	public String getEmailError() {
		return emailError;
	}

	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}

	public String getRoomNumberError() {
		return roomNumberError;
	}

	public void setRoomNumberError(String roomNumberError) {
		this.roomNumberError = roomNumberError;
	}

	public String getDeckNumberError() {
		return deckNumberError;
	}

	public void setDeckNumberError(String deckNumberError) {
		this.deckNumberError = deckNumberError;
	}

}
