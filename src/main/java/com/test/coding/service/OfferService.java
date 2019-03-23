package com.test.coding.service;



import com.test.coding.entity.Offer;
import com.test.coding.model.OfferRequest;
import com.test.coding.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

	@Autowired
	private OfferRepository offerRepository;

	public Offer addOffer(OfferRequest offerRequest){
		return offerRepository.save(new Offer(offerRequest));
	}

}
