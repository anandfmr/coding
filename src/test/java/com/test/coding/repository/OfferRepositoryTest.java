package com.test.coding.repository;


import com.test.coding.entity.Offer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OfferRepositoryTest {

    @Autowired
    private OfferRepository offerRepository;

    @Before
    public void setUp() {
        Offer offer1 = new Offer("Sr. Developer", LocalDate.of(2019, 03, 24));
        Offer offer2 = new Offer("Developer", LocalDate.of(2019, 03, 24));
        offerRepository.save(offer1);
        offerRepository.save(offer2);
    }

    @Test
    public void testFindByJobTitle() {
        assertTrue(offerRepository.findById("Sr. Developer").isPresent());
    }

    @Test
    public void testFindAll() {
        assertEquals(2, offerRepository.findAll().size());
    }
}
