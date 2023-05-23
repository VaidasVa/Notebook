package pro.vaidas.notebookserver.config.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {

    public static void consume() {

        Logger logger = LoggerFactory.getLogger(Consumer.class.getName());

        String groupId = "javaaap";
        String topic = "atopic";

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                logger.info("\nValue : " + record.value() +
                        ", \nOffset : " + record.offset() +
                        ", \nPartition: " + record.partition());
            }
        }

        // assign and seek - to reply data or fetch a specific message
        /*
        TopicPartition partitionRead = new TopicPartition(topic, 0);
        long offsetToReadFrom = 15L;

        consumer.assign(List.of(partitionRead));
        consumer.seek(partitionRead, offsetToReadFrom);

        int messagesAmountToRead = 5;
        boolean keepOnReading = true;
        int noOfReadMsg = 0;
        // poll for new data
        while (keepOnReading) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records){
                noOfReadMsg += 1;
                logger.info("Key :" + record.key() +
                        ", \nValue : " + record.value() +
                        ", \nOffset : " + record.offset() +
                        ", \nPartition: " + record.partition());
                if (noOfReadMsg >= messagesAmountToRead){ keepOnReading = false; break;};
                Thread.sleep(2000);
*/
    }
}