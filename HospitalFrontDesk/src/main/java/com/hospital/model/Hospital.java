package com.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hospital {
private Integer hospitalId;
private String HospitalName;
private int totalBed;

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Hospital other = (Hospital) obj;
	if (hospitalId == null) {
		if (other.hospitalId != null)
			return false;
	} else if (!hospitalId.equals(other.hospitalId))
		return false;
	return true;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((hospitalId == null) ? 0 : hospitalId.hashCode());
	return result;
}


}
