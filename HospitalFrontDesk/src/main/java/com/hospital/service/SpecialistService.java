package com.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hospital.exception.InvalidInput;
import com.hospital.model.AppointmentRequest;
import com.hospital.model.AppointmentResponse;
import com.hospital.model.SpecialistList;
import com.hospital.model.SpecialistRequest;
import com.hospital.model.SpecialistResponse;
import com.hospital.repository.SpecialistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpecialistService {
	
	private final SpecialistRepository specialistRepository;

	public SpecialistList getSpecialist(SpecialistRequest specialistRequest) throws InvalidInput{
		return specialistRepository.getSpecialists(specialistRequest);
	}

	public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) throws InvalidInput{
		return specialistRepository.createAppointment(appointmentRequest);
	}
	
	public Integer getAvailableBedCount(String hospitalName) throws InvalidInput{
		return specialistRepository.getAvailableBedCount(hospitalName);
	}
}
