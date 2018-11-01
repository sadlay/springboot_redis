package com.lay.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootRedisApplication {
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringbootRedisApplication.class, args);
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        /*        RedisTemplate redisTemplate = (RedisTemplate)ctx.getBean("redisTemplate");
        //redisTemplate.opsForValue().set("key1", "value1");
        //redisTemplate.opsForHash().put("hash", "field", "hvalue");
        System.out.println(redisTemplate.opsForValue().get("key1"));
        System.out.println(redisTemplate.opsForHash().get("hash", "field"));*/
    }
}
