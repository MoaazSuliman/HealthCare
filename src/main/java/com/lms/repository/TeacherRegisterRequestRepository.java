package com.lms.repository;

import com.lms.model.TeacherRegisterRequest;
import com.lms.model.enums.RegisterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRegisterRequestRepository extends JpaRepository<TeacherRegisterRequest, Long> {
    public List<TeacherRegisterRequest> findAllByRegisterStatus(RegisterStatus waiting);
}
