package CruiseActivityManagement.model;

import CruiseActivityManagement.data.UserDAO;

public class User implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;
	private String username; // PK in our database
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private int roomNumber;
	private int deckNumber;
	private String membershipType;
	private String role;
	private Event event;
	
	public void setUser(String username, String password, String firstName, String lastName, String phone, String email, int roomNumber, int deckNumber, String membershipType, String role) {
		this.setUsername(username);
		this.setPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPhone(phone);
		this.setEmail(email);
		this.setRoomNumber(roomNumber);
		this.setDeckNumber(deckNumber);
		this.setMembershipType(membershipType);
		this.setRole(role);		
	}
	
	private String validateUsernameLogin(String username) {
		String result = "";		
		if (username.isEmpty()) {
			result =  "Username cannot be empty";
		} else {		
			char ch = username.charAt(0);
			if (!Character.isAlphabetic(ch)) {
				result = "Username must start with a letter";
			} else if (!stringSize(username, 4, 20)) {
				result = "Username length must be >= 4 and <= 20 characters.";
			} 
		}
		return result;
	}
	
	private String validatePassword(String password) {
        String result = "";       
        if (!password.isEmpty()) {
            boolean hasUppercase = !password.equals(password.toLowerCase());
            boolean hasLowercase = !password.equals(password.toUpperCase());
            boolean hasSpecial   = !password.matches("[A-Za-z0-9 ]*");
            boolean hasNumber = password.matches(".*\\d.*");
            if (!stringSize(password, 7, 30) ) {
                result = "Password length must be >=7 and <=30";
            } else if (!hasUppercase){
                result = "Password must contain an upper case letter";
            } else if (!hasLowercase){
                result = "Password must contain a lower case letter";
            } else if (!hasNumber){
                result = "Password must contain a number";
            } else if (!hasSpecial){
                result = "Password must contain a special character";
            }
        } else {
            result =  "Password cannot be empty";
        }
        return result;
    }
	
	private String validateName(String nameType, String name) {
		String result = "";
		if (!name.isEmpty()) {
			char ch = name.charAt(0);
			String firstAlphabet = Character.toString(ch);
			boolean isUpper = !firstAlphabet.equals(firstAlphabet.toLowerCase());
			
			if (!stringSize(name, 3, 29)){
				result = nameType+"name length must be >2 and <30";
			} else if (!Character.isAlphabetic(ch)) {
				result = nameType+"name must start with a letter";
			} else if (!isUpper) {
				result = nameType+"name must start with a capital letter";
			} else if (name.matches(".*\\d.*")) {
				result = nameType+"name cannot be a number";					
			} else if (!name.matches("[A-Za-z0-9 ]*")) {
				result = nameType+"name cannot contain special characters";					
			}
		} else {
			result = "";
		}
		return result;
	}
	
	private String validatePhone(String phone) {
		String result = "";
		if (!phone.isEmpty()) {
			if (!isValidNumber(phone)) {
				result = "Phone number must be numeric";
			} else if (phone.length() != 10) {
				result = "Phone number must have 10 digits";
			}
		} else {
			return result = "";
		}
		return result;
	}
	
	private String validateRoomNumber(int roomNumber) {
		String result = "";	
		int length = (int) (Math.log10(roomNumber) + 1);
		if (isValidNumber(Integer.toString(roomNumber))) {
			if (length != 3) {
				result = "Room number must have 3 digits";
			} else if (roomNumber <= 99 || roomNumber >= 200) {
				result = "Room number must be >=100 and <=199";
			}
		} else {
			result = "Phone number must be numeric";
		}
		
		return result;
	}	
	
	private String validateEmail(String email) {
		String result="",extension="";
		if (!email.contains("@"))
			result = "Email address needs to contain @";
		else
			if (!stringSize(email,7,45))
				result="Email address must be between 7 and 45 characters long";
			else {
				extension = email.substring(email.length()-4, email.length());
				if (!extension.equals(".org") && !extension.equals(".edu") && !extension.equals(".com") 
						&& !extension.equals(".net") && !extension.equals(".gov") && !extension.equals(".mil"))
					result = "Invalid domain name";				
			}
		return result;		
	}

	private String validateDeckNumber(int deckNumber) {
		String result = "";
		int length = (int) (Math.log10(deckNumber) + 1);
		if (!isValidNumber(Integer.toString(deckNumber))) {
			result = "Deck number must be numeric";
		} else if (length <= 0 || length >= 3) {
			result = "Deck number must be 1 or 2 digit(s)";
		} else if (deckNumber <= 0 || deckNumber >= 16) {
			result = "Deck number must be >=1 and <=15"; 
		}
		return result;
	}
	
	public void validateLogin(User user, UserErrors userErrors) {
		userErrors.setUsernameError(validateUsernameLogin(user.getUsername()));
		userErrors.setPasswordError(validatePassword(user.getPassword()));	
		userErrors.setErrorMsg();
	}

	public void validateRegistration(User user, UserErrors userErrors) {
		userErrors.setUsernameError(validateUsername(user.getUsername()));
		userErrors.setPasswordError(validatePassword(user.getPassword()));
		userErrors.setFirstNameError(validateName("First", user.getFirstName()));
		userErrors.setLastNameError(validateName("Last", user.getLastName()));
		userErrors.setPhoneError(validatePhone(user.getPhone()));
		userErrors.setRoomNumberError(validateRoomNumber(user.getRoomNumber()));
		userErrors.setEmailError(validateEmail(user.getEmail()));
		userErrors.setDeckNumberError(validateDeckNumber(user.getDeckNumber()));
		userErrors.setErrorMsg();
	}
	
	private String validateUsername(String username) {
		String result = "";
		char ch = username.charAt(0);
		
		if (!username.isEmpty()) {
			if (!UserDAO.uniqueUsername(username)) {
				result = "Username already in database";
			} else if (!Character.isAlphabetic(ch)) {				
					result = "Username must start with a letter";
				} else if (!stringSize(username, 4, 20)) {
					result = "Username length must be >= 4 and <= 20 characters.";
				} else if(stringContainsSpecialChar(username)) {
					result = "Username cannot contain special characters";
				}			
		} else { 
			result =  "Username cannot be empty";
		}
		return result;
	}
		

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getDeckNumber() {
		return deckNumber;
	}

	public void setDeckNumber(int deckNumber) {
		this.deckNumber = deckNumber;
	}

	public String getMembershipType() {
		return membershipType;
	}
	
	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	//
	// Internal Methods here
	//
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	
	private boolean isValidNumber(String num) {
		for (char c : num.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
		return true;
	}
	
	private boolean stringContainsSpecialChar(String string) {
		String specialCharacters=" !#$%&'()*+,-./:;<=>?@[]^_`{|}";	
		boolean isSpecial = false;
		for (int i = 0; i < string.length(); i++) {		    
		    if (specialCharacters.contains(Character.toString(string.charAt(i)))) {
		    	isSpecial=  true;
		    } 
		}
		return isSpecial;
	}

		
}
