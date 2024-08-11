package producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class MessageProducer {
    public static void main(String[] args) {
        MessageProducer producer=new MessageProducer();
        producer.sendMessage();
    }

    public void sendMessage() {
        // Set up properties
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Your Kafka server address
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // Create Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            // Create a producer record
            ProducerRecord<String, String> record = new ProducerRecord<>("test-topic", "key", "value from POC");

            // Send record asynchronously
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception != null) {
                        exception.printStackTrace();
                    } else {
                        System.out.println("Record sent to partition " + metadata.partition() + " with offset " + metadata.offset());
                    }
                }
            });
        } finally {
            // Close the producer
            producer.close();
        }
    }
}
