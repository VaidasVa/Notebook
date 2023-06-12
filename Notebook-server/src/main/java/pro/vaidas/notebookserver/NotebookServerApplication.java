package pro.vaidas.notebookserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class NotebookServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotebookServerApplication.class, args);
    }
}
