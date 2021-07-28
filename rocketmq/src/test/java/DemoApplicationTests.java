import com.iamkyun.rocketmq.model.OrderPaidEvent;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void test() {
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent("1", BigDecimal.valueOf(1L));
        rocketMQTemplate.convertAndSend("topic-1", orderPaidEvent);
    }

}
