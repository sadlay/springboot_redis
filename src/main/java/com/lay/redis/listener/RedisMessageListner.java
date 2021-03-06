package com.lay.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisMessageListner implements MessageListener {
    
    @Override
    public void onMessage(Message message, byte[] pattern) {
        //消息体
        String body = new String(message.getBody());
        //渠道名称
        String topic = new String(pattern);
        System.out.println("body消息体： " + body);
        System.out.println("topic渠道名称： " + topic);
    }
    
}
