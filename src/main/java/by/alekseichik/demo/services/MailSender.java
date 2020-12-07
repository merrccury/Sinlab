package by.alekseichik.demo.services;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

@Service
public interface MailSender {
    void send(String emailTo, String subject, String message)throws ServiceException;
}
