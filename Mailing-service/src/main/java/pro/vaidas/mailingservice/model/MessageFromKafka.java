package pro.vaidas.mailingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageFromKafka {
    private String email;
    private String name;
    private String eventType;
}
