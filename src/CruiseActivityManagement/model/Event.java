package CruiseActivityManagement.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import CruiseActivityManagement.data.EventDAO;
import CruiseActivityManagement.data.UserDAO;

public class Event implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id; // PK in 'event' table,
	private String event_name;
	private String capacity;
	private String location;
	private int duration;
	private String type;
	private String date;
	private String startTime;
	private String endTime;
	private String eventCoordinator; // FK from 'user'
	private String estAttendance;

	public void setEvent(int id, String event_name, String capacity, String location, int duration, String type,
			String date, String startTime, String endTime, String eventCoordinator, String estAttendance) {
		setId(id);
		setEvent_name(event_name);
		setCapacity(capacity);
		setLocation(location);
		setDuration(duration);
		setType(type);
		setDate(date);
		setStartTime(startTime);
		setEndTime(endTime);
		setEventCoordinator(eventCoordinator);
		setEstAttendance(estAttendance);
	}

	public void validateReservation(Event event, User user, EventErrors eventErrors) {
		eventErrors.setTypeError(
				validateMaxReservationLimit(user.getUsername(), event.getId(), event.getDate(), event.getType()));
		eventErrors.setCapacityError(validateEventCapacity(user.getUsername(), event.getCapacity(), event.getId()));
		eventErrors.setErrorMsg();
	}

	public void validateDate(EventErrors eventErrors, String date, String time) {
		eventErrors.setDateError(validatePastDate(date));
		eventErrors.setStartTimeError(validatePastTime(time));
		eventErrors.setErrorMsg();
	}

	public void validateUpdateEvent(Event event, EventErrors eventErrors) {
		eventErrors.setEstAttendanceError(validateEstAttendees(event.getEstAttendance()));
		eventErrors.setEventCoordinatorError(
				validateAvailableCoordinator(event.getEventCoordinator(), event.getStartTime(), event.getEndTime()));
		eventErrors.setDurationError(validateExceedingDuration(event.getEndTime()));
		eventErrors.setErrorMsg();
	}

	private String validateExceedingDuration(String endTime) {
		String result = "";
		String startTime = endTime.substring(8);
		String closeTime = "22:00";
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date d1 = null, d2 = null;
		try {
			d1 = sdf.parse(startTime);
			d2 = sdf.parse(closeTime);
		} catch (ParseException e) {
		}
		long difference = d2.getTime() - d1.getTime();
		if (difference < 0) {
			result = "Duration cannot exceed close time";
		} else {
			result = "";
		}
		return result;
	}

	private String validateAvailableCoordinator(String eventCoordinator, String startTime, String endTime) {
		String result = "";
		boolean isAvailable = EventDAO.availableCoordinator(startTime, endTime, eventCoordinator);
		if (!isAvailable) {
			result = eventCoordinator + " is not available at that time";
		}
		return result;
	}

	private String validatePastTime(String time) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy HH:mm");
		String currentDate = sdf.format(new Date());

		try {
			if (sdf.parse(currentDate).after(sdf.parse(time))) {
				result = "Time must not be in past";
			} else {
				result = "";
			}
		} catch (ParseException e) {
		}

		return result;
	}

	private String validatePastDate(String selectedDate) {
		String result = "";
		SimpleDateFormat sdf1 = new SimpleDateFormat("M/d/yy");
		String currentDate = sdf1.format(new Date());
		try {
			Date selectedDateUpdated = sdf1.parse(selectedDate);
			selectedDate = sdf1.format(selectedDateUpdated);
			if (sdf1.parse(selectedDate).before(sdf1.parse(currentDate))) {
				result = "Date must not be in past";
			} else {
				result = "";
			}
		} catch (ParseException e) {
		}
		return result;
	}

	private String validateMaxReservationLimit(String username, int eventId, String date, String selectedEventType) {
		String result = "";
		int athleticCount = 0;
		int showCount = 0;
		ArrayList<Event> events = EventDAO.listReservedEventsByUsername(username, date);
		for (Event event : events) {
			if (event.getType().equalsIgnoreCase("ATHLETIC")) {
				athleticCount++;
			} else {
				showCount++;
			}
		}
		// Add current selected event
		if (selectedEventType.equalsIgnoreCase("ATHLETIC")) {
			athleticCount++;
		} else {
			showCount++;
		}
		// validation check
		if (athleticCount >= 3) {
			result = "You cannot reserve more than 2 athletic events per day";
		} else if (showCount >= 2) {
			result = "You cannot reserve more than 1 show event per day";
		}
		return result;
	}

	private String validateEventCapacity(String username, String capacity, int event_id) {
		String result = "";
		ArrayList<User> participants = UserDAO.listParticipantsInEvent(event_id);
		// Validation check
		if (participants.size() + 1 > Integer.parseInt(capacity)) {
			result = "Event capacity exceeded";
		}
		return result;
	}

	private String validateEstAttendees(String estAttendees) {
		String result = "";
		if (!isValidNumber(estAttendees)) {
			result = "Estimated attendees must be a number";
		} else if (Integer.parseInt(estAttendees) <= 0) {
			result = "Estimated Attendance must be greater than 0";
		} else if (Integer.parseInt(estAttendees) > 100) {
			result = "Estimated Attendance must be <=100";
		}
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEventCoordinator() {
		return eventCoordinator;
	}

	public void setEventCoordinator(String eventCoordinator) {
		this.eventCoordinator = eventCoordinator;
	}

	public String getEstAttendance() {
		return estAttendance;
	}

	public void setEstAttendance(String estAttendance) {
		this.estAttendance = estAttendance;
	}

	private boolean isValidNumber(String num) {
		for (char c : num.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

}
