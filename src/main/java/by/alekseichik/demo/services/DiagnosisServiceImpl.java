package by.alekseichik.demo.services;

import by.alekseichik.demo.dto.DiagnosesDto;
import by.alekseichik.demo.dto.DiagnosesRequestDto;
import by.alekseichik.demo.dto.PatientDto;
import by.alekseichik.demo.model.Diagnosis;
import by.alekseichik.demo.repository.DiagnosisRepository;
import by.alekseichik.demo.repository.UserRepository;

import java.util.List;

public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final UserRepository userRepository;

    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, UserRepository userRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<DiagnosesDto> getUsersDiagnosis(Long userId) {
        if (userRepository.findById(userId).isEmpty())
            return null;
        PatientDto dto = new PatientDto(userRepository.findById(userId).get());
        return  dto.getDiagnosis();
    }

    @Override
    public DiagnosesDto getDiagnoses(Long diagnosesId) {
        if (diagnosisRepository.findById(diagnosesId).isEmpty())
            return null;
        DiagnosesDto dto = new DiagnosesDto(diagnosisRepository.findById(diagnosesId).get());
        return  dto;
    }


    @Override
    public void createDiagnoses(DiagnosesRequestDto dto) {

        diagnosisRepository.save(new Diagnosis(dto));

    }
}
