package com.test.coding.controller;



import com.test.coding.model.OfferRequest;
import com.test.coding.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class OfferController {

	@Autowired
	private OfferService offerService;

	@PostMapping(value = "/offer")
	public OfferRequest createOffer(@RequestBody OfferRequest offer){

		return null;
	}

}
