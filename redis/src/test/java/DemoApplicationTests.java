import com.iamkyun.redis.RedisApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest(classes = RedisApplication.class)
class DemoApplicationTests {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void test() {
        HyperLogLogOperations<String, String> hyperLogLogOperations = redisTemplate.opsForHyperLogLog();

        for (int i = 0; i < 1000; i++) {
            hyperLogLogOperations.add("ip", "192.168.1." + i);
        }

        System.out.println(hyperLogLogOperations.size("ip"));

    }

}
