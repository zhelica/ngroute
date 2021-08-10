package com.cmos.springcloud.service;

import com.alibaba.fastjson.JSONObject;
import com.cmos.springcloud.mapper.IMessageProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author lizhe
 * @date 2021-04-22 19:51
 */
@Component
@EnableBinding(Sink.class)
public class IMessageProduct {
    @Value("${server.port}")
    private String serverPort;
    @Value("${config.info}")
    private String info;
    @Autowired
    private IMessageProductMapper messageProductMapper;
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        TreeMap treeMap = JSONObject.parseObject(message.getPayload(), TreeMap.class);
        messageProductMapper.addProduct(treeMap);
        System.out.println("消费者"+serverPort+"接收到的消息:"+message.getPayload());
        System.out.println(info);
    }
}
