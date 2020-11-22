package by.alekseichik.demo.services;

import by.alekseichik.demo.dto.PatientDto;
import by.alekseichik.demo.dto.RegistrationDequestDto;
import by.alekseichik.demo.model.User;
import by.alekseichik.demo.repository.UserRepository;

public class PatientServiceImpl implements PatientService {

    private final UserRepository userRepository;

    public PatientServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public PatientDto getHistory(Long id) {
        if (userRepository.findById(id).isEmpty())
            return null;

        return new PatientDto(userRepository.findById(id).get());
    }

    @Override
    public PatientDto addPatient(RegistrationDequestDto dto) {
        User user  = new User(dto);
        if (userRepository.findById(user.getId()).isEmpty()){
            userRepository.save(user);
            return  new PatientDto(user);
        }
        return null;
    }
}
