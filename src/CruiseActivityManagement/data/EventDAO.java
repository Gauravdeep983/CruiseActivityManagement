package CruiseActivityManagement.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import CruiseActivityManagement.util.SQLConnection;
import CruiseActivityManagement.model.Event;
import CruiseActivityManagement.model.User;

public class EventDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	private static ArrayList<Event> returnEventList(String queryString) {
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
				event.setCapacity(Integer.toString(listEvent.getInt("capacity")));
				event.setDuration(listEvent.getInt("duration"));
				event.setDate(listEvent.getString("date"));
				event.setStartTime(listEvent.getString("start_time"));
				event.setEndTime(listEvent.getString("end_time"));
				event.setEventCoordinator(listEvent.getString("event_coordinator"));
				event.setEstAttendance(Integer.toString(listEvent.getInt("est_attendance")));

				EventInDB.add(event);
			}
		} catch (SQLException e) {
		}
		return EventInDB;
	}

	public static int reserveEvent(Event event, User user) {
		int reserved = 0;
		Statement stmt = null;
		Connection conn = null;
		conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO cruise_activity.participants (id,participant,event_id) VALUES " + "(null, " + "'"
					+ user.getUsername() + "', " + event.getId() + ");";

			reserved = stmt.executeUpdate(sql);
			conn.commit();

		} catch (SQLException e) {
		}
		return reserved;
	}

	public static Event updateEvent(Event event, String ogId) {
		Statement stmt = null;
		Connection conn = null;
		Event updatedEvent = new Event();
		conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			// Check to ignore foreign key constraint
			String preQuery = "SET FOREIGN_KEY_CHECKS=0; ";
			String postQuery = "SET FOREIGN_KEY_CHECKS=1;";

			// Update event table
			String updateEvent = "UPDATE cruise_activity.all_events SET " + "event_name = '" + event.getEvent_name()
					+ "', " + "capacity = " + event.getCapacity() + ", " + "location = '" + event.getLocation() + "', "
					+ "duration = " + event.getDuration() + ", " + "type = '" + event.getType() + "', " + "date = '"
					+ event.getDate() + "', " + "start_time = '" + event.getStartTime() + "', " + "end_time = '"
					+ event.getEndTime() + "', " + "event_coordinator = '" + event.getEventCoordinator() + "', "
					+ "est_attendance = " + event.getEstAttendance() + " WHERE id = " + event.getId() + ";";

			stmt.executeUpdate(preQuery);
			stmt.executeUpdate(updateEvent);
			stmt.executeUpdate(postQuery);
			conn.commit();
			updatedEvent.setEvent(event.getId(), event.getEvent_name(), event.getCapacity(), event.getLocation(),
					event.getDuration(), event.getType(), event.getDate(), event.getStartTime(), event.getEndTime(),
					event.getEventCoordinator(), event.getEstAttendance());
		} catch (SQLException e) {
		}
		return updatedEvent;
	}

	public static Boolean availableCoordinator(String sql) {
		Statement stmt = null;
		Connection conn = null;
		conn = SQLConnection.getDBConnection();
		ArrayList<User> userListInDB = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(sql);
			while (userList.next()) {
				User user = new User();
				user.setUsername(userList.getString("event_coordinator"));
				userListInDB.add(user);
			}
		} catch (SQLException e) {
		}
		return userListInDB.isEmpty();
	}


	public static ArrayList<Event> listSpecificEvent(int id) {
		return returnEventList("SELECT * FROM cruise_activity.all_events WHERE id = " + id);
	}

	public static ArrayList<Event> listAllEvents(String date, String time, String type) {
		String query = "SELECT * FROM cruise_activity.all_events\r\n" + "WHERE date = '" + date
				+ "' AND start_time >= '" + time + "' AND type='" + type + "' \r\n" + "ORDER BY date ASC, start_time ASC, event_name ASC;";
		return returnEventList(query);
	}
	
	public static ArrayList<Event> listAllEvents(String date, String time) {
		String query = "SELECT * FROM cruise_activity.all_events\r\n" + "WHERE date = '" + date
				+ "' AND start_time >= '" + time + "' ORDER BY date ASC, start_time ASC, event_name ASC;";
		return returnEventList(query);
	}
	
	public static ArrayList<Event> listReservedEventsByUsername(String username, String date) {
		String query = "SELECT ae.* from cruise_activity.all_events ae \r\n"
				+ "INNER JOIN participants p ON p.event_id = ae.id\r\n" + "WHERE p.participant = '" + username
				+ "' AND ae.date = '" + date + "';";
		return returnEventList(query);
	}

	public static ArrayList<Event> listReservedEventsByUsername(String username, String date, String time) {
		String query = "SELECT ae.* from cruise_activity.all_events ae \r\n"
				+ "INNER JOIN participants p ON p.event_id = ae.id\r\n" + "WHERE p.participant = '" + username
				+ "' AND ae.date = '" + date + "' AND ae.start_time >= '" + time + "';";
		return returnEventList(query);
	}

	public static boolean availableCoordinator(String startTime, String endTime, String eventCoordinator) {
		String query = "SELECT * FROM cruise_activity.all_events WHERE start_time " + "BETWEEN '" + startTime
				+ "' AND '" + endTime + "' AND event_coordinator='" + eventCoordinator + "'\r\n";

		return availableCoordinator(query);
	}

	public static ArrayList<Event> listAssignedEvents(String date, String time, String username) {
		String query = "SELECT * FROM cruise_activity.all_events\r\n" + 
				"WHERE event_coordinator = '"+username+"' AND date = '"+date+"' AND start_time >= '"+time+"';";
		return returnEventList(query);
	}

}