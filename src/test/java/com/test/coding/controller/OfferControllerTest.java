package com.test.coding.controller;


import com.test.coding.entity.Offer;
import com.test.coding.repository.ApplicationRepository;
import com.test.coding.repository.OfferRepository;
import com.test.coding.service.ApplicationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationService applicationService;

    private static final String APPLICATION_JSON = "application/json";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @DirtiesContext
    public void createOffer() throws Exception {
        mockMvc.perform(post("/v1/offer")
                .contentType(APPLICATION_JSON)
                .content("{\"jobTitle\": \"dev\", \"startDate\": \"10/02/2019\"}"))
                .andExpect(status().is2xxSuccessful());

        assertEquals(1, offerRepository.findAll().size());

    }

    @Test
    @DirtiesContext
    public void getOffer() throws Exception {
        Offer offer = new Offer("Sr. Developer", LocalDate.of(2019, 03, 24));
        offerRepository.save(offer);

        mockMvc.perform(get("/v1/offer/Sr. Developer"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    @DirtiesContext
    public void getAllOffer() throws Exception {

        Offer offer1 = new Offer("Sr. Developer", LocalDate.of(2019, 03, 24));
        Offer offer2 = new Offer("Developer", LocalDate.of(2019, 03, 24));

        offerRepository.save(offer1);
        offerRepository.save(offer2);

        mockMvc.perform(get("/v1/offer"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(2)));

    }
}
