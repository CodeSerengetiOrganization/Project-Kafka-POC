

import com.mytech.producer.CaseMessageProducerConfig;
import com.mytech.producer.KafkaProducerConfig;
import com.mytech.producer.MessageSenderCaseNew;
import com.mytech.producer.StringMessageProducerConfig;
import io.swagger.client.model.CaseNew;
import io.swagger.client.model.CaseNew.CaseTypeEnum;
import io.swagger.client.model.CaseNew.CaseStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
@ContextConfiguration(classes = KafkaProducerConfig.class)
public class MessageSenderCaseNewTests {

    @Autowired
    MessageSenderCaseNew sender;

    CaseNew caseNew;// Declare as an instance variable

    @BeforeEach
    public void setup(){
        caseNew=new CaseNew();  // Initialize the caseNew object
        caseNew.setCaseId(99);
        caseNew.setCaseStatus(CaseStatusEnum.PENDINGDOCUMENT);
        caseNew.setCaseType(CaseTypeEnum.LOD);
        caseNew.setCreatedBy("Perry Stark");
        caseNew.setCreateDate(LocalDateTime.now() );
        caseNew.setModifiedDate(LocalDateTime.now());
        caseNew.setPendingReviewDate(LocalDateTime.now());
        caseNew.setNote("Kafka note");
    }

    @Test
    public void shouldSendSuccessfully(){
        sender.sendMessageSync(caseNew);
    }
}
