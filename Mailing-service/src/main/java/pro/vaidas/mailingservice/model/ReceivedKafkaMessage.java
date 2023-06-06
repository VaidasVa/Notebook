package pro.vaidas.mailingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceivedKafkaMessage {
    private String email;
    private String name;
    private String eventType;
}
