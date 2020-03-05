package com.hospital.model;

import lombok.Data;

@Data
public class AppointmentRequest {
	
	private String specialistName;
	private String appointmentDay;
	private String patientName;
	private Integer hospitalId;
	
}
