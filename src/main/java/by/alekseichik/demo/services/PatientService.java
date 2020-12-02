package by.alekseichik.demo.services;

import by.alekseichik.demo.model.User;

public interface PatientService {
    public User getUser(String email);
    public void banStatus(String email);
    public void activeStatus(String email);
}
