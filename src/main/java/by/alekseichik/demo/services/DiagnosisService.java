package by.alekseichik.demo.services;

import by.alekseichik.demo.dto.DiagnosesDto;
import by.alekseichik.demo.dto.DiagnosesRequestDto;

import java.util.List;

public interface DiagnosisService {
    List<DiagnosesDto> getUsersDiagnosis(Long UserId);

    DiagnosesDto getDiagnoses(Long diagnosesId);

    void createDiagnoses (DiagnosesRequestDto dto);

}
