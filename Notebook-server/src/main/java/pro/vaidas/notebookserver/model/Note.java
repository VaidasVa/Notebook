package pro.vaidas.notebookserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note implements Serializable {

    private UUID id;
    private String title;
    private String content;
    private String userUUID;
    private LocalDateTime created;
    private LocalDateTime updated;
}
