import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.mytech.producer.MessageProducerConfig;
import com.mytech.producer.StringMessageProducer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MessageProducerConfig.class)
public class StringMessageProducerTests {
    @Autowired
    StringMessageProducer producer;

    @Test
    public void shouldSendMessageSuccessfully(){
        producer.sendMessage();
        System.out.println("Junit works");

    }
}
