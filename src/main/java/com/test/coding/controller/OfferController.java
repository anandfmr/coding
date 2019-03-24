package com.test.coding.controller;


import com.test.coding.entity.Offer;
import com.test.coding.model.OfferRequest;
import com.test.coding.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping(value = "/offer")
    public ResponseEntity<String> createOffer(@RequestBody OfferRequest offer) {
        offerService.addOffer(offer);
        return new ResponseEntity<String>("Offer Created", HttpStatus.CREATED);
    }

    @GetMapping(value = {"/offer", "/offer/{jobTitle}"})
    public ResponseEntity<List<Offer>> getOffer(@PathVariable Optional<String> jobTitle) {
        if (jobTitle.isPresent()) {
            Optional<Offer> offer = offerService.fetchOfferByJobTitle(jobTitle.get());
            return offer.isPresent() ? new ResponseEntity<List<Offer>>(Collections.singletonList(offer.get()), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Offer>>(offerService.fetchAll(), HttpStatus.OK);
        }
    }

}
