package pro.vaidas.kafkaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.vaidas.kafkaservice.config.Producer;

import java.io.IOException;

@SpringBootApplication
public class KafkaServiceApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(KafkaServiceApplication.class, args);
		Producer.produce();
    }
}