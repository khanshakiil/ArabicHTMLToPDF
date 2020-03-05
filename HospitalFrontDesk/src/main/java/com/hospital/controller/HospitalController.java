package com.hospital.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.exception.InvalidInput;
import com.hospital.model.AppointmentRequest;
import com.hospital.model.AppointmentResponse;
import com.hospital.model.SpecialistList;
import com.hospital.model.SpecialistRequest;
import com.hospital.service.SpecialistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HospitalController {

	
	private final SpecialistService specialistService;
	
	//@GetMapping(path = "${helpdesk.getSpecialist}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	private SpecialistList getSpecialists(@RequestBody SpecialistRequest specialistRequest ) throws InvalidInput {
	System.out.println("Here..");
		SpecialistList specialist = specialistService.getSpecialist(specialistRequest);
		if(specialist.getSpecialistList().isEmpty()) {
			throw new InvalidInput("No specialist found for "+ specialistRequest.getHospitalName());
		}
		return  specialist;
	}

	@PostMapping(path = "${helpdesk.createAppointment}")
	public AppointmentResponse createAppointment(@RequestBody AppointmentRequest appointmentRequest ) throws InvalidInput {
		AppointmentResponse specialist = specialistService.createAppointment(appointmentRequest);
		return  specialist;
	}
	
	@GetMapping(path = "${helpdesk.getAvailableBedCount}")
	public String getAvailableBedCount(String hospitalName ) throws InvalidInput {
		Integer availableBedCount = specialistService.getAvailableBedCount(hospitalName);
		if(availableBedCount==0)
		{
			throw new InvalidInput("Beds are not available");
		}
		
		return "Number of Bed Available is ="+availableBedCount;
		
	}

	@GetMapping(path = "${helpdesk.getSpecialist}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public SpecialistList get(@RequestParam("hospitalName") String hospitalName, @RequestParam("specialistType") String specialistType) throws InvalidInput {
		System.out.println("specialistService="+specialistService);
		SpecialistRequest specialistRequest = new SpecialistRequest(hospitalName,specialistType);
		System.out.println(specialistService);
		SpecialistList specialist = specialistService.getSpecialist(specialistRequest);
		System.out.println(specialistType+"Here.."+hospitalName);	
		if(specialist.getSpecialistList().isEmpty()) {
			throw new InvalidInput("No specialist found for "+ specialistRequest.getHospitalName());
		}
		
		return  specialist;
	}

}
