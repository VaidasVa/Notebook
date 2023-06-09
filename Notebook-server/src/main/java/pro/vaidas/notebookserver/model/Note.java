package pro.vaidas.notebookserver.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private UUID id;
    private String title;
    private String content;
    private String userUUID;
    private LocalDateTime created;
    private LocalDateTime updated;

}
