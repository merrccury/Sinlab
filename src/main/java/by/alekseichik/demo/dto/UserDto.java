package by.alekseichik.demo.dto;

import by.alekseichik.demo.model.Role;
import by.alekseichik.demo.model.Status;
import by.alekseichik.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private Status status;

    private List<DiagnosesDto> diagnosis = new ArrayList<>();

    public UserDto(){}

    public UserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
        this.status = user.getStatus();
        user.getDiagnosis().forEach(diagnosis1 -> {
            diagnosis.add(new DiagnosesDto(diagnosis1));
        });
    }
}
