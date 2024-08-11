package producer;

import java.util.Properties;

public class MessageProducerConfig {
    private static Properties producerProperty;
    // Step 2: Create a private static instance of the class
    private static MessageProducerConfig instance;
    // Step 1: Private constructor to prevent instantiation
    private MessageProducerConfig() {}

    // Step 3: Public method to provide access to the instance
    public static MessageProducerConfig getInstance() {
        if (instance == null) {
            synchronized (MessageProducerConfig.class) {
                if (instance == null) {
                    instance = new MessageProducerConfig();
                    instance.getProducerProperty();
                }
            }
        }
        return instance;
    }
//    public static final Properties configProperty=new Properties;

    public Properties getProducerProperty(){
        // Set up properties
        Properties props = new Properties();
        props.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Your Kafka server address
        props.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

}
