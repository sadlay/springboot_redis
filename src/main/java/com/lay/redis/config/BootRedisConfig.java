package com.lay.redis.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lay.redis.listener.RedisMessageListner;

@Configuration
@EnableCaching
public class BootRedisConfig {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory = null;
    
    @Autowired
    private RedisTemplate redisTemplate = null;
    
    @Autowired
    private RedisMessageListner redisMessageListner = null;
    
    //任务池
    private ThreadPoolTaskScheduler taskScheduler = null;
    
    @PostConstruct
    public void init() {
        initRedisTemplate();
    }
    
    /**
     * redisTemplate 
     * @return
     * @Date        2018年11月4日 下午10:11:35 
     * @Author      lay
     */
    private RedisTemplate initRedisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        Jackson2JsonRedisSerializer<Object> jacksonSeial = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jacksonSeial);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jacksonSeial);
        return redisTemplate;
        
    }
    
    /**
     * 任务池
     * @return
     * @Date        2018年11月4日 下午10:11:02 
     * @Author      lay
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler() {
        if (taskScheduler != null) {
            return taskScheduler;
        }
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(20);
        this.taskScheduler = threadPoolTaskScheduler;
        return threadPoolTaskScheduler;
    }
    
    /**
     * redis消息监听容器
     * @return
     * @Date        2018年11月4日 下午10:10:26 
     * @Author      lay
     */
    @Bean
    public RedisMessageListenerContainer initRedisContainer() {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.setTaskExecutor(taskScheduler);
        Topic topic = new ChannelTopic("topic1");
        redisMessageListenerContainer.addMessageListener(redisMessageListner, topic);
        return redisMessageListenerContainer;
    }
    
}
