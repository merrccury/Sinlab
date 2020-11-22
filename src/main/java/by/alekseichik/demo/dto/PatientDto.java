package by.alekseichik.demo.dto;

import by.alekseichik.demo.model.Diagnosis;
import by.alekseichik.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PatientDto {
    Long id;
    String firstName;
    String lastName;
    List<DiagnosesDto> diagnosis = new ArrayList<>();

    public PatientDto(User user){
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        user.getDiagnosis().forEach(diagnosis1 -> {
            diagnosis.add(new DiagnosesDto(diagnosis1));
        });
    }
}
