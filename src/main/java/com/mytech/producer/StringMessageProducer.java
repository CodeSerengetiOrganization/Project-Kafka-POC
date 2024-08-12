package com.mytech.producer;

import org.apache.kafka.clients.producer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class StringMessageProducer {

    @Autowired
    KafkaTemplate<String, String> producerTemplateString;
/*    public static void main(String[] args) {
        StringMessageProducer producer=new StringMessageProducer();
        producer.sendMessage();
    }*/

    public void sendMessage() {

        String topicName="test-topic";
        String key="case-management";
        String data="data sent from java producer via KafkaTemplate";
        CompletableFuture<SendResult<String, String>> sendResultFuture = producerTemplateString.send(topicName, key, data);

        try{

            SendResult<String, String> sendResult = sendResultFuture.get();
            System.out.println(sendResult.toString());
        } catch ( ExecutionException executionException){
//            System.out.println();
            executionException.printStackTrace();
        }catch ( InterruptedException interruptedException){
            interruptedException.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    /*
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
        }*/
    }
}
