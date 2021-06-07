package com.iamkyun.spb.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;


@SpringBootApplication
@Slf4j
public class KafkaDemo {

    static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext run = SpringApplication.run(KafkaDemo.class, args);

        KafkaTemplate template = run.getBean(KafkaTemplate.class);
        for (int j = 0; j < 100; j++) {
            String msg = "Hello Kafka " + j;
            template.send("topic1", msg);
        }

        countDownLatch.await();
        run.close();
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                           .partitions(10)
                           .replicas(1)
                           .build();
    }


    @KafkaListener(topics = "topic1", id = "consumer1", groupId = "g1")
    public void listen1(String msg) {
        log.info("listen1 received:" + msg);
        countDownLatch.countDown();
    }

    @KafkaListener(topics = "topic1", id = "consumer2", groupId = "g1")
    public void listen2(String msg) {
        log.info("listen2 received:" + msg);
        countDownLatch.countDown();
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
        return args -> template.send("topic1", "test");
    }

}
