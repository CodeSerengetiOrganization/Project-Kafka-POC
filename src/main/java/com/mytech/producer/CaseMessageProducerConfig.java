package com.mytech.producer;

import com.mytech.model.CaseNew;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;


import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.mytech")
public class CaseMessageProducerConfig {

    @Bean
    public KafkaTemplate<String, CaseNew> producerTemplateCase(){
        return new KafkaTemplate<>(producerFactoryCase());
    }

    @Bean
    public ProducerFactory<String, CaseNew> producerFactoryCase() {

        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
//        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.J");
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(JsonDeserializer.TRUSTED_PACKAGES, "com.mytech.entity");

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
