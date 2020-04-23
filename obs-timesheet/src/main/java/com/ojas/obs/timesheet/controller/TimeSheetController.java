package com.ojas.obs.timesheet.controller;

import static com.ojas.obs.timesheet.constant.Constant.GET;
import static com.ojas.obs.timesheet.constant.Constant.GETBYID;
import static com.ojas.obs.timesheet.constant.Constant.REQUESTOBJECTNULL;
import static com.ojas.obs.timesheet.constant.Constant.SAVE;
import static com.ojas.obs.timesheet.constant.Constant.SET;
import static com.ojas.obs.timesheet.constant.Constant.TIMESHEETOBJECTFIELDNULL;
import static com.ojas.obs.timesheet.constant.Constant.TIMESHEETSERVICE;
import static com.ojas.obs.timesheet.constant.Constant.UPDATE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.timesheet.facadeImpl.TimeSheetFacadeImpl;
import com.ojas.obs.timesheet.model.ErrorResponse;
import com.ojas.obs.timesheet.model.TimeSheetStatus;
import com.ojas.obs.timesheet.request.TimeSheetRequest;

@RestController
@RequestMapping(TIMESHEETSERVICE)
public class TimeSheetController {

	@Autowired
	private TimeSheetFacadeImpl facade;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping(SET)
	public ResponseEntity<Object> saveTimeSheet(@RequestBody TimeSheetRequest timeSheetRequestObject,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		logger.debug("data is received into controller" + timeSheetRequestObject);
		List<TimeSheetStatus> timesheet = timeSheetRequestObject.getTimeSheetStatus();

		try {
			if ((timeSheetRequestObject == null) || (timeSheetRequestObject.getTimeSheetStatus() == null)
					|| timeSheetRequestObject.getTransactionType() == null) {
				ErrorResponse error = new ErrorResponse();
				error.setStatusCode("422");
				error.setStatusMessage(REQUESTOBJECTNULL);
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			if (timeSheetRequestObject.getTransactionType().equalsIgnoreCase(SAVE)) {
				for (TimeSheetStatus timesheet1 : timesheet) {
					if (timesheet1.getStatus() == null || timesheet1.getStatus().isEmpty()) {
						ErrorResponse error = new ErrorResponse();
						error.setStatusCode("422");
						error.setStatusMessage(TIMESHEETOBJECTFIELDNULL);
						return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
					}
				}
			}

			if (timeSheetRequestObject.getTransactionType().equalsIgnoreCase(UPDATE)) {
				for (TimeSheetStatus timesheet2 : timesheet) {
					if (timesheet2.getStatus() == null || timesheet2.getStatus().isEmpty()) {
						ErrorResponse error = new ErrorResponse();
						error.setStatusCode("422");
						error.setStatusMessage(TIMESHEETOBJECTFIELDNULL);
						return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
					}
				}
			}
			return facade.saveTimeSheet(timeSheetRequestObject);
		} catch (DuplicateKeyException e) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusMessage("duplicate key Exception");
			error.setStatusCode("409");
			error.setStatusMessage(e.getCause().getLocalizedMessage());
			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setStatusCode("409");
			error.setStatusMessage(e.getMessage());
			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		}
	}

	@PostMapping(GET)
	public ResponseEntity<Object> getTimeSheet(@RequestBody TimeSheetRequest timesheetRequest,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		logger.debug("received data into controller" + timesheetRequest);
		try {
			if (timesheetRequest == null || timesheetRequest.getTimeSheetStatus() == null
					|| timesheetRequest.getTimeSheetStatus().isEmpty() || timesheetRequest.getTransactionType() == null
					|| timesheetRequest.getTransactionType().isEmpty()) {
				ErrorResponse error = new ErrorResponse();
				error.setStatusCode("422");
				error.setStatusMessage("REQUESTOBJECTNULL");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (timesheetRequest.getTransactionType().equalsIgnoreCase(GETBYID)
					&& timesheetRequest.getTimeSheetStatus().get(0).getStatusId() == null) {
				logger.error("please provide id");
				ErrorResponse error = new ErrorResponse();
				error.setStatusCode("422");
				error.setStatusMessage("Type must be getAll");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			return facade.getTimeSheet(timesheetRequest);
		} catch (Exception e) {
			logger.debug("inside catch block.*******");
			ErrorResponse er = new ErrorResponse();
			er.setStatusCode("409");
			er.setStatusMessage(e.getMessage());
			return new ResponseEntity<>(er, HttpStatus.CONFLICT);
		}

	}
}
