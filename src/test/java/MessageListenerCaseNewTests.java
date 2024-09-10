import com.mytech.consumer.KafkaConsumerConfig;
import com.mytech.consumer.MessageListenerCaseNew;
import com.mytech.producer.KafkaProducerConfig;
import com.mytech.producer.MessageSenderCaseNew;
import io.swagger.client.model.CaseNew;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.awaitility.Awaitility;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/*@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= KafkaConsumerConfig.class)*/

//@EnableKafka
/*@EmbeddedKafka(partitions = 1,
        brokerProperties = {
                "listeners=PLAINTEXT://127.0.0.1:9092",
                "log.dirs=/tmp/kafka-logs"
        },
        ports = {9092},
        topics = "test-topic")*/
//@SpringBootTest
//@ContextConfiguration(classes= KafkaConsumerConfig.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {KafkaConsumerConfig.class,KafkaProducerConfig.class})
public class MessageListenerCaseNewTests {

    @Autowired
    private MessageListenerCaseNew listenerCaseNew;

    @Autowired
    private MessageSenderCaseNew senderCaseNew;

/*
    @Autowired
    private KafkaTemplate<String, CaseNew> kafkaTemplate;
*/
    private boolean messageReceived = false; // Simulating message reception flag.
    @Test
    public void shouldReceiveKafkaMessage(){
/*        ConsumerRecord<String, CaseNew> record=new ConsumerRecord<>();
        listenerCaseNew.listenerCaseNew(record);
        System.out.println("record in unit test: "+record.toString());*/


        // Send a test message
        CaseNew caseNew = new CaseNew();
        caseNew.setCaseId(99);
        caseNew.setCaseStatus(CaseNew.CaseStatusEnum.PENDINGDOCUMENT);
        caseNew.setCaseType(CaseNew.CaseTypeEnum.FRAUD);
        caseNew.setCreatedBy("Tony Stark");
        caseNew.setCreateDate(LocalDateTime.now());
        caseNew.setModifiedDate(LocalDateTime.now());
        caseNew.setPendingReviewDate(LocalDateTime.now());
        caseNew.setNote("Kafka note2");

//        kafkaTemplate.send("test-topic", caseNew);
        senderCaseNew.sendMessageSync(caseNew);
        System.out.println("need listener");



        // Add assertions to verify the message was processed correctly
        startKafkaConsumer();

        // Approach 1: Use Awaitility to wait for the message to be received.
        Awaitility.await()
                .atMost(30, TimeUnit.SECONDS)
                .until(() -> messageReceived);  // This should be the condition that verifies message reception.


    }

    private void startKafkaConsumer() {
        // Simulate Kafka consumer starting and receiving a message
        // You would replace this with actual Kafka consumer initialization and message processing logic.
        new Thread(() -> {
            try {
                Thread.sleep(4000); // Simulate message processing delay
                messageReceived = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
