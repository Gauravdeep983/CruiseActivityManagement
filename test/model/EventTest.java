package model;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import CruiseActivityManagement.model.Event;
import CruiseActivityManagement.model.EventErrors;
import CruiseActivityManagement.model.User;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class EventTest {
	private Event event;
	private User user;
	private EventErrors eventErrors;

	@Before
	public void setUp() throws Exception {
		event = new Event();
		user = new User();
		eventErrors = new EventErrors();
	}

	@Test
	@FileParameters("test/model/updateEventTest.csv")
	public void eventUpdateTest(int testcaseNo, int id, String event_name, String capacity, String location, int duration,
			String type, String date, String startTime, String endTime, String eventCoordinator, String estAttendance,
			String errorMsg, String eventCoordinatorError, String estAttendanceError, String durationError) {

		event.setEvent(id, event_name, capacity, location, duration, type, date, startTime, endTime, eventCoordinator,
				estAttendance);
		event.validateUpdateEvent(event, eventErrors);

		assertTrue(errorMsg.equals(eventErrors.getErrorMsg()));
		assertTrue(eventCoordinatorError.equals(eventErrors.getEventCoordinatorError()));
		assertTrue(estAttendanceError.equals(eventErrors.getEstAttendanceError()));
		assertTrue(durationError.equals(eventErrors.getDurationError()));
	}

	@Test
	@FileParameters("test/model/reservationTest.csv")
	public void eventReservationTest(int testcaseNo, int id, String event_name, String capacity, String location,
			int duration, String type, String date, String startTime, String endTime, String eventCoordinator,
			String estAttendance, String username, String password, String errorMsg, String typeError,
			String capacityError) {

		event.setEvent(id, event_name, capacity, location, duration, type, date, startTime, endTime, eventCoordinator,
				estAttendance);
		user.setUser(username, password);
		event.validateReservation(event, user, eventErrors);

		assertTrue(errorMsg.equals(eventErrors.getErrorMsg()));
		assertTrue(typeError.equals(eventErrors.getTypeError()));
		assertTrue(capacityError.equals(eventErrors.getCapacityError()));
	}

//	@Test
//	@FileParameters("") 
//	public void pastDateTest(int testcaseNo, int id, String event_name, int capacity, String location, int duration, String type,
//			String date, String startTime, String endTime, String eventCoordinator, int estAttendance, String errorMsg, String dateError, String timeError) {
//		
//		event.setEvent(id, event_name, capacity, location, duration, type, dateError, startTime, endTime, eventCoordinator, estAttendance);
//
//		assertTrue(errorMsg.equals(eventErrors.getErrorMsg()));
//		assertTrue(dateError.equals(eventErrors.getDateError()));
//		assertTrue(timeError.equals(eventErrors.getEndTimeError()));
//	}
}
