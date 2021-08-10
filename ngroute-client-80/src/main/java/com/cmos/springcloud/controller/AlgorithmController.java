package com.cmos.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cmos.springcloud.common.domain.AjaxResult;
import com.cmos.springcloud.service.AlgorithmServcie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author lizhe
 * @date 2021-05-08 22:14
 */
@RestController
@RequestMapping("/system")
@Slf4j
public class AlgorithmController {
    @Value("${service-url.nacos-user-service}")
    private String SERVICE_URL;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AlgorithmServcie algorithmServcie;
    @PostMapping(value = "/algorithm/CollaborativeFiltering")
    @SentinelResource(value = "fallback")//没有配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback")//fallback只负责业务
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")//blockHandler只负责Sentinel控制台配置
    //如果两者都配置则 被限流降级而抛出BlockException时只会进入blockHandler
    //@SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler")
    //exceptionsToIgnore排除异常
    //@SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler", exceptionsToIgnore={IllegalArgumentException.class})
    public AjaxResult filtering(@RequestBody TreeMap<String,Object> map){
        return algorithmServcie.filtering(map);
    }
    // 服务流量控制处理，参数最后多一个 BlockException，其余与原函数一致。
    public AjaxResult blockHandler(TreeMap<String,Object> map, BlockException exception){
        return new AjaxResult(444,exception.getClass().getCanonicalName()+"\t 服务端算法服务繁忙");
    }
    // 服务熔断降级处理，函数签名与原函数一致或加一个 Throwable 类型的参数
    public AjaxResult handlerFallback(TreeMap<String,Object> map,Throwable exception){
        return new AjaxResult(444,"handlerFallback异常兜底服务"+exception.getMessage()+" 服务端算法异常");
    }
}
