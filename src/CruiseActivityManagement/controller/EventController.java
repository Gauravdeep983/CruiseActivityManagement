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

@WebServlet("/EventController")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		
		if (action.equals("viewAllEvents")) {
			// TODO
			
		} else if (action.equals("listSpecificEvent")) {	    	  
	    	  int id = Integer.parseInt(request.getParameter("id"));
	    	  ArrayList<Event> EventInDB = new ArrayList<Event>();
	    	  EventInDB = EventDAO.listSpecificEvent(id);
	          session.removeAttribute("EVENTS");
	    	  session.setAttribute("EVENTS", EventInDB);	
	    	  url = "/viewSpecificEventManager.jsp";
	    	  
	    } else if (action.equals("userReservedEvents")) {
	          session.removeAttribute("errorMsgs");
		      session.removeAttribute("EVENTS");
//	          Reserved events for passenger	        
	          ArrayList<User> UserInDB = new ArrayList<User>();
	          User user = (User)session.getAttribute("user");
	          String username = user.getUsername();
	          UserInDB = UserDAO.passengerReservedEvents(username);
	          session.setAttribute("EVENTS", UserInDB);  
	          url = "/viewReservedEvents.jsp";
	          
	    } else if (action.equals("eventDetails")) {
			  int id = Integer.parseInt(request.getParameter("id"));
			  ArrayList<Event> EventInDB = new ArrayList<Event>();
	          EventInDB = EventDAO.listSpecificEvent(id);
	          session.removeAttribute("EVENT");
	    	  session.setAttribute("EVENT", EventInDB);
	          url = "/EventDetails.jsp";
	    }
		
		// redirects to url specified
		getServletContext().getRequestDispatcher(url).forward(request, response);	
	}

}
