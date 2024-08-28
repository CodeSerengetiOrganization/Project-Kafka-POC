package com.mytech.producer;

import com.mytech.model.CaseNew;
import org.apache.kafka.common.errors.TimeoutException;
import org.apache.kafka.common.errors.InterruptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class MessageSenderCaseNew {

    @Autowired
    KafkaProducerConfig producerConfig;

    @Autowired
    KafkaTemplate<String, CaseNew> producerTemplateCaseNew;

    public void sendMessageSync(CaseNew caseNew){
        try {
            CompletableFuture<SendResult<String, CaseNew>> future = producerTemplateCaseNew.send(producerConfig.topicName, producerConfig.key, caseNew);
            SendResult<String, CaseNew> sendResult = future.get();
            if(sendResult.getRecordMetadata() !=null){
                //todo: log the activity like:
                //logger.debug("Message send success: "+sendResult.toString());
                System.out.println("Message send success: "+sendResult.toString());
            }
        }catch (ExecutionException e){
            e.printStackTrace();
            //logger.error("Message send failed : "+e.getMessage());
        }catch (TimeoutException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
/*
* exceptions are from spring doc example: https://docs.spring.io/spring-kafka/reference/kafka/sending-messages.html
*
* Note that the cause of the ExecutionException is KafkaProducerException with the producerRecord property.
* */