package com.hospital.model;

import lombok.Data;

@Data
public class AppointmentResponse {
	
	private String specialistName;
	private String appointmentDay;
	private String appointmentTime;
	private String patientName;
	
}
