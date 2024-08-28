import com.mytech.model.CaseNew;
import com.mytech.model.CaseNew.CaseStatus;
import com.mytech.model.CaseNew.CaseType;
import com.mytech.producer.KafkaProducerConfig;
import com.mytech.producer.MessageSenderCaseNew;
import com.mytech.producer.StringMessageProducerConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = KafkaProducerConfig.class)
public class MessageSenderCaseNewTests {

    @Autowired
    MessageSenderCaseNew sender;

    CaseNew caseNew;// Declare as an instance variable

    @BeforeEach
    public void setup(){
        caseNew=new CaseNew();  // Initialize the caseNew object
        caseNew.setCaseId(99);
        caseNew.setCaseStatus(CaseStatus.PENDING_REVIEW);
        caseNew.setCaseType(CaseType.NET_NEW);
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
