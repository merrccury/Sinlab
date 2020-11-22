package by.alekseichik.demo.dto;

import by.alekseichik.demo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}
