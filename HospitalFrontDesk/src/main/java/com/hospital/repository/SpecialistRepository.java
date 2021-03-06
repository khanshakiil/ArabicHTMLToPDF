package com.hospital.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.hospital.exception.InvalidInput;
import com.hospital.model.AppointmentRequest;
import com.hospital.model.AppointmentResponse;
import com.hospital.model.Hospital;
import com.hospital.model.Patient;
import com.hospital.model.SpecialistList;
import com.hospital.model.SpecialistRequest;
import com.hospital.model.SpecialistResponse;

@Repository
public class SpecialistRepository {
	
	static Map<Integer, List<SpecialistResponse>> database = new HashMap<>();
	static List<Hospital> hospitals = new ArrayList<>();
	static List<SpecialistResponse> specialists = new ArrayList<>();
	static List<Patient> patientList = new ArrayList<>();
	
	static {
		
		hospitals.add(new Hospital(901,"King Abdulaziz",10));
		hospitals.add(new Hospital(902,"King Fahad",20));
		hospitals.add(new Hospital(903,"Al Habib",20));
		hospitals.add(new Hospital(904,"Al Abeer",25));
		hospitals.add(new Hospital(905,"Al Wurud",100));
		
		SpecialistResponse sp1 = new SpecialistResponse("Dentist","Suranya1","Monday,Thursday","1 to 2","N",901);
		SpecialistResponse sp2 = new SpecialistResponse("Dentist","Suranya2","Monday,Thursday","2 to 4","Y",901);
		SpecialistResponse sp3 = new SpecialistResponse("Dentist","Suranya3","Monday,Thursday","4 to 6","N",902);
		SpecialistResponse sp4 = new SpecialistResponse("Dentist","Suranya4","Monday,Thursday","6 to 8","Y",902);
		SpecialistResponse sp5 = new SpecialistResponse("Dentist","Suranya5","Monday,Thursday","8 to 10","N",903);
		SpecialistResponse sp6 = new SpecialistResponse("Dentist","Suranya6","Monday,Thursday","10 to 12","Y",903);
		SpecialistResponse sp7 = new SpecialistResponse("Dentist","Suranya7","Monday,Thursday","12 to 14","N",904);
		SpecialistResponse sp8 = new SpecialistResponse("Dentist","Suranya8","Monday,Thursday","14 to 16","Y",904);
		SpecialistResponse sp9 = new SpecialistResponse("Dentist","Suranya9","Monday,Thursday","16 to 18","N",905);
		SpecialistResponse sp10 = new SpecialistResponse("Dentist","Suranya10","Monday,Thursday","18 to 20","Y",905);
		
		specialists.add(sp1);
		specialists.add(sp2);
		specialists.add(sp3);
		specialists.add(sp4);
		specialists.add(sp5);
		specialists.add(sp6);
		specialists.add(sp7);
		specialists.add(sp8);
		specialists.add(sp9);
		specialists.add(sp10);	
		
		Patient p1= new Patient(1, "Ahmed", "ADMIT", hospitals.get(0) );
		Patient p2= new Patient(2, "Al khalid", "ADMIT", hospitals.get(0) );
		Patient p3= new Patient(3, "Mohd", "ADMIT", hospitals.get(1) );
	
		
		patientList.add(p1);
		patientList.add(p2);
		patientList.add(p3);
	}
	
	public SpecialistList getSpecialists(SpecialistRequest specialistRequest) throws InvalidInput{
		
		System.out.println("72");
		List<Hospital> hospitalList = hospitals.stream().filter(hospital -> hospital.getHospitalName().equals(specialistRequest.getHospitalName())).collect(Collectors.toList());
		System.out.println(hospitalList.size());
		if(hospitalList ==null || hospitalList.size()==0)
			throw new InvalidInput(String.format("No hospital found with name %s",specialistRequest.getHospitalName()));
			
		System.out.println("78");
		Hospital hospital = hospitalList.get(0);// Hospital Name is unique	
		
		List<SpecialistResponse> response = specialists.stream()
				.filter(sp -> sp.getHospitalId() == hospital.getHospitalId() && 
						sp.getType().equals(specialistRequest.getSpecialistType()))
				.collect(Collectors.toList());
		
		return new SpecialistList(response);		
	}
	
	public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) throws InvalidInput{
		
		List<SpecialistResponse> specialistList = specialists.stream()
				.filter(sp-> sp.getName().equals(appointmentRequest.getSpecialistName()))
				.collect(Collectors.toList());
			
		
		SpecialistResponse specialistResponse = specialistList.get(0);
		AppointmentResponse appointmentResponse = new AppointmentResponse();
		
		if(!Arrays.asList( specialistResponse.getAvailableDay().split(",")).contains(appointmentRequest.getAppointmentDay())) {
			throw new InvalidInput(String.format("Not available on %s",appointmentRequest.getAppointmentDay()));	
		}
		
		appointmentResponse.setAppointmentDay(appointmentRequest.getAppointmentDay());
		appointmentResponse.setPatientName(appointmentRequest.getPatientName());
		appointmentResponse.setSpecialistName(appointmentRequest.getSpecialistName());
		appointmentResponse.setAppointmentTime(specialistResponse.getAvailableTime());
		
		return appointmentResponse;
	}
	

	public Integer getAvailableBedCount(String hospitalName) throws InvalidInput{
		
		
		List<Hospital> hospitalList = hospitals.stream().filter(hospital -> hospital.getHospitalName().equals(hospitalName)).collect(Collectors.toList());
		
		if(hospitalList ==null || hospitalList.size()==0)
			throw new InvalidInput(String.format("No hospital found with name %s",hospitalName));
			
		Hospital hospital = hospitalList.get(0);// Hospital Name is unique	
		
		List<Patient> collect = patientList.stream()
					.filter(p-> p.getHospital().getHospitalId() == hospital.getHospitalId() && !p.getStatus().equals("DISCHARGE"))
					.collect(Collectors.toList());
		
		return hospital.getTotalBed() - collect.size();	
			
		}
}
