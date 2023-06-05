package pro.vaidas.notebookuser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class KafkaMessageFromUser {

    private String email;
    private String name;
    private String eventType;
}

