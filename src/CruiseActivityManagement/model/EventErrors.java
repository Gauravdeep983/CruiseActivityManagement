package CruiseActivityManagement.model;

public class EventErrors {
	private String errorMsg = "";
	private String typeError = "";
	private String capacityError = "";
	private String durationError = "";
	private String dateError = "";
	private String startTimeError = "";
	private String eventCoordinatorError = "";
	private String estAttendanceError = "";
	
	
	public EventErrors() {
		this.setErrorMsg("");
	} 

	public void setErrorMsg() {
		if (!typeError.equals("") || !capacityError.equals("") || !durationError.equals("") || !dateError.equals("") || 
				!startTimeError.equals("") || !eventCoordinatorError.equals("") || !estAttendanceError.equals("")) 
			errorMsg = "Please correct the following errors";		
	}

	public String getErrorMsg() {
		return errorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public String getTypeError() {
		return typeError;
	}


	public void setTypeError(String typeError) {
		this.typeError = typeError;
	}


	public String getCapacityError() {
		return capacityError;
	}


	public void setCapacityError(String capacityError) {
		this.capacityError = capacityError;
	}


	public String getDurationError() {
		return durationError;
	}


	public void setDurationError(String durationError) {
		this.durationError = durationError;
	}


	public String getDateError() {
		return dateError;
	}


	public void setDateError(String dateError) {
		this.dateError = dateError;
	}


	public String getStartTimeError() {
		return startTimeError;
	}


	public void setStartTimeError(String startTimeError) {
		this.startTimeError = startTimeError;
	}


	public String getEventCoordinatorError() {
		return eventCoordinatorError;
	}


	public void setEventCoordinatorError(String eventCoordinatorError) {
		this.eventCoordinatorError = eventCoordinatorError;
	}


	public String getEstAttendanceError() {
		return estAttendanceError;
	}


	public void setEstAttendanceError(String estAttendanceError) {
		this.estAttendanceError = estAttendanceError;
	}
}
