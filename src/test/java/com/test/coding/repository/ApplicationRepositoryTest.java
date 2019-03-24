package com.test.coding.repository;


import com.test.coding.entity.Application;
import com.test.coding.entity.ApplicationCode;
import com.test.coding.entity.Offer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static com.test.coding.model.ApplicationStatus.APPLIED;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Before
    public void setUp() throws Exception {
        Offer offer = new Offer("Sr. Developer", LocalDate.of(2019, 03, 24));
        Application application1 = new Application(new ApplicationCode("Sr. Developer", "pks@gmail.com"), "Some Text Here", APPLIED);
        Application application2 = new Application(new ApplicationCode("Sr. Developer", "aks@gmail.com"), "Some Text Here", APPLIED);
        offerRepository.save(offer);
        applicationRepository.save(application1);
        applicationRepository.save(application2);
    }

    @Test
    public void testFindByOfferAndEmailAndApplicationStatus() {
        assertEquals(2, applicationRepository.findByOfferAndEmailAndApplicationStatus(null, null, null).size());
    }

    @Test
    public void testFindByOfferAndEmailAndApplicationStatusWithOnlyOffer() {
        assertEquals(2, applicationRepository.findByOfferAndEmailAndApplicationStatus("Sr. Developer", null, null).size());
    }
}
