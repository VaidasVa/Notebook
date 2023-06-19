package pro.vaidas.notebookclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private UUID id;
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;

    // todo add FK to connect to user
}
