package com.mytech.producer;


import io.swagger.client.model.CaseNew;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.mytech.producer",excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = CaseMessageProducerConfig.class))
@PropertySource("classpath:kafkaproducerconfig.properties")
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    String bootstrapServers;

    //seem no need to create fields for 'spring.kafka.producer.key-serializer' and 'spring.kafka.producer.value-serializer'
    @Value("${spring.kafka.producer.key-serializer}")
    String keySerializer;
    
    @Value("${spring.kafka.producer.value-serializer}")
    String valueSerializer;

    @Value("${kafka.producer.topic}")
    String topicName;

    @Value("${kafka.producer.key}")
    String key;

    /*
    * create config bean from properties file
    * */
    @Bean
    public Map<String, Object> producerConfigs(){
        Map<String, Object> props=new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,valueSerializer);

/*        //another style:
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);*/

        return props;
    }

    /*
    * create a ProducerFactory with configs
    * */
    @Bean
    public ProducerFactory<String, CaseNew> producerFactoryCaseNew(){
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    /*
    * create a KafkaTemplate bean
    * */

    @Bean
    public KafkaTemplate<String, CaseNew> produceTemplateCaseNew(){
        return new KafkaTemplate<>(producerFactoryCaseNew());
    }
}
