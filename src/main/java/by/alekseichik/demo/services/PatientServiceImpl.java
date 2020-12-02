package by.alekseichik.demo.services;

import by.alekseichik.demo.exception.ApiAuthException;
import by.alekseichik.demo.model.Status;
import by.alekseichik.demo.model.User;
import by.alekseichik.demo.repository.UserRepository;

public class PatientServiceImpl implements PatientService{

    private final UserRepository userRepository;

    public PatientServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getUser(String email) {
        if(!userRepository.findByEmail(email).isEmpty()){
            return userRepository.findByEmail(email).get();
        }
        throw new ApiAuthException("User Not found");
    }

    @Override
    public void banStatus(String email) {
        if(!userRepository.findByEmail(email).isEmpty()) {
            User user = userRepository.findByEmail(email).get();
            user.setStatus(Status.BANNED);
            userRepository.save(user);
        }
    }

    @Override
    public void activeStatus(String email) {
        if(!userRepository.findByEmail(email).isEmpty()) {
            User user = userRepository.findByEmail(email).get();
            user.setStatus(Status.ACTIVE);
            userRepository.save(user);
        }
    }
}
