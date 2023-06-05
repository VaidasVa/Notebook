package pro.vaidas.notebookuser.service;

import pro.vaidas.notebookuser.model.Mail;

import java.io.IOException;
import java.net.URISyntaxException;

public interface MailingService {

    String sendEmail(Mail mail) throws IOException, URISyntaxException;
}
