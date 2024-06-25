package com.thinkify.EventRegistrationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinkify.EventRegistrationSystem.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}