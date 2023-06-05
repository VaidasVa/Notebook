package pro.vaidas.mailingservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pro.vaidas.mailingservice.model.Mail;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class SendMailServiceImpl implements SendMailService {

    private final JavaMailSender javaMailSender;
    private final String senderEmail = "notebookmailer@gmail.com";
    private final String senderName = "Notebook App";

    private static final Logger logger = LogManager.getLogger(SendMailServiceImpl.class);

    public SendMailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(Mail mail) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(senderEmail);
        msg.setTo(mail.getRecipient());
        msg.setSubject(mail.getSubject());
        msg.setText(mail.getMessage());

        javaMailSender.send(msg);
        logger.info(LocalDateTime.now() + " - email sent to : " + mail.getRecipient() + " with subject : "
                + mail.getSubject() + ", and body : " + mail.getMessage());
    }

    @Override
    public void sendMailWithAttachments(Mail mail) throws MessagingException, UnsupportedEncodingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom(senderEmail, senderName);
        helper.setBcc(senderEmail);
        helper.setTo(mail.getRecipient());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getMessage() + "\nFind the attached image", true);
        helper.addAttachment("403.jpg", new ClassPathResource("static/403.jpg"));

        javaMailSender.send(msg);
        logger.info(LocalDateTime.now() + " - email with picture sent to : " + mail.getRecipient() + " with subject : "
                + mail.getSubject() + ", and body : " + mail.getMessage());
    }

    @Override
    public void sendEmailOnNewNote(Mail mail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getRecipient());
        msg.setSubject("New note");
        msg.setText("A new note has been created");

        javaMailSender.send(msg);
        logger.info(LocalDateTime.now() + " - email sent to : " + mail.getRecipient() + " because new note was created");
    }
}
