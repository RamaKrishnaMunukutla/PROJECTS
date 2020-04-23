package com.ojas.obs.timesheet.response;

import java.util.List;

import com.ojas.obs.timesheet.model.TimeSheetStatus;

public class TimeSheetResponse {

	private List<TimeSheetStatus> timesheetList;
	private String statusCode;
	private String statusMessage;

	public TimeSheetResponse() {
		super();
	}

	public List<TimeSheetStatus> getTimesheetList() {
		return timesheetList;
	}

	public void setTimesheetList(List<TimeSheetStatus> timesheetList) {
		this.timesheetList = timesheetList;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
