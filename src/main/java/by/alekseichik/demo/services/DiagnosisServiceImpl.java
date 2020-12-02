package by.alekseichik.demo.services;

import by.alekseichik.demo.model.Diagnosis;
import by.alekseichik.demo.model.User;
import by.alekseichik.demo.repository.DiagnosisRepository;
import by.alekseichik.demo.repository.UserRepository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class DiagnosisServiceImpl implements DiagnosisService {

    public final UserRepository userRepository;
    public final DiagnosisRepository diagnosisRepository;

    public DiagnosisServiceImpl(UserRepository userRepository, DiagnosisRepository diagnosisRepository) {
        this.userRepository = userRepository;
        this.diagnosisRepository = diagnosisRepository;
    }


    @Override
    public void addDiagnosis(String d, String patientEmail, String doctorEmail) {
        PatientServiceImpl patientService = new PatientServiceImpl(userRepository);
        User patient =  patientService.getUser(patientEmail);
        User doctor = patientService.getUser(doctorEmail);
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setPatientId(patient.getId());
        diagnosis.setDoctorId(doctor.getId());
        diagnosis.setDiagnosis(d);

        diagnosisRepository.save(diagnosis);
    }
}
