package by.alekseichik.demo.services;

import by.alekseichik.demo.dto.UserRequestDto;
import by.alekseichik.demo.model.User;
import by.alekseichik.demo.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserRequestDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).get();
        return new UserRequestDto(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getRole());
    }
}
