package com.mytech.consumer;


//import com.mytech.model.CaseNew;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConsumerApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(KafkaConsumerConfig.class);
        KafkaConsumerConfig kafkaConsumerConfigBean = context.getBean(KafkaConsumerConfig.class);
        MessageListenerCaseNew messageListenerCaseNewBean = context.getBean(MessageListenerCaseNew.class);
/*        ConsumerRecord<String, CaseNew> ConsumerRecord = null;
        bean.listenerCaseNew(ConsumerRecord);*/
        System.out.println("bean in Main: "+messageListenerCaseNewBean.toString());
    }
}
