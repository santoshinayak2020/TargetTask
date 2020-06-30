package com.target.retail.MyRetail.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyRetailServiceImpl{
	
	 @Autowired
	 RestTemplate restTemplate;

	 @HystrixCommand(fallbackMethod = "getDefaultProductInventoryByCode")
	public Object productdetailsWithId(int id) {
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      String url = "https://redsky.target.com/v2/pdp/tcin/"+id+"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
	      
	      return restTemplate.exchange(url, HttpMethod.GET, entity, Object.class).getBody();
	}
	
	@SuppressWarnings("unused")
	public Object getDefaultProductInventoryByCode(int id) {
		return null;
		
	}

}
