package com.moaaz.health.repository;

import com.moaaz.health.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient , Long> {
    public Optional<Patient> findByAuthenticationEmail(String email);

    public Optional<Patient> findByAuthenticationEmailAndAuthenticationPassword(String email, String password);
}
