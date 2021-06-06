package com.iamkyun.spb.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

@SpringBootApplication
@Slf4j
public class RedisDemo implements ApplicationRunner {

    @Autowired
    private RedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RedisDemo.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ZSetOperations ops = redisTemplate.opsForZSet();
        String key = "kzset";
        ops.add(key, "A", 4);
        ops.add(key, "B", 3);
        ops.add(key, "C", 1);
        ops.add(key, "D", 2);

        Set range = ops.rangeByScore(key, 0, Long.MAX_VALUE, 0, 1);
        String next = (String) range.iterator().next();
        log.info(next);
        Long remove = ops.remove(key, next);
        log.info(String.valueOf(remove));
        Long aLong = ops.zCard(key);
        log.info(String.valueOf(aLong));

    }
}
