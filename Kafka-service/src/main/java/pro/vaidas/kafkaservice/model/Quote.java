package pro.vaidas.kafkaservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    private int id;
    private String quote;
    private String author;

}
