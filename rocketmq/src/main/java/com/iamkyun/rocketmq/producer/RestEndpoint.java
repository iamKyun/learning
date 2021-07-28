package com.iamkyun.rocketmq.producer;

import com.iamkyun.rocketmq.model.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Slf4j
@RestController
public class RestEndpoint {
    @Resource(name = "rocketMQTemplate")
    private RocketMQTemplate rocketMQTemplate;

    @Resource(name = "rocketMQTemplate2")
    private RocketMQTemplate rocketMQTemplate2;

    @PostMapping("/message")
    public void sendMessage() {
        for (int i = 0; i < 100; i++) {
            log.info("send {}", i);
            rocketMQTemplate.convertAndSend("topic-1", new OrderPaidEvent(String.valueOf(i), BigDecimal.valueOf(i)));
        }
    }

    @PostMapping("/tx-message")
    public void sendTxMessage() {
        Message<OrderPaidEvent> msg =
                MessageBuilder.withPayload(new OrderPaidEvent("tx", BigDecimal.valueOf(123))).build();
        rocketMQTemplate.sendMessageInTransaction("topic-1", msg, null);
        rocketMQTemplate2.sendMessageInTransaction("topic-1", msg, null);
    }

}
