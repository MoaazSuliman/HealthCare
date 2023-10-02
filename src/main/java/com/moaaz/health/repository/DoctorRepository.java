package com.moaaz.health.repository;

import com.moaaz.health.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    public Optional<Doctor> findByAuthenticationEmail(String email);

    public Optional<Doctor> findByAuthenticationEmailAndAuthenticationPassword(String email, String password);
}
