package pro.vaidas.notebookuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
        (exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class NotebookUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotebookUserApplication.class, args);
    }
}
