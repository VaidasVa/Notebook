package pro.vaidas.mailingservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pro.vaidas.mailingservice.model.Mail;
import pro.vaidas.mailingservice.model.MessageFromKafka;
import pro.vaidas.mailingservice.service.SendMailService;

import java.util.Objects;

@Component
public class KafkaConsumer {

    @Autowired
    private SendMailService mailService;

    @KafkaListener(topics = "NotebookUserServiceTopic",
            groupId = "group_id")

    // Method
    public void
    consume(String message) throws JsonProcessingException {
        // Print statement
        System.out.println("message = " + message);

        ObjectMapper mapper = new ObjectMapper();
        MessageFromKafka msg = mapper.readValue(message, MessageFromKafka.class);

        System.out.println(msg);

        if (Objects.equals(msg.getEventType(), "newUser")){
            Mail mail = Mail.builder()
                    .recipient(msg.getEmail())
                    .subject("User created in Notebook App")
                    .message("Your user has been created.")
                    .build();
            mailService.sendMail(mail);
        }
    }
}