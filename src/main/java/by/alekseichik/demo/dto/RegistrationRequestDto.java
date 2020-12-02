package by.alekseichik.demo.dto;

import lombok.Data;

@Data
public class RegistrationRequestDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
