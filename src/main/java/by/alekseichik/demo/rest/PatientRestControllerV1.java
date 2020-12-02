package by.alekseichik.demo.rest;

import by.alekseichik.demo.dto.UserDto;
import by.alekseichik.demo.repository.UserRepository;
import by.alekseichik.demo.security.UserDetailsServesImpl;
import by.alekseichik.demo.services.PatientServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientRestControllerV1 {

    private final UserRepository userRepository;

    final static Logger logger = Logger.getLogger(PatientRestControllerV1.class);

    public PatientRestControllerV1(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<UserDto> getUsers() {
        PatientServiceImpl service = new PatientServiceImpl(userRepository);
        List<UserDto> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            users.add(new UserDto(user));
        });
        logger.info("/api/v1/auth/all[GET] -> getting all users");
        return users;
    }

    @GetMapping("ban/{e}")
    public void banUser(@PathVariable String e) {
        PatientServiceImpl service = new PatientServiceImpl(userRepository);
        logger.info("/api/v1/auth/ban" + e + " [GET] -> banned user: " + e);
        service.banStatus(e);
    }

    @GetMapping("active/{e}")
    public void activeUser(@PathVariable String e) {
        PatientServiceImpl service = new PatientServiceImpl(userRepository);
        logger.info("/api/v1/auth/active" + e + " [GET] -> active user: " + e);
        service.activeStatus(e);
    }

    @GetMapping("/{e}")
    public UserDto getUserByEmail(@PathVariable String e) {
        PatientServiceImpl service = new PatientServiceImpl(userRepository);
        logger.info("/api/v1/auth/patients" + e + " [GET] -> getting User: " + e);
        return new UserDto(service.getUser(e));
    }


}
