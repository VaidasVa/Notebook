package pro.vaidas.notebookuser.repository.impl;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import pro.vaidas.notebookuser.model.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserDAO {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false)
    private UUID id;

    @NotBlank(message = "Name is a mandatory field.")
    @NotNull
    private String name;

    @NotBlank(message = "Email is a mandatory field.")
    @NotNull
    private String email;

    @NotBlank(message = "Password is a mandatory field.")
    @NotNull
    private String password;

    @CreationTimestamp
    private LocalDateTime created;

    private boolean notExpired = true;
    private boolean notLocked = true;
    private boolean credentialsNotExpired = true;
    private boolean enabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;

}