package com.test.coding.repository;

import com.test.coding.entity.Application;
import com.test.coding.entity.ApplicationCode;
import com.test.coding.model.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationCode> {

    @Query("SELECT app " +
            "FROM Application app " +
            "WHERE (:offer is null or app.code.offer = :offer) " +
            "AND (:email is null or app.code.candidateEmail = :email) " +
            "AND (:applicationStatus is null or app.applicationStatus = :applicationStatus)")
    List<Application> findByOfferAndEmailAndApplicationStatus(@Param("offer") String offer, @Param("email") String email,
                                                              @Param("applicationStatus") ApplicationStatus applicationStatus);
}
