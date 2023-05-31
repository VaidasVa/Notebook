package pro.vaidas.mailingservice.service;


import jakarta.mail.MessagingException;
import pro.vaidas.mailingservice.model.Mail;

import java.io.UnsupportedEncodingException;

public interface SendMailService {
    void sendMail(Mail mail);

    void sendMailWithAttachments(Mail mail) throws MessagingException, UnsupportedEncodingException;

    void sendEmailOnNewNote(Mail mail) throws MessagingException;
}
