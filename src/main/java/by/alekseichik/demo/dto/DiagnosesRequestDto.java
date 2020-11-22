package by.alekseichik.demo.dto;

import lombok.Data;

import javax.persistence.Column;
import java.sql.Date;

@Data
public class DiagnosesRequestDto {

    private Long patient;
    private String diagnosis;
    private Long doctor;
}
