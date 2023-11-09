package com.lms.repository;

import com.lms.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Teacher, Long> {
    public Optional<Teacher> findByAuthenticationEmail(String email);

    public Optional<Teacher> findByAuthenticationEmailAndAuthenticationPassword(String email, String password);
}
