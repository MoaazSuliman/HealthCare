package com.moaaz.health.repository;

import com.moaaz.health.model.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication , Long> {
}
