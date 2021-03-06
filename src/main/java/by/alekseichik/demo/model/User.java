package by.alekseichik.demo.model;

import by.alekseichik.demo.dto.RegistrationRequestDto;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany
    @JoinColumn(name = "patient_id")
    private Set<Diagnosis> diagnosis;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;



    public User(){}

    public User(RegistrationRequestDto dto){
        email = dto.getEmail();
        password = new BCryptPasswordEncoder().encode(dto.getPassword());
        firstName = dto.getFirstName();
        lastName = dto.getLastName();
        role = Role.PATIENT;
        status = Status.ACTIVE;
    }
}
