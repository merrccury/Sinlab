package by.alekseichik.demo.services;

import by.alekseichik.demo.dto.AuthenticationRequestDto;
import by.alekseichik.demo.dto.UserRequestDto;
import by.alekseichik.demo.model.Diagnosis;

import java.util.List;

public interface UserService {

    UserRequestDto getUserByEmail(String email);
}
