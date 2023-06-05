package pro.vaidas.notebookuser.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import pro.vaidas.notebookuser.model.User;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    // Works, but let's try another way
//    public static void produce(User user) {
//        Properties properties = new Properties();
//        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Object.class.getName());
//
//        String topic = "NotebookUserServiceTopic";
//
//        KafkaProducer<String, User> producer = new KafkaProducer<>(properties);
//        ProducerRecord<String, User> message = new ProducerRecord<>(topic, user);
//
//       producer.send(message, ((metadata, exception) -> {
//                if (exception == null) {
//                    System.out.println("Kafka Producer message : " + message);
//                } else {
//                    System.out.println("Kafka has en error in producer :" + exception);
//                }
//            }));
//       producer.flush();
//       producer.close();
//    }

    public ProducerFactory<String, User> producerFactory()
    {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate kafkaTemplate()
    {
        return new KafkaTemplate<>(producerFactory());
    }
}
