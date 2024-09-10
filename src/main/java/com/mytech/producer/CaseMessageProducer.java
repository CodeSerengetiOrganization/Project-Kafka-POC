package com.mytech.producer;


import com.mytech.entity.CaseStatus;
import com.mytech.entity.CaseType;
import io.swagger.client.model.CaseNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class CaseMessageProducer {

    @Autowired
    KafkaTemplate<String, CaseNew> producerTemplateCase;
/*    public static void main(String[] args) {
        StringMessageProducer producer=new StringMessageProducer();
        producer.sendMessage();
    }*/

    // todo: need to add a new method :public void sendMessage(CaseNew caseNew)
    public void sendMessage() {

        String topicName="test-topic";
        String key="case-management";
//        String data="data sent from java producer via KafkaTemplate";
        CaseNew caseNew=new CaseNew();
        caseNew.setCaseId(99);
        caseNew.setCaseStatus(CaseNew.CaseStatusEnum.PENDINGDOCUMENT);
        caseNew.setCaseType(CaseNew.CaseTypeEnum.LOD);
        caseNew.setCreatedBy("Perry Stark");
        caseNew.setCreateDate(LocalDateTime.now() );
        caseNew.setModifiedDate(LocalDateTime.now());
        caseNew.setPendingReviewDate(LocalDateTime.now());
        caseNew.setNote("Kafka note");


        CompletableFuture<SendResult<String, CaseNew>> sendResultFuture = producerTemplateCase.send(topicName, key, caseNew);

        try{

            SendResult<String, CaseNew> sendResult = sendResultFuture.get();
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
