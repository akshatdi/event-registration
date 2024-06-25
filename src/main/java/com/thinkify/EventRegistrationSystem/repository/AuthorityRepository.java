package com.thinkify.EventRegistrationSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinkify.EventRegistrationSystem.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	List<Authority> findByUsername(String username);
}
