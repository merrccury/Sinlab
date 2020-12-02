package by.alekseichik.demo.dto;

import lombok.Data;

@Data
public class DiagnosisRequestDto {
    private String diagnosis;
    private String doctorEmail;
    private String patientEmail;

}
