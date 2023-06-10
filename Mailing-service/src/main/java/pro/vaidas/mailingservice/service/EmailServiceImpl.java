package pro.vaidas.mailingservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pro.vaidas.mailingservice.model.ReceivedKafkaMessage;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);

    private final String SENDER_EMAIL = "notebookmailer@gmail.com";
    private final String SENDER_NAME = "Notebook App";

    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendMail(ReceivedKafkaMessage msg) throws MessagingException, IOException {

        Context context = new Context();
        context.setVariable("msg", msg);

        String process = templateEngine.process("newUserEmail", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper email = new MimeMessageHelper(mimeMessage);

        email.setFrom(SENDER_EMAIL, SENDER_NAME);
        email.setTo(msg.getEmail());

        String event = msg.getEventType();
        switch (event) {
            case "newUser" -> {
                email.setSubject("Welcome " + msg.getName());
                email.setText(process, true);
            }
            case "newNote" -> {
                email.setSubject(msg.getName() + " created");
                email.setText("Hi, your note has been saved!", false);
            }
        }

        javaMailSender.send(mimeMessage);

        logger.info(LocalDateTime.now() + " - email sent to : " + msg.getEmail() + ", message : " + mimeMessage.getContent());
    }
}
