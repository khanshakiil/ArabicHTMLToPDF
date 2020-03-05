package com.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Patient {
private Integer patientId;
private String patientName;
private String status;
private Hospital hospital;

}
