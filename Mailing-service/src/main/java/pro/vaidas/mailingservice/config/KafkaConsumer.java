package pro.vaidas.mailingservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import pro.vaidas.mailingservice.model.ReceivedKafkaMessage;
import pro.vaidas.mailingservice.service.EmailService;

import java.io.IOException;

@Component
@AllArgsConstructor
public class KafkaConsumer {

    private EmailService mailService;

    @KafkaListener(topics = {"NotebookUserServiceTopic", "NotebookServerTopic"}, groupId = "notebook_group")
    public void consume(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws IOException, MessagingException {
        ObjectMapper mapper = new ObjectMapper();

                ReceivedKafkaMessage msg = mapper.readValue(message, ReceivedKafkaMessage.class);
                mailService.sendMail(msg);
        }
    }
