package com.mytech.consumer;

import com.mytech.common.KafkaProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:kafkaconsumerconfig.properties")
public class KafkaConsumerProperties extends KafkaProperties {
    @Value("${kafka.consumer.trusted.packages}")
    String trustedPackages;

    @Value("${kafka.consumer.group-id}")
    String groupId;
}
