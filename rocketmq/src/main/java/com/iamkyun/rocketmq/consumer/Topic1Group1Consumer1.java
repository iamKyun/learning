package com.iamkyun.rocketmq.consumer;

import com.iamkyun.rocketmq.model.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "topic-1", consumerGroup = "topic-1-group-1")
public class Topic1Group1Consumer1 implements RocketMQListener<OrderPaidEvent> {
    public void onMessage(OrderPaidEvent message) {
        log.info("received 1 {}", message.getOrderId());
    }
}
