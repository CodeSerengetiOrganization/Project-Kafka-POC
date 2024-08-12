package com.mytech.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.mytech")
public class StringMessageProducerConfig {

    @Bean
    public KafkaTemplate<String, String> producerTemplateString(){
        return new KafkaTemplate<>(producerFactoryString());
    }

    @Bean
    public ProducerFactory<String, String> producerFactoryString() {

        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

/*        //use class alos works
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);*/



        return new DefaultKafkaProducerFactory<>(configs);
    }


/*    private static Properties producerProperty;
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
    }*/

}
