package com.hospital.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "List")
@AllArgsConstructor
public class SpecialistList implements Serializable {

    private static final long serialVersionUID = 22L;

    @JacksonXmlProperty(localName = "item")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<SpecialistResponse> specialistList = new ArrayList<>();
 }