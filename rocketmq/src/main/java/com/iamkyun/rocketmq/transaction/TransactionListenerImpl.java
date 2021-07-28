package com.iamkyun.rocketmq.transaction;

import com.iamkyun.rocketmq.model.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

@Slf4j
@RocketMQTransactionListener
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // ... local transaction process, return rollback, commit or unknown
        log.info("executeLocalTransaction: {}", msg.getPayload());
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        Object payload = msg.getPayload();
        log.info("checkLocalTransaction: {}", payload);
        // ... check transaction status and return bollback, commit or unknown
        return RocketMQLocalTransactionState.COMMIT;
    }
}
