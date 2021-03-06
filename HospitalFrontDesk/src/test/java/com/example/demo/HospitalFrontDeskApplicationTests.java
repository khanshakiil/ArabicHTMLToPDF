package com.example.demo;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.hospital.HospitalFrontDeskApplication;
import com.hospital.model.SpecialistList;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(classes = HospitalFrontDeskApplication.class)
public class HospitalFrontDeskApplicationTests {

	@org.junit.Test
	public void contextLoads() {
	}
	
	@Test
	public void getSpecialist()
	{
		final String uri = "http://localhost:8080/getSpecialist";
		RestTemplate restTemplate = new RestTemplate();
		  restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
          restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    	    
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    	    
	     UriComponents build = UriComponentsBuilder.fromHttpUrl(uri)
	    		.queryParam("hospitalName", "Al Abeer")
	    		.queryParam("specialistType", "Dentist").build();
	    
	     try {
	    ResponseEntity<SpecialistList> result = restTemplate.exchange( build.toUri() , HttpMethod.GET, entity, SpecialistList.class);
	    System.out.println(result);
	     }
	    catch (HttpClientErrorException exception) {
            System.out.println( "callToRestService Error :" + exception.getResponseBodyAsString());
            //Handle exception here
        }catch (HttpStatusCodeException exception) {
        	System.out.println( "callToRestService Error :" + exception.getResponseBodyAsString());
            //Handle exception here
        }
	    
	}
}
