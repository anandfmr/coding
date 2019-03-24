package com.test.coding.service;


import com.test.coding.entity.Offer;
import com.test.coding.model.OfferRequest;
import com.test.coding.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public boolean addOffer(OfferRequest offerRequest) {
        offerRepository.save(new Offer(offerRequest));
        return true;
    }

    public Optional<Offer> fetchOfferByJobTitle(String jobTitle) {
        return offerRepository.findById(jobTitle);
    }

    public List<Offer> fetchAll() {
        return offerRepository.findAll();
    }

}
