package pro.vaidas.notebookuser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mail {
    private String recipient;
    private String subject;
    private String message;
}
