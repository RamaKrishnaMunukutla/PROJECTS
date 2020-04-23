package com.ojas.obs.timesheet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ojas.obs.timesheet.model.TimeSheetStatus;

@Repository
public interface TimeSheetDao extends JpaRepository<TimeSheetStatus, Integer>{

}
