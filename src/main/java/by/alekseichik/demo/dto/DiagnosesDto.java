package by.alekseichik.demo.dto;

import by.alekseichik.demo.model.Diagnosis;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class DiagnosesDto {
    private Long id;
    private Long patientId;
    private String diagnosis;
    private Date dateOfVisit;
    private Long doctorId;
    List<OrderDto> orders = new ArrayList<>();


    public DiagnosesDto(Diagnosis diagnosis){
        id = diagnosis.getId();
        patientId = diagnosis.getPatientId();
        this.diagnosis = diagnosis.getDiagnosis();
        dateOfVisit = diagnosis.getDateOfVisit();
        doctorId = diagnosis.getDoctorId();
        diagnosis.getOrders().forEach(order -> {
            orders.add(new OrderDto(order));
        });
    }

}



