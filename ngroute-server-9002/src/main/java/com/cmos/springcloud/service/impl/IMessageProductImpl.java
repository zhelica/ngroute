package com.cmos.springcloud.service.impl;

import com.cmos.springcloud.service.IMessageProduct;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @author lizhe
 * @date 2021-04-22 19:52
 */
@EnableBinding(Source.class)//定义消息的推送管道
@Service
public class IMessageProductImpl implements IMessageProduct {
    @Resource
    private MessageChannel output;//消息发送管道
    @Override
    public String send(String userId) {
        String serialno = UUID.randomUUID().toString();
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("serialno",serialno);
        resMap.put("userId",userId);
        resMap.put("productName","新用户注册赠送商品一个。");
        output.send(MessageBuilder.withPayload(resMap).build());
        return null;
    }
}
