package CruiseActivityManagement.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import CruiseActivityManagement.data.EventDAO;
import CruiseActivityManagement.data.UserDAO;

public class Event implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;
	private int id; // PK in 'event' table, 
	private String event_name; // PK in 'all_events' table, FK in 'event' table
	private int capacity;
	private String location;
	private int duration;
	private String type;
	private String date;
	private String startTime;
	private String endTime;
	private String eventCoordinator; // FK from 'user'
	private int estAttendance;
	
	public void setEvent (int id, String event_name, int capacity, String location, int duration, String type, String date,  String startTime, String endTime, String eventCoordinator, int estAttendance) {
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
		eventErrors.setTypeError(validateMaxReservationLimit(user.getUsername(), event.getId(), event.getDate(), event.getType()));
		eventErrors.setCapacityError(validateEventCapacity(user.getUsername(), event.getCapacity(), event.getId()));	
		eventErrors.setErrorMsg();
	}
	
	public void validateDate(EventErrors eventErrors, String date, String time) {
		eventErrors.setDateError(validatePastDate(date));
		eventErrors.setStartTimeError(validatePastTime(time));
		eventErrors.setErrorMsg();
	}
	
	private String validatePastTime(String time) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy hh:mm");
		String currentDate = sdf.format(new Date());

		try {
			if (sdf.parse(time).before(sdf.parse(currentDate))) {
				result = "Time must not be in past";
			} else {
				result = "";
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return result;
	}

	private String validatePastDate(String selectedDate) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy hh:mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("M/d/yy");
		String currentDate = sdf1.format(new Date());
		currentDate = currentDate.concat(" 0:00");
		try {
			if (sdf.parse(selectedDate).before(sdf.parse(currentDate))) {
				result = "Date must not be in past";
			} else {
				result = "";
			}
		} catch (ParseException e) {			
			e.printStackTrace();
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
			} else if (event.getType().equalsIgnoreCase("SHOW")) {
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
	
	private String validateEventCapacity(String username, int capacity, int event_id) {
		String result = "";
		ArrayList<User> participants = UserDAO.listParticipantsInEvent(event_id);
		// Validation check
		if (participants.size() + 1 > capacity) {
			result = "Event capacity exceeded";
		}		
		return result;
	}
	
	private String validateEstAttendees(int estAttendees) {
		String result = "";
		if (!isValidNumber(Integer.toString(estAttendees))) {
			result = "Attendance must be numeric";
		} else if (estAttendees <= 0) {
			result = "Estimated attendees must be greater than 0";
		} else if (estAttendees >= 101) {
			result = "Estimated attendees must be <=100";					
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
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

	public int getEstAttendance() {
		return estAttendance;
	}

	public void setEstAttendance(int estAttendance) {
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
