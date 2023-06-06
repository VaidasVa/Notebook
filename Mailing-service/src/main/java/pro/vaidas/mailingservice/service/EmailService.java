package pro.vaidas.mailingservice.service;


import jakarta.mail.MessagingException;
import pro.vaidas.mailingservice.model.ReceivedKafkaMessage;

import java.io.IOException;

public interface EmailService {

    void sendMail(ReceivedKafkaMessage msg) throws MessagingException, IOException;
}
