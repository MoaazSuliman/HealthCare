package com.lms.service;

import com.lms.model.Teacher;
import com.lms.dto.TeacherRequest;
import com.lms.dto.TeacherResponse;
import com.lms.model.TeacherRegisterRequest;
import com.lms.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TeacherLevelService teacherLevelService;
    @Autowired
    private OtpService otpService;

    public Teacher createTeacher(TeacherRequest teacherRequest) {
        // create Teacher
        Teacher teacher = Teacher.convertTeacherRegisterRequestToTeacher(teacherRequest);
        // assign Levels To Teachers
        teacherLevelService.assignLevelsToTeacher(teacher , teacherRequest.getLevels());
        // set authentication
        teacher.setAuthentication(authenticationService.createAuthentication(teacherRequest.getEmail(), teacherRequest.getPassword()));

        return teacherRepository.save(teacher);
    }


    public Teacher getByEmail(String email) {
        return teacherRepository.findByAuthenticationEmail(email).orElse(null);
    }

    public Teacher login(String email, String password) {
        return teacherRepository.findByAuthenticationEmailAndAuthenticationPassword(email, password).orElse(null);
    }

    public Teacher getById(long id) {
        return teacherRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("This Id Are Not In Our database.")
        );
    }

    // this didn't update email and password.
    public Teacher update(long teacherId, TeacherRequest teacherRequest) {
        Teacher existingTeacher = getById(teacherId);
        existingTeacher.setName(teacherRequest.getName());
        existingTeacher.setAddress(teacherRequest.getAddress());
        existingTeacher.setSubject(teacherRequest.getSubject());
        existingTeacher.setPhone(teacherRequest.getPhone());
        existingTeacher.setWhatsappNumber(existingTeacher.getWhatsappNumber());
        // assign levels to teacher
        teacherLevelService.assignLevelsToTeacher(existingTeacher, teacherRequest.getLevels());

        return teacherRepository.save(existingTeacher);
    }

    public void deleteById(long id) {
        getById(id);//to check if exist or throw exception.
        teacherRepository.deleteById(id);
    }

    public void justUpdate(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public List<TeacherResponse> getAll() {
        return teacherRepository.findAll().stream().map(TeacherResponse::convertEntityToResponse).toList();
    }

    public void createTeacherFromTeacherRequest(TeacherRegisterRequest teacherRegisterRequest) {
        //TODO Take Teacher Register Request And Convert It To Teacher.
    }
}
