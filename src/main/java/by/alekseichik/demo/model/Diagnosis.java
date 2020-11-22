package by.alekseichik.demo.model;

import by.alekseichik.demo.dto.DiagnosesRequestDto;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "users_diagnosis")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "diagnoses")
    private String diagnosis;

    @Column(name = "date_of_visit")
    private Date dateOfVisit;

    @Column(name = "user_id")
    private Long doctorId;

    @OneToMany
    @JoinColumn(name = "diagnosis_id")
    private Set<Order> orders;

    public  Diagnosis(){}

    public Diagnosis (DiagnosesRequestDto dto){
        patientId = dto.getPatient();
        diagnosis = dto.getDiagnosis();
        dateOfVisit = new Date(System.currentTimeMillis());
        doctorId = dto.getDoctor();
    }

}
