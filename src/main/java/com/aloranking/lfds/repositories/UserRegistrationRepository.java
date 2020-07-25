package com.aloranking.lfds.repositories;


import com.aloranking.lfds.models.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {
}
