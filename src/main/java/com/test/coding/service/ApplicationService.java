package com.test.coding.service;


import com.test.coding.entity.Application;
import com.test.coding.entity.ApplicationCode;
import com.test.coding.entity.Offer;
import com.test.coding.exceptions.DuplicateApplicationException;
import com.test.coding.exceptions.OfferNotFoundException;
import com.test.coding.model.ApplicationProgressRequest;
import com.test.coding.model.ApplicationRequest;
import com.test.coding.model.ApplicationStatus;
import com.test.coding.repository.ApplicationRepository;
import com.test.coding.repository.OfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ApplicationService {

    Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private OfferRepository offerRepository;


    public boolean createApplication(ApplicationRequest applicationRequest) throws OfferNotFoundException, DuplicateApplicationException {
        System.out.println(applicationRequest);
        Optional<Offer> optionalOffer = offerRepository.findById(applicationRequest.getOffer());
        Optional<Application> application = applicationRepository.findById(new ApplicationCode(applicationRequest.getOffer(), applicationRequest.getCandidateEmail()));

        if (application.isPresent()) {
            throw new DuplicateApplicationException("Application already Applied");
        }


        if (optionalOffer.isPresent())
            applicationRepository.save(new Application(applicationRequest));
        else {
            throw new OfferNotFoundException("No Offer Found");
        }
        return true;
    }


    public List<ApplicationRequest> getApplications(String offer, String email, ApplicationStatus applicationStatus) {
        return applicationRepository.findByOfferAndEmailAndApplicationStatus(offer, email, applicationStatus).stream()
                .map(ApplicationRequest::new)
                .collect(Collectors.toList());

    }

    public ApplicationStatus changeStatus(ApplicationProgressRequest request) throws OfferNotFoundException {
        Optional<Application> application = applicationRepository.findById(new ApplicationCode(request.getOffer(), request.getCandidateEmail()));
        if (application.isPresent()) {
            if (validateUpdate(application.get().getApplicationStatus(), request.getApplicationStatus())) {
                application.get().setApplicationStatus(request.getApplicationStatus());
                applicationRepository.save(application.get());
            }
        } else {
            throw new OfferNotFoundException("Application Not Found");
        }
        return request.getApplicationStatus();
    }

    private boolean validateUpdate(ApplicationStatus oldApplicationStatus, ApplicationStatus newApplicationStatus) {
        if (oldApplicationStatus.equals(newApplicationStatus)) {
            throw new UnsupportedOperationException("Status Can Not Be Updated To The Same");
        }

        switch (newApplicationStatus) {
            case INVITED:
                logger.info("Status will be changed to Invited");
                break;
            case REJECTED:
                logger.info("Status will be changed to Rejected");
                break;
            case HIRED:
                logger.info("Status will be changed to Hired");

        }
        return true;
    }
}
