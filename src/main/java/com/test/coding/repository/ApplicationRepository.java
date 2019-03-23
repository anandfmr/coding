package com.test.coding.repository;

import com.test.coding.entity.Application;
import com.test.coding.entity.ApplicationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationCode> {
}
