package by.alekseichik.demo.rest;

import by.alekseichik.demo.dto.AuthenticationRequestDto;
import by.alekseichik.demo.dto.RegistrationRequestDto;
import by.alekseichik.demo.dto.UserDto;
import by.alekseichik.demo.exception.ApiAuthException;
import by.alekseichik.demo.model.User;
import by.alekseichik.demo.repository.UserRepository;
import by.alekseichik.demo.security.JwtTokenProvider;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationRestControllerV1 {

    final static Logger logger = Logger.getLogger(AuthenticationRestControllerV1.class);

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            logger.info("/api/v1/auth/login [POST] -> User: " + user.getEmail() + " is authenticate");
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            logger.error("/api/v1/auth/login [POST] -> Invalid email/password combination: " + HttpStatus.FORBIDDEN.toString());
            throw new ApiAuthException("Invalid password or email");
        }
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        logger.info("/api/v1/auth/logout [POST] -> user logout");
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }


    @PostMapping("/registration")
    public ResponseEntity<?> signUpUser(@RequestBody RegistrationRequestDto dto) {
        User user = new User(dto);
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", user.getEmail());
            response.put("token", token);
            logger.info("/api/v1/auth/registration [POST] -> user registration");
            return ResponseEntity.ok(response);
        }
        throw new ApiAuthException("Email already exist");
    }

    @GetMapping("/about/{email}")
    public UserDto aboutUser(@PathVariable String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        logger.info("/api/v1/auth/about" + email + " [POST] -> user registration");
        return new UserDto(user);
    }
}
