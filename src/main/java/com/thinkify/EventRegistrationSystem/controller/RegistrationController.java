package com.thinkify.EventRegistrationSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinkify.EventRegistrationSystem.entity.Event;
import com.thinkify.EventRegistrationSystem.entity.Registration;
import com.thinkify.EventRegistrationSystem.entity.User;
import com.thinkify.EventRegistrationSystem.exception.ResourceNotFoundException;
import com.thinkify.EventRegistrationSystem.repository.EventRepository;
import com.thinkify.EventRegistrationSystem.repository.RegistrationRepository;
import com.thinkify.EventRegistrationSystem.repository.UserRepository;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @PostMapping
    public Registration createRegistration(@RequestBody Registration registration) {
        User user = userRepository.findById(registration.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Event event = eventRepository.findById(registration.getEvent().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        registration.setUser(user);
        registration.setEvent(event);

        return registrationRepository.save(registration);
    }

    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Registration getRegistrationById(@PathVariable Long id) {
        return registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found"));

        registrationRepository.delete(registration);
        return ResponseEntity.noContent().build();
    }
}
