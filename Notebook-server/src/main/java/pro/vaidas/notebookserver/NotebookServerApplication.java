package pro.vaidas.notebookserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.vaidas.notebookserver.config.kafka.Consumer;

@SpringBootApplication
public class NotebookServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotebookServerApplication.class, args);
        Consumer.consume();
    }
}
