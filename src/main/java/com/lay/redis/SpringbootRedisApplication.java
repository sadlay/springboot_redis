package com.lay.redis;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = "com.lay.redis", annotationClass = Mapper.class)
@EnableCaching
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
