package CruiseActivityManagement.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import CruiseActivityManagement.util.SQLConnection;
import CruiseActivityManagement.model.Event;
public class EventDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<Event> returnEventList (String queryString) {
		ArrayList<Event> EventInDB = new ArrayList<Event>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet listEvent = stmt.executeQuery(queryString);
			while (listEvent.next()) {
				Event event = new Event(); 
				event.setId(listEvent.getInt("id"));
				event.setEvent_name(listEvent.getString("event_name"));
				event.setType(listEvent.getString("type"));
				event.setLocation(listEvent.getString("location"));
				event.setCapacity(listEvent.getInt("capacity"));  
				event.setDuration(listEvent.getInt("duration"));  
				event.setDate(listEvent.getString("date"));
				event.setStartTime(listEvent.getString("start_time"));
				event.setEndTime(listEvent.getString("end_time"));
				event.setEventCoordinator(listEvent.getString("event_coordinator")); 
				event.setEstAttendance(listEvent.getInt("est_attendance"));  
				
				EventInDB.add(event);	
			}
		} catch (SQLException e) {}
		return EventInDB;
	}
	
	public static ArrayList<Event> listAllEvents() {  
			return returnEventList(" SELECT * from ALL_EVENTS ");
	}
	
	public static ArrayList<Event> listAllCreatedEvents() {  
		return returnEventList("SELECT e.*, ev.capacity, ev.location, ev.duration, ev.type FROM cruise_activity.event AS e\r\n" + 
				"	INNER JOIN cruise_activity.all_events AS ev ON ev.event_name = e.event_name");
	}
	
	public static ArrayList<Event> listSpecificEvent(int id) {  
		return returnEventList("SELECT e.*, ev.capacity, ev.location, ev.duration, ev.type FROM cruise_activity.event AS e\r\n" + 
				"	INNER JOIN cruise_activity.all_events AS ev ON ev.event_name = e.event_name WHERE e.id = "+id);
	}
	
		
	
	
}