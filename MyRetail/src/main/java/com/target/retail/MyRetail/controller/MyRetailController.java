package com.target.retail.MyRetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.target.retail.MyRetail.service.MyRetailServiceImpl;

@RestController
public class MyRetailController {
	
	private static final String PRODUCT_WITH_ID = "/products/{id}";
	
	@Autowired
	private MyRetailServiceImpl myRetailService;
	
	   @GetMapping(PRODUCT_WITH_ID)
	   public Object getProductList(@PathVariable int id) {
		   
		   return myRetailService.productdetailsWithId(id);
		
	}

}
