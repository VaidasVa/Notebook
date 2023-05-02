package pro.vaidas.notebook.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID id;

    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String password;

// todo add FK to connect to user
// todo created, updated, prepersist


}
