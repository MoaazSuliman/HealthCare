package com.lms.service;

import com.lms.service.files.FileService;
import com.lms.model.TeacherRegisterRequest;
import com.lms.model.enums.RegisterStatus;
import com.lms.repository.TeacherRegisterRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TeacherRegisterService {

    @Autowired
    private TeacherRegisterRequestRepository teacherRegisterRequestRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private MailService mailService;


    // save the Teacher register request
    public TeacherRegisterRequest createNewRegisterRequestForTeacher(TeacherRegisterRequest teacherRegisterRequest) {
        teacherRegisterRequest.setRegisterStatus(RegisterStatus.WAITING);
//        teacherRegisterRequest.setCardImage(fileService.saveImageToFolder(teacherRegisterRequest.getCardImage()));
        mailService.sendMessage(teacherRegisterRequest.getEmail(), "Your Request Has Been Submitted  , We Will See It And Answer Soon , Thanks For Using Our Health Care ");
        return teacherRegisterRequestRepository.save(teacherRegisterRequest);

    }

    // accept Teacher register request
    public TeacherRegisterRequest acceptTeacherRequest(long requestId) {
        // accept order.
        TeacherRegisterRequest teacherRegisterRequest = getById(requestId);
        teacherRegisterRequest.setRegisterStatus(RegisterStatus.ACCEPTED);
        teacherService.createTeacherFromTeacherRequest(teacherRegisterRequest);

        mailService.sendMessage(teacherRegisterRequest.getEmail(), "Your Register Had Been Accepted Teacher ==> Go Now And Verify Your Otp");
        return teacherRegisterRequestRepository.save(teacherRegisterRequest);

        // create Teacher and save it in the database


    }

    // refuse the Teacher register request

    public TeacherRegisterRequest rejectTeacherRequest(long requestId, String whyRejectedMessage) {
        // accept order.
        TeacherRegisterRequest teacherRegisterRequest = getById(requestId);
        teacherRegisterRequest.setRegisterStatus(RegisterStatus.REJECTED);

        mailService.sendMessage(teacherRegisterRequest.getEmail(), whyRejectedMessage);
        return teacherRegisterRequestRepository.save(teacherRegisterRequest);


    }

    // get all waiting  registers
    public List<TeacherRegisterRequest> getAllRegistersThatAreInWaiting() {
        return teacherRegisterRequestRepository.findAllByRegisterStatus(RegisterStatus.WAITING);
    }


    // get by id
    public TeacherRegisterRequest getById(long requestId) {
        return teacherRegisterRequestRepository.findById(requestId).orElseThrow(
                () -> new NoSuchElementException("There Are No Teacher Register Request with id = " + requestId)
        );
    }
}
