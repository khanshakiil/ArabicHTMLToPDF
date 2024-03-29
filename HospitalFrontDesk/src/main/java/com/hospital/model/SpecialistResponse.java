package com.hospital.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(localName = "item")
public class SpecialistResponse {
    @JacksonXmlProperty  
	private String type;
	private String name;
	private String availableDay;
	private String availableTime;
	private String isAvailable;
	private int hospitalId;
	
}
