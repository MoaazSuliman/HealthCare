package com.moaaz.health.repository;

import com.moaaz.health.model.DoctorRegisterRequest;
import com.moaaz.health.model.enums.RegisterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRegisterRepository extends JpaRepository<DoctorRegisterRequest , Long> {
    public List<DoctorRegisterRequest> findAllByRegisterStatus(RegisterStatus waiting);
}
