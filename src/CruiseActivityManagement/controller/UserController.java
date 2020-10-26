package CruiseActivityManagement.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CruiseActivityManagement.data.EventDAO;
import CruiseActivityManagement.data.UserDAO;
import CruiseActivityManagement.model.Event;
import CruiseActivityManagement.model.User;
import CruiseActivityManagement.model.UserErrors;


@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get action from hidden input field
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();

		if (action.equals("logout")) {
			session.invalidate();
			url = "/index.jsp";

		// Check if action == login
		} else if (action.equals("login")) {
			User user = new User();
			UserErrors userErrors = new UserErrors();
			// Get and Set username, password
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			user.setUsername(username);
			user.setPassword(password);			
			// Validate username and password
			user.validateLogin(user, userErrors);
			// Check if there are no errors in error array
			if (userErrors.getErrorMsg().equals("")) {
				// if no errors found then login
				User user1 = new User();
				user1 = UserDAO.login(username, password);
				try {
					if (!user1.getUsername().equals(null)) {
						session.removeAttribute("user");
						session.setAttribute("user", user1);
						// redirect to homepage using role
						String role = user1.getRole();
						switch(role) {
						case "PASSENGER":
							url = "/passengerHome.jsp";
							break;
						case "EVENT_MANAGER":
							url = "/managerHome.jsp";
							break;
						case "EVENT_COORDINATOR":
							url = "/eventCoordinatorHome.jsp";
							break;
						}
					}
				} catch (NullPointerException ex) {
					// User doesnt exist
					userErrors.setUsernameError("User doesn't exist");
					session.setAttribute("errorMsgs", userErrors);
					url = "/index.jsp";					
				}
			} else {
				// Show errors
				session.setAttribute("errorMsgs", userErrors);
				url = "/index.jsp";
				session.removeAttribute("user");
			}
			
		
		//	Update profile
		} else if (action.equals("updateProfile")) {
			User user = new User();
			UserErrors userErrors = new UserErrors();
			String ogUsername = request.getParameter("ogUsername");
			// Set user
            user.setUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("firstname"), 
            		request.getParameter("lastname"), request.getParameter("phone"), request.getParameter("email"), 
            		request.getParameter("room_number"), request.getParameter("deck_number"), 
            		request.getParameter("membership_type"), "PASSENGER");
            // Validation
            user.validateUpdateProfile(user, userErrors);
            // If no error persists
            if (userErrors.getErrorMsg().equals("")) {
            	//Update user            	
            	User userUpdated = UserDAO.updateUser(user, ogUsername);
				session.removeAttribute("user");
				session.removeAttribute("error");
				session.setAttribute("user", userUpdated);
				// Show success msg
				session.setAttribute("msg", "Profile updated.");
				url = "/passengerHome.jsp";
            
            } else {
				// Show errors
				session.setAttribute("error", userErrors);
				url = "/editProfile.jsp";
            }
            
		// Registration form
		} else if (action.equals("register")) {
			session.removeAttribute("error");
            User user = new User();
            UserErrors userErrors = new UserErrors();
            // Set user
            user.setUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("firstname"), 
            		request.getParameter("lastname"), request.getParameter("phone"), request.getParameter("email"), 
            		request.getParameter("room_number"), request.getParameter("deck_number"), 
            		request.getParameter("membership_type"), "PASSENGER");
            // Validation 
            user.validateRegistration(user, userErrors);
           
            session.setAttribute("error", userErrors);
            if (userErrors.getErrorMsg().equals("")) {
                UserDAO.registerUser(user);
                session.setAttribute("message", "Registration successfull!");
                url = "/index.jsp";
            } else {
                // Show errors
                url = "/register.jsp";
            } 
        // Homepage for each role
		} else if (action.equalsIgnoreCase("homepage")) {
			User user = (User)session.getAttribute("user");
			session.removeAttribute("msg");		
		  	session.removeAttribute("EVENTS");
			session.removeAttribute("RESULT");
			session.removeAttribute("error");
			session.removeAttribute("errorMsgs");	
			
			switch(user.getRole()) {
			case "PASSENGER":
				url = "/passengerHome.jsp";
				break;
			case "EVENT_MANAGER":
				url = "/managerHome.jsp";
				break;
			case "EVENT_COORDINATOR":
				url = "/eventCoordinatorHome.jsp";
				break;
			}
		}

		// redirects to url specified
		getServletContext().getRequestDispatcher(url).forward(request, response);	
		
	}
	
	

}
