package com.test.coding.service;


import com.test.coding.entity.Application;
import com.test.coding.entity.ApplicationCode;
import com.test.coding.entity.Offer;
import com.test.coding.exceptions.DuplicateApplicationException;
import com.test.coding.exceptions.OfferNotFoundException;
import com.test.coding.model.ApplicationProgressRequest;
import com.test.coding.model.ApplicationRequest;
import com.test.coding.repository.ApplicationRepository;
import com.test.coding.repository.OfferRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.test.coding.model.ApplicationStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationServiceTest {

    @InjectMocks
    private ApplicationService applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private OfferRepository offerRepository;

    private ApplicationRequest applicationRequest;

    private Offer offer;

    private Application application;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        applicationRequest = new ApplicationRequest("dev", "myMail@mail.com", "resume", APPLIED);
        offer = new Offer("dev", LocalDate.now());
        application = new Application(new ApplicationCode("dev", "myMail@mail.com"), "resume", APPLIED);
    }


    @Test(expected = OfferNotFoundException.class)
    public void createApplication_withNoOffer() throws OfferNotFoundException, DuplicateApplicationException {
        when(offerRepository.findById(any())).thenReturn(Optional.empty());


        applicationService.createApplication(applicationRequest);
    }

    @Test
    public void createApplication_success() throws OfferNotFoundException, DuplicateApplicationException {
        when(offerRepository.findById(any())).thenReturn(Optional.of(offer));
        when(applicationRepository.findById(any())).thenReturn(Optional.empty());

        assertTrue(applicationService.createApplication(applicationRequest));
    }

    @Test(expected = DuplicateApplicationException.class)
    public void createApplication_alreadyExists() throws OfferNotFoundException, DuplicateApplicationException {
        when(offerRepository.findById(any())).thenReturn(Optional.of(offer));
        when(applicationRepository.findById(any())).thenReturn(Optional.of(application));

        assertTrue(applicationService.createApplication(applicationRequest));
    }

    @Test
    public void getApplications() {
        when(applicationRepository.findByOfferAndEmailAndApplicationStatus(any(), any(), any()))
                .thenReturn(Collections.singletonList(application));
        List<ApplicationRequest> applications = applicationService.getApplications(null, null, null);

        assertEquals(1, applications.size());
        assertEquals("dev", applications.get(0).getOffer());
    }

    @Test(expected = OfferNotFoundException.class)
    public void ApplicationProgressRequest_applicationCandidateNotExists() throws OfferNotFoundException {
        when(applicationRepository.findById(any())).thenReturn(Optional.empty());

        ApplicationProgressRequest applicationProgressRequest = new ApplicationProgressRequest("dev", "myMail@mail.com", INVITED);

        assertEquals(applicationProgressRequest.getApplicationStatus(), applicationService.changeStatus(applicationProgressRequest));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void ApplicationProgressRequest_appliedToApplied() throws OfferNotFoundException {
        when(applicationRepository.findById(any())).thenReturn(Optional.of(application));

        ApplicationProgressRequest ApplicationProgressRequest = new ApplicationProgressRequest("dev", "myMail@mail.com", APPLIED);

        assertEquals("Application couldn't progress to status APPLIED", applicationService.changeStatus(ApplicationProgressRequest));
    }

    @Test
    public void ApplicationProgressRequest_appliedToInvited() throws OfferNotFoundException {
        when(applicationRepository.findById(any())).thenReturn(Optional.of(application));

        ApplicationProgressRequest applicationProgressRequest = new ApplicationProgressRequest("dev", "myMail@mail.com", INVITED);

        assertEquals(INVITED, applicationService.changeStatus(applicationProgressRequest));
    }

    @Test
    public void ApplicationProgressRequest_appliedToRejected() throws OfferNotFoundException {
        when(applicationRepository.findById(any())).thenReturn(Optional.of(application));

        ApplicationProgressRequest applicationProgressRequest = new ApplicationProgressRequest("dev", "myMail@mail.com", REJECTED);

        assertEquals(REJECTED, applicationService.changeStatus(applicationProgressRequest));
    }

    @Test
    public void ApplicationProgressRequest_appliedToHired() throws OfferNotFoundException {
        when(applicationRepository.findById(any())).thenReturn(Optional.of(application));

        ApplicationProgressRequest applicationProgressRequest = new ApplicationProgressRequest("dev", "myMail@mail.com", HIRED);

        assertEquals(HIRED, applicationService.changeStatus(applicationProgressRequest));
    }

    @Test
    public void ApplicationProgressRequest_invitedToApplied() throws OfferNotFoundException {
        Application invitedApp = new Application(new ApplicationCode("dev", "myMail@mail.com"), "resume", INVITED);
        when(applicationRepository.findById(any())).thenReturn(Optional.of(invitedApp));

        ApplicationProgressRequest applicationProgressRequest = new ApplicationProgressRequest("dev", "myMail@mail.com", APPLIED);

        assertEquals(APPLIED, applicationService.changeStatus(applicationProgressRequest));
    }


}
