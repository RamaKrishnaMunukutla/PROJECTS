package com.ojas.obs.timesheet.request;

import java.util.List;

import com.ojas.obs.timesheet.model.TimeSheetStatus;

public class TimeSheetRequest {

	
	private List<TimeSheetStatus> timeSheetStatus;
	private String transactionType;


	public TimeSheetRequest() {
		super();
	}

	public TimeSheetRequest(List<TimeSheetStatus> timeSheetStatus, String transactionType) {
		super();
		this.timeSheetStatus = timeSheetStatus;
		this.transactionType = transactionType;
	}

	public String getTransactionType() {
		return transactionType;
	}

	void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public List<TimeSheetStatus> getTimeSheetStatus() {
		return timeSheetStatus;
	}

	public void setTimeSheetStatus(List<TimeSheetStatus> timeSheetStatus) {
		this.timeSheetStatus = timeSheetStatus;
	}

	@Override
	public String toString() {
		return "TimeSheetRequest [timeSheetStatus=" + timeSheetStatus + ", transactionType=" + transactionType + "]";
	}
}
