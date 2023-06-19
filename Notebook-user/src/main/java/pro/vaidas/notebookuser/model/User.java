package pro.vaidas.notebookuser.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created;
    private Role role;

    private boolean notExpired;
    private boolean notLocked;
    private boolean credentialsNotExpired;
    private boolean enabled;

}
