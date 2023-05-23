package pro.vaidas.kafkaservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.vaidas.kafkaservice.model.Quote;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Properties;

public class Producer {

    public static void produce() throws InterruptedException, IOException  {

        Logger logger = LoggerFactory.getLogger(Producer.class);

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        for (int i = 0; i < 10; i++) {

            String topic = "atopic";

            URL url = new URL("https://dummyjson.com/quotes/random");
            ObjectMapper mapper = new ObjectMapper();
            Quote obj = mapper.readValue(url, Quote.class);

            String value = LocalDateTime.now() + " " + obj.getQuote();

            KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, value);

            // send data - async!!!
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    logger.info("\n" + "\n" +
                            "Received new Metadata: \n" +
                            "Timestamp: " + metadata.timestamp() + "\n" +
                            "Topic: " + metadata.topic() + "\n" +
                            "Partition: " + metadata.partition() + "\n" +
                            "Offset: " + metadata.offset() + "\n");}
                else { logger.error("Error: " + exception);}
            });
            Thread.sleep(2000);

            //flush and close
            producer.flush();
            producer.close();
        }
    }
}
