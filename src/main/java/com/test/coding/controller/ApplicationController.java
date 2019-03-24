package com.test.coding.controller;


import com.test.coding.exceptions.DuplicateApplicationException;
import com.test.coding.exceptions.OfferNotFoundException;
import com.test.coding.model.ApplicationProgressRequest;
import com.test.coding.model.ApplicationRequest;
import com.test.coding.model.ApplicationStatus;
import com.test.coding.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;


    @PostMapping("/application")
    public ResponseEntity<String> apply(@RequestBody ApplicationRequest applicationRequest) throws OfferNotFoundException, DuplicateApplicationException {
        applicationService.createApplication(applicationRequest);
        return new ResponseEntity<String>("Applied Successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping("/application")
    public List<ApplicationRequest> getApplications(@RequestParam(value = "offer", required = false) String offer,
                                                    @RequestParam(value = "email", required = false) String email,
                                                    @RequestParam(value = "applicationStatus", required = false) ApplicationStatus applicationStatus) {
        return applicationService.getApplications(offer, email, applicationStatus);
    }

    @PostMapping("/applicationProgress")
    public ResponseEntity<String> progressApplication(@RequestBody ApplicationProgressRequest applicationProgressRequest) throws OfferNotFoundException {
        ApplicationStatus applicationStatus = applicationService.changeStatus(applicationProgressRequest);
        return new ResponseEntity<>(applicationStatus.name(), HttpStatus.OK);
    }
}
