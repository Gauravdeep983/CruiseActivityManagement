package CruiseActivityManagement.model;

public class Event implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;
	private int id; // PK in 'event' table, 
	private String event_name; // PK in 'all_events' table, FK in 'event' table
	private String type;
	private String location;
	private int capacity;	
	private int duration;
	private String date;
	private String startTime;
	private String endTime;
	private String eventCoordinator; // FK from 'user'
	private int estAttendance;
	
	public void setEvent (int id, String event_name, String type,String location, int capacity,int duration, String date,  String startTime, String endTime, String eventCoordinator, int estAttendance) {
		setId(id);
		setEvent_name(event_name);
		setType(type);
		setLocation(location);
		setCapacity(capacity);
		setDuration(duration);
		setStartTime(startTime);
		setEndTime(endTime);
		setEventCoordinator(eventCoordinator);
		setEstAttendance(estAttendance);
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
		
	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
