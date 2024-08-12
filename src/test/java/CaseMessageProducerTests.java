import com.mytech.producer.CaseMessageProducer;
import com.mytech.producer.StringMessageProducerConfig;
import com.mytech.producer.StringMessageProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StringMessageProducerConfig.class)
public class CaseMessageProducerTests {
    @Autowired
    CaseMessageProducer producer;

    @Test
    public void shouldSendMessageSuccessfully(){
        producer.sendMessage();
        System.out.println("Junit works with Send Case Message");

    }
}
