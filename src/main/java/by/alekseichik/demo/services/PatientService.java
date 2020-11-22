package by.alekseichik.demo.services;

import by.alekseichik.demo.dto.PatientDto;
import by.alekseichik.demo.dto.RegistrationDequestDto;

public interface PatientService {
    PatientDto getHistory(Long id);
    PatientDto addPatient(RegistrationDequestDto dto);
}
