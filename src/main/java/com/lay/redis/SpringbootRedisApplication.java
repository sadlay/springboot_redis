package com.lay.redis;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.lay.redis.config.RedisConfig;

@SpringBootApplication
public class SpringbootRedisApplication {
    
    public static void main(String[] args) {
        //		SpringApplication.run(SpringbootRedisApplication.class, args);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        //redisTemplate.opsForValue().set("key1", "value1");
        //redisTemplate.opsForHash().put("hash", "field", "hvalue");
        System.out.println(redisTemplate.opsForValue().get("key1"));
        System.out.println(redisTemplate.opsForHash().get("hash", "field"));
    }
}
