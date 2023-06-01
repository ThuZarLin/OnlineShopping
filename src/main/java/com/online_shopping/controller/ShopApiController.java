package com.online_shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.online_shopping.service.ShopService;

@RestController
public class ShopApiController {
	@Autowired
	ShopService shopService;
	@SuppressWarnings({"rawtypes", "unchecked"})
	@GetMapping("/check-email/{email}")
	public ResponseEntity isEmailSatisfy(@PathVariable String email) {
		if(this.shopService.isEmailSatisfy(email.toLowerCase())) {
			return ResponseEntity.ok("Yous email is  valid");
		}else {
			return new ResponseEntity("Your email is already registered", HttpStatus.CONFLICT);
		}
	}
}
