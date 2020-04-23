package com.ojas.obs.timesheet.facadeImpl;

import static com.ojas.obs.timesheet.constant.Constant.GETALL;
import static com.ojas.obs.timesheet.constant.Constant.GETBYID;
import static com.ojas.obs.timesheet.constant.Constant.SAVE;
import static com.ojas.obs.timesheet.constant.Constant.UPDATE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.timesheet.dao.TimeSheetDao;
import com.ojas.obs.timesheet.facade.TimeSheetFacade;
import com.ojas.obs.timesheet.model.TimeSheetStatus;
import com.ojas.obs.timesheet.request.TimeSheetRequest;
import com.ojas.obs.timesheet.response.TimeSheetResponse;

@Service
public class TimeSheetFacadeImpl implements TimeSheetFacade {

	@Autowired
	private TimeSheetDao repo;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ResponseEntity<Object> saveTimeSheet(TimeSheetRequest timesheetRequest) {

		TimeSheetResponse response = null;
		if (timesheetRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
			response = new TimeSheetResponse();
			List<TimeSheetStatus> timeSheetStatus = timesheetRequest.getTimeSheetStatus();
			for (TimeSheetStatus timeSheetStatus2 : timeSheetStatus) {
				TimeSheetStatus save = repo.save(timeSheetStatus2);
				if (save != null) {
					logger.debug("save method");
					response.setStatusCode("200");
					response.setStatusMessage("Timesheet details has saved successfully");
					return new ResponseEntity<Object>(response, HttpStatus.OK);
				} else {
					logger.debug("update method");
					response.setStatusCode("422");
					response.setStatusMessage("failed to save");
					return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
		}
		if (timesheetRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
			response = new TimeSheetResponse();
			for (TimeSheetStatus time : timesheetRequest.getTimeSheetStatus()) {
				Integer statusId = timesheetRequest.getTimeSheetStatus().get(0).getStatusId();
				Optional<TimeSheetStatus> findById = repo.findById(statusId);
				if (findById != null && findById.get().getStatusId() != null) {
					repo.save(time);
					response.setStatusCode("200");
					response.setStatusMessage("TimeSheet details has updated successfully");
					return new ResponseEntity<Object>(response, HttpStatus.OK);
				} else {
					logger.debug("update method");
					response.setStatusCode("422");
					response.setStatusMessage("failed to update");
					return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
		} 
		boolean obj = (timesheetRequest.getTransactionType().equalsIgnoreCase(SAVE)
				|| timesheetRequest.getTransactionType().equalsIgnoreCase(UPDATE));
		if (!obj) {
			response = new TimeSheetResponse();
			response.setStatusCode("422");
			response.setStatusMessage("transaction type is not correct");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getTimeSheet(TimeSheetRequest timesheetRequest) {
		List<TimeSheetStatus> list = timesheetRequest.getTimeSheetStatus();
		logger.debug("inside getAll TimeSheet");
		TimeSheetResponse response = null;
		List<TimeSheetStatus> getAll = null;
		if (timesheetRequest.getTransactionType().equalsIgnoreCase(GETALL)) {
			getAll = repo.findAll();
			if (!getAll.isEmpty()) {
				response = new TimeSheetResponse();
				response.setTimesheetList(getAll);
				response.setStatusMessage("success");
				response.setStatusCode("200");
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			} else {
				response = new TimeSheetResponse();
				response.setTimesheetList(new ArrayList<TimeSheetStatus>());
				response.setStatusMessage("No records found");
				response.setStatusCode("409");
				return new ResponseEntity<Object>(response, HttpStatus.CONFLICT);
			}
		}
		if (timesheetRequest.getTransactionType().equalsIgnoreCase(GETBYID) && list.get(0).getStatusId() != null) {
			for (TimeSheetStatus timesheet : list) {
				if (timesheet.getStatusId() != null) {
					Integer id = timesheetRequest.getTimeSheetStatus().get(0).getStatusId();
					ArrayList<TimeSheetStatus> listSheetStatus = new ArrayList<TimeSheetStatus>();
					TimeSheetStatus getById = repo.findById(id).orElse(new TimeSheetStatus());
					listSheetStatus.add(getById);
					if (getById != null && getById.getStatus() != null) {
						response = new TimeSheetResponse();
						response.setTimesheetList(listSheetStatus);
						response.setStatusCode("200");
						response.setStatusMessage("success");
						return new ResponseEntity<Object>(response, HttpStatus.OK);
					} else {
						response = new TimeSheetResponse();
						response.setStatusCode("422");
						response.setStatusMessage("please provide valid id");
						return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
					}

				}
			}
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
