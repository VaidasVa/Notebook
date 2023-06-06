package pro.vaidas.notebookserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaMessage {

    private String email;
    private String name;
    private String eventType;

    // todo add user when implemented

}
