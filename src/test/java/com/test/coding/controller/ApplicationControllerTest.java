package com.test.coding.controller;


import com.test.coding.entity.Offer;
import com.test.coding.repository.ApplicationRepository;
import com.test.coding.repository.OfferRepository;
import com.test.coding.service.ApplicationService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationControllerTest {

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
        Offer offer = new Offer("Sr. Developer", LocalDate.of(2019, 03, 24));
        offerRepository.save(offer);
        mockMvc.perform(post("/v1/application")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"offer\": \"Sr. Developer\", \"candidateEmail\": \"aks@gmail.com\", \"resume\":\"Resume text here\"}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Applied Successfully"));

    }

    @Test
    @DirtiesContext
    public void testApply() throws Exception {

        assertEquals(1, applicationRepository.findAll().size());
    }

    @Test
    @DirtiesContext
    public void testGetApplicationByOffer() throws Exception {
        mockMvc.perform(get("/v1/application?offer=Sr. Developer"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    @DirtiesContext
    public void testApplicationProgress() throws Exception {
        mockMvc.perform(post("/v1/applicationProgress")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"offer\": \"Sr. Developer\", \"candidateEmail\": \"aks@gmail.com\", \"applicationStatus\":\"INVITED\"}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("INVITED"));
    }
}
