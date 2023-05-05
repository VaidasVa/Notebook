package pro.vaidas.notebookserver.business.repository.impl;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDAO {

    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Note title is a mandatory field.")
    @NotNull
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;

    @PrePersist
    protected void prePersist() {
        if (created == null) created = LocalDateTime.now();
        updated = LocalDateTime.now();
    }
}
