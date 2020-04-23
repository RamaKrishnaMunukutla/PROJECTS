package com.ojas.obs.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({ "com.ojas.obs.timesheet.*" })
public class ObsTimesheetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObsTimesheetApplication.class, args);
	}

}
