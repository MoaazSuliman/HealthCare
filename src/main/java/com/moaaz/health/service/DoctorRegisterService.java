package com.moaaz.health.service;

import com.moaaz.health.model.Doctor;
import com.moaaz.health.model.DoctorRegisterRequest;
import com.moaaz.health.model.enums.RegisterStatus;
import com.moaaz.health.repository.DoctorRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DoctorRegisterService {

    @Autowired
    private DoctorRegisterRepository doctorRegisterRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private MailService mailService;


    // save the doctor register request
    public DoctorRegisterRequest createNewRegisterRequestForDoctor(DoctorRegisterRequest doctorRegisterRequest) {
        doctorRegisterRequest.setRegisterStatus(RegisterStatus.WAITING);
        doctorRegisterRequest.setCardImage(imageService.saveImageToFolder(doctorRegisterRequest.getCardImage()));
        mailService.sendMessage(doctorRegisterRequest.getEmail(), "Your Request Has Been Submitted  , We Will See It And Answer Soon , Thanks For Using Our Health Care ");
        return doctorRegisterRepository.save(doctorRegisterRequest);

    }

    // accept doctor register request
    public DoctorRegisterRequest acceptDoctorRequest(long requestId) {
        // accept order.
        DoctorRegisterRequest doctorRegisterRequest = getById(requestId);
        doctorRegisterRequest.setRegisterStatus(RegisterStatus.ACCEPTED);
        doctorService.createDoctor(doctorRegisterRequest);

        mailService.sendMessage(doctorRegisterRequest.getEmail(), "Your Register Had Been Accepted Doctor ==> Go Now And Verify Your Otp");
        return doctorRegisterRepository.save(doctorRegisterRequest);

        // create doctor and save it in the database


    }

    // refuse the doctor register request

    public DoctorRegisterRequest rejectDoctorRequest(long requestId, String whyRejectedMessage) {
        // accept order.
        DoctorRegisterRequest doctorRegisterRequest = getById(requestId);
        doctorRegisterRequest.setRegisterStatus(RegisterStatus.REJECTED);

        mailService.sendMessage(doctorRegisterRequest.getEmail(), whyRejectedMessage);
        return doctorRegisterRepository.save(doctorRegisterRequest);


    }

    // get all waiting  registers
    public List<DoctorRegisterRequest> getAllRegistersThatAreInWaiting() {
        return doctorRegisterRepository.findAllByRegisterStatus(RegisterStatus.WAITING);
    }


    // get by id
    public DoctorRegisterRequest getById(long requestId) {
        return doctorRegisterRepository.findById(requestId).orElseThrow(
                () -> new NoSuchElementException("There Are No Doctor Register Request with id = " + requestId)
        );
    }
}
