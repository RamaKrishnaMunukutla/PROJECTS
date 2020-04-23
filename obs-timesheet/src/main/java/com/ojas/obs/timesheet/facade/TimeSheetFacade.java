package com.ojas.obs.timesheet.facade;

import org.springframework.http.ResponseEntity;

import com.ojas.obs.timesheet.request.TimeSheetRequest;

public interface TimeSheetFacade {

	public ResponseEntity<Object> saveTimeSheet(TimeSheetRequest timesheetRequest);
	public ResponseEntity<Object> getTimeSheet(TimeSheetRequest timesheetRequest);
}
