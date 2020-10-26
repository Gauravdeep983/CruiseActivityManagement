package CruiseActivityManagement.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import CruiseActivityManagement.model.Event;
import CruiseActivityManagement.model.User;
import CruiseActivityManagement.util.SQLConnection;

public class UserDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();

	public static User registerUser(User user) {
		Statement stmt = null;
		Connection conn = null;
		conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String insertUser = "INSERT INTO cruise_activity.user (username,password,firstname,lastname,phone,email,room_number,deck_number,membership_type,role) VALUES ('"
					+ user.getUsername() + "','" + user.getPassword() + "','" + user.getFirstName() + "','"
					+ user.getLastName() + "'," + user.getPhone() + ",'" + user.getEmail() + "'," + user.getRoomNumber()
					+ "," + user.getDeckNumber() + ",'" + user.getMembershipType() + "','" + user.getRole() + "')";
			stmt.executeUpdate(insertUser);
			conn.commit();
		} catch (SQLException e) {
		}
		return user;
	}

	// To check if the username inputted by user is unique or not
	public static Boolean uniqueUsername(String username) {
		Statement stmt = null;
		Connection conn = null;
		conn = SQLConnection.getDBConnection();
		ArrayList<User> userListInDB = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT * from cruise_activity.user WHERE username = '" + username + "'";
			ResultSet userList = stmt.executeQuery(searchUser);
			while (userList.next()) {
				User user = new User();
				String username1 = userList.getString("username");
				user.setUsername(username1);
				userListInDB.add(user);
			}
		} catch (SQLException e) {
		}
		return userListInDB.isEmpty();
	}

	// Login function
	public static User login(String username, String password) {
		Statement stmt = null;
		Connection conn = null;
		User user = new User();
		conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String sql = " SELECT * from cruise_activity.user WHERE username = '" + username + "' AND password ='"
					+ password + "' ";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				user.setUser(rs.getString("username"), rs.getString("password"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getString("phone"), rs.getString("email"),
						Integer.toString(rs.getInt("room_number")), Integer.toString(rs.getInt("deck_number")), rs.getString("membership_type"),
						rs.getString("role"));
			}

		} catch (SQLException e) {
			return user;
		}
		return user;
	}

	public static User viewprofile(String username) {
		Statement stmt = null;
		Connection conn = null;
		User user = new User();
		conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String sql = "Select * from cruise_activity.user WHERE username =" + username + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				user.setUser(rs.getString("username"), rs.getString("password"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getString("phone"), rs.getString("email"),
						Integer.toString(rs.getInt("room_number")), Integer.toString(rs.getInt("deck_number")), rs.getString("membership_type"),
						rs.getString("role"));
			}

		} catch (SQLException e) {
			return user;
		}
		return user;
	}

	private static ArrayList<User> returnAttendingEvent(String queryString) {
		ArrayList<User> UserInDB = new ArrayList<User>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet listEvent = stmt.executeQuery(queryString);
			while (listEvent.next()) {
				User user = new User();
				Event event = new Event();
				event.setId(listEvent.getInt("id"));
				event.setEvent_name(listEvent.getString("event_name"));
				event.setDate(listEvent.getString("date"));
				event.setStartTime(listEvent.getString("start_time"));
				event.setEndTime(listEvent.getString("end_time"));
				event.setEventCoordinator(listEvent.getString("event_coordinator"));
				event.setEstAttendance(Integer.toString(listEvent.getInt("est_attendance")));
				user.setUsername(listEvent.getString("username"));
				user.setFirstName(listEvent.getString("firstname"));
				user.setLastName(listEvent.getString("lastname"));
				user.setPhone(listEvent.getString("phone"));
				user.setEmail(listEvent.getString("email"));
				user.setRoomNumber(Integer.toString(listEvent.getInt("room_number")));
				user.setDeckNumber(Integer.toString(listEvent.getInt("deck_number")));
				user.setMembershipType(listEvent.getString("membership_type"));
				user.setRole(listEvent.getString("role"));

				user.setEvent(event);

				UserInDB.add(user);
			}
		} catch (SQLException e) {
		}
		return UserInDB;
	}

	private static ArrayList<User> returnParticipants(String queryString) {
		ArrayList<User> UserInDB = new ArrayList<User>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet listEvent = stmt.executeQuery(queryString);
			while (listEvent.next()) {
				User user = new User();
				user.setUsername(listEvent.getString("participant"));

				UserInDB.add(user);
			}
		} catch (SQLException e) {
		}
		return UserInDB;
	}

	public static User updateUser(User user, String ogUsername) {
		Statement stmt = null;
		Connection conn = null;
		User updatedUser = new User();
		conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			// Check to ignore foreign key constraint
			String preQuery = "SET FOREIGN_KEY_CHECKS=0; ";
			String postQuery = "SET FOREIGN_KEY_CHECKS=1;";
			// Update properties in 'participant' table
			String participantQuery = "UPDATE cruise_activity.participants SET " + "participant = '"
					+ user.getUsername() + "' WHERE participant = '" + ogUsername + "'; ";
			// Update user table
			String updateUser = "UPDATE cruise_activity.user SET " + "username = '" + user.getUsername() + "', "
					+ "password = '" + user.getPassword() + "', " + "firstname = '" + user.getFirstName() + "', "
					+ "lastname = '" + user.getLastName() + "', " + "phone = " + user.getPhone() + ", " + "email = '"
					+ user.getEmail() + "', " + "room_number = " + user.getRoomNumber() + ", " + "deck_number = "
					+ user.getDeckNumber() + ", " + "membership_type = '" + user.getMembershipType() + "' "
					+ "WHERE username ='" + ogUsername + "'; ";

			stmt.executeUpdate(preQuery);
			stmt.executeUpdate(updateUser);
			stmt.executeUpdate(participantQuery);
			stmt.executeUpdate(postQuery);
			conn.commit();
			updatedUser.setUser(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
					user.getPhone(), user.getEmail(), user.getRoomNumber(), user.getDeckNumber(),
					user.getMembershipType(), user.getRole());
		} catch (SQLException e) {
			e.getMessage();
		}
		return updatedUser;
	}

	public static ArrayList<User> passengerReservedEvents(String username) {
		return returnAttendingEvent("SELECT e.*, u.* FROM cruise_activity.participants p\r\n"
				+ "INNER JOIN cruise_activity.all_events e ON e.id = p.event_id\r\n"
				+ "INNER JOIN cruise_activity.user u ON u.username = p.participant\r\n" + "WHERE p.participant = '"
				+ username + "'");
	}

	public static ArrayList<User> listParticipantsInEvent(int event_id) {
		return returnParticipants("SELECT participant FROM cruise_activity.participants\r\n"
				+ "WHERE participants.event_id = " + event_id);
	}

	public static ArrayList<User> listEventCoordinators() {
		return returnEventCoordinators("SELECT * FROM cruise_activity.user WHERE role = 'EVENT_COORDINATOR';");
	}

	private static ArrayList<User> returnEventCoordinators(String queryString) {
		ArrayList<User> UserInDB = new ArrayList<User>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet listEvent = stmt.executeQuery(queryString);
			while (listEvent.next()) {
				User user = new User();
				user.setUsername(listEvent.getString("username"));
				user.setFirstName(listEvent.getString("firstname"));
				user.setLastName(listEvent.getString("lastname"));
				user.setPhone(listEvent.getString("phone"));
				user.setEmail(listEvent.getString("email"));
				user.setRoomNumber(Integer.toString(listEvent.getInt("room_number")));
				user.setDeckNumber(Integer.toString(listEvent.getInt("deck_number")));
				user.setMembershipType(listEvent.getString("membership_type"));
				user.setRole(listEvent.getString("role"));
				UserInDB.add(user);
			}
		} catch (SQLException e) {
		}
		return UserInDB;
	}

}
