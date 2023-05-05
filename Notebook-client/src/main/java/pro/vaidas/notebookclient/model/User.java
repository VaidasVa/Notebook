package pro.vaidas.notebookclient.model;

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

    private String email;

    private String password;
}
