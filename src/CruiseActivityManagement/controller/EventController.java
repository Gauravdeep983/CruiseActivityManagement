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
import CruiseActivityManagement.model.EventErrors;
import CruiseActivityManagement.model.User;

@WebServlet("/EventController")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action"), url = "";
		HttpSession session = request.getSession();

		// Search for available events (Passenger)
		if (action.equalsIgnoreCase("searchEvents")) {
			String date = request.getParameter("datepicker");
			String time = request.getParameter("timepicker");
			String eventType = request.getParameter("event_type");
			Event event = new Event();
			EventErrors err = new EventErrors();
			// String format for database
			time = date.concat(" ").concat(time);
			date = date.concat(" 0:00");
			// Validate datetime
			event.validateDate(err, date, time);
			// Fetch all events based on filter
			ArrayList<Event> EventsInDB = EventDAO.listAllEvents(date, time, eventType);
			if (err.getErrorMsg().equals("")) {
				if (!EventsInDB.isEmpty()) {
					session.removeAttribute("error");
					session.setAttribute("RESULT", EventsInDB);
					url = "/searchEvents.jsp";
				} else {
					session.removeAttribute("RESULT");
					err.setErrorMsg("No events found.");
					session.setAttribute("error", err);
					url = "/searchEvents.jsp";
				}
			} else {// Show errors
				session.setAttribute("error", err);
				url = "/searchEvents.jsp";

			}

			// View specified event (Manger function)
		} else if (action.equalsIgnoreCase("listSpecificEvent")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Event> EventInDB = new ArrayList<Event>();
			EventInDB = EventDAO.listSpecificEvent(id);
			session.removeAttribute("EVENTS");
			session.setAttribute("EVENTS", EventInDB);
			url = "/viewSpecificEventManager.jsp";

			// Search reserved events (Passenger function)
		} else if (action.equalsIgnoreCase("searchReservedEvents")) {
			session.removeAttribute("errorMsgs");
			session.removeAttribute("EVENTS");
			User user = (User) session.getAttribute("user");
			String date = request.getParameter("datepicker");
			String time = request.getParameter("timepicker");
			EventErrors err = new EventErrors();
			// String format for database
			time = date.concat(" ").concat(time);
			date = date.concat(" 0:00");
			// Fetch all events based on filter
			ArrayList<Event> EventsInDB = EventDAO.listReservedEventsByUsername(user.getUsername(), date, time);

			if (!EventsInDB.isEmpty()) {
				session.removeAttribute("error");
				session.setAttribute("EVENTS", EventsInDB);
				url = "/viewReservedEvents.jsp";
			} else {
				session.removeAttribute("RESULT");
				session.setAttribute("error", err);
				url = "/viewReservedEvents.jsp";
			}

		} else if (action.equalsIgnoreCase("searchEventsPassenger")) {
			session.removeAttribute("RESULT");
			session.removeAttribute("error");
			session.removeAttribute("errorMsgs");
			session.removeAttribute("EVENTS");
			url = "/searchEvents.jsp";

		} else if (action.equalsIgnoreCase("searchEventsEC")) {
			session.removeAttribute("RESULT");
			session.removeAttribute("error");
			session.removeAttribute("errorMsgs");
			session.removeAttribute("EVENTS");
			url = "/viewEventSummaryEC.jsp";

			// EC Search assigned events
		} else if (action.equalsIgnoreCase("searchAssignedEvents")) {
			User user = (User) session.getAttribute("user");
			String date = request.getParameter("datepicker");
			String time = request.getParameter("timepicker");
			EventErrors err = new EventErrors();
			// String format for database
			time = date.concat(" ").concat(time);
			date = date.concat(" 0:00");
			// Fetch all events based on filter
			ArrayList<Event> EventsInDB = EventDAO.listAssignedEvents(date, time, user.getUsername());

			if (!EventsInDB.isEmpty()) {
				session.removeAttribute("error");
				session.setAttribute("EVENTS", EventsInDB);
				url = "/viewEventSummaryEC.jsp";
			} else {
				session.removeAttribute("RESULT");
				err.setErrorMsg("No events found.");
				session.setAttribute("error", err);
				url = "/viewEventSummaryEC.jsp";
			}
		} else if (action.equalsIgnoreCase("userReservedEvents")) {
			url = "/viewReservedEvents.jsp";

			// View specific event details (for booking) (Passenger function)
		} else if (action.equalsIgnoreCase("reserveEventDetails")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Event> EventInDB = new ArrayList<Event>();
			EventInDB = EventDAO.listSpecificEvent(id);
			session.removeAttribute("EVENT");
			session.setAttribute("EVENT", EventInDB.get(0));
			url = "/viewEventPassenger.jsp";

			// Specific event details assigned to EC
		} else if (action.equalsIgnoreCase("eventDetails")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Event> EventInDB = new ArrayList<Event>();
			EventInDB = EventDAO.listSpecificEvent(id);
			session.removeAttribute("EVENT");
			session.setAttribute("EVENT", EventInDB.get(0));
			// Redirect to event details view for EC
			url = "/EventDetails.jsp";

			// Reserve event function (Passenger)
		} else if (action.equalsIgnoreCase("reserveEvent")) {
			Event event = (Event) session.getAttribute("EVENT");
			User user = (User) session.getAttribute("user");
			EventErrors eventErrors = new EventErrors();
			try {
				// Validation
				event.validateReservation(event, user, eventErrors);
				// Reserve
				if (eventErrors.getErrorMsg().equals("")) {
					int inserted = EventDAO.reserveEvent(event, user);
					if (inserted == 1) {
						// Show success msg
						session.setAttribute("msg", "Event successfully reserved.");
						url = "/passengerHome.jsp";
					} else {
						throw new Exception("Failed to save in database!");
					}
				} else {
					// Show errors
					session.setAttribute("errorMsgs", eventErrors);
					url = "/viewEventPassenger.jsp";
				}
			} catch (Exception ex) {
				eventErrors.setErrorMsg(ex.getMessage());
				url = "/viewEventPassenger.jsp";
			}

			// Redirects to search event (Manager function)
		} else if (action.equals("listCreatedEvents")) {
			session.removeAttribute("errorMsgs");
			session.removeAttribute("RESULT");
			session.removeAttribute("error");
			session.removeAttribute("EVENTS");
			url = "/listCreatedEvents.jsp";

			// Search created events (Manager function)
		} else if (action.equalsIgnoreCase("searchCreatedEvents")) {
			session.removeAttribute("errorMsgs");
			session.removeAttribute("EVENTS");
			String date = request.getParameter("datepicker");
			String time = request.getParameter("timepicker");
			EventErrors err = new EventErrors();
			// String format for database
			time = date.concat(" ").concat(time);
			date = date.concat(" 0:00");
			// Fetch all events based on filter
			ArrayList<Event> EventsInDB = EventDAO.listAllEvents(date, time);

			if (!EventsInDB.isEmpty()) {
				session.removeAttribute("error");
				session.setAttribute("EVENTS", EventsInDB);
				url = "/listCreatedEvents.jsp";
			} else {
				session.removeAttribute("RESULT");
				session.setAttribute("error", err);
				url = "/listCreatedEvents.jsp";
			}

			// Redirection to Modify event page (Manager function)
		} else if (action.equalsIgnoreCase("modifySpecificEvent")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Event> EventInDB = new ArrayList<Event>();
			EventInDB = EventDAO.listSpecificEvent(id);
			// get all event coordinators
			ArrayList<User> coordinators = UserDAO.listEventCoordinators();
			session.setAttribute("coordinators", coordinators);
			session.removeAttribute("event");
			session.setAttribute("event", EventInDB.get(0));
			url = "/editEvent.jsp";

			// Modify event (Manager function)
		} else if (action.equals("updateEvent")) {
			Event event = new Event();
			EventErrors eventErrors = new EventErrors();
			String date = request.getParameter("datepicker");
			String timeFrom = request.getParameter("timepickerFrom");
			String timeTo = request.getParameter("timepickerTo");
			String ogId = request.getParameter("ogId");
			String eventCoordinator = request.getParameter("event_coordinator");
			// Set user
			// String format for database
			timeFrom = date.concat(" ").concat(timeFrom);
			timeTo = date.concat(" ").concat(timeTo);
			date = date.concat(" 0:00");
			// Validate datetime
			event.setEvent(Integer.parseInt(ogId), request.getParameter("event_name"), request.getParameter("capacity"),
					request.getParameter("location"), Integer.parseInt(request.getParameter("duration")),
					request.getParameter("type"), date, timeFrom, timeTo, eventCoordinator,
					request.getParameter("est_attendance"));
			// Validation
			event.validateUpdateEvent(event, eventErrors);
			// If no error persists
			if (eventErrors.getErrorMsg().equals("")) {
				// Update event
				EventDAO.updateEvent(event, ogId);
				session.removeAttribute("event");
				// Show success msg
				session.setAttribute("msg", "Event updated.");
				url = "/managerHome.jsp";

			} else {
				// Show errors
				session.setAttribute("error", eventErrors);
				url = "/editEvent.jsp";
			}
		}

		// redirects to url specified
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
