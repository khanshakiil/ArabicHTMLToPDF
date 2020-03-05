package com.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpecialistRequest {
	
	private String hospitalName;
	private String specialistType;
}
