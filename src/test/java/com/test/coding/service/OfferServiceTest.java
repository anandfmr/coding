package com.test.coding.service;


import com.test.coding.entity.Offer;
import com.test.coding.repository.OfferRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class OfferServiceTest {

    @InjectMocks
    private OfferService offerService;

    @Mock
    private OfferRepository offerRepository;

    Offer offer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        offer = new Offer("Sr. Developer", LocalDate.now());
    }

    @Test
    public void testFindByIdWithEmpty() {
        when(offerRepository.findById(any())).thenReturn(Optional.empty());

        assertEquals(Optional.empty(), offerService.fetchOfferByJobTitle(any()));

    }

    @Test
    public void testFindByIdWithOffer() {
        when(offerRepository.findById(any())).thenReturn(Optional.of(offer));

        assertEquals(Optional.of(offer), offerService.fetchOfferByJobTitle(any()));
    }

    @Test
    public void testFetchAll() {
        when(offerRepository.findAll()).thenReturn(Arrays.asList(offer));

        assertEquals(1, offerService.fetchAll().size());
    }
}
