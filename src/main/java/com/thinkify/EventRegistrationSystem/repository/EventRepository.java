package com.thinkify.EventRegistrationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinkify.EventRegistrationSystem.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}