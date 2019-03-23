package com.test.coding.repository;

import com.test.coding.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends JpaRepository<Offer,String> {
}
