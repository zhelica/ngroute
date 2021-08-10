package com.cmos.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cmos.springcloud.common.domain.AjaxResult;
import com.cmos.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

/**
 * @author lizhe
 * @date 2021-05-08 9:00
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class UserController {
    @Value("${service-url.nacos-user-service}")
    private String SERVICE_URL;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @PostMapping(value = "/user/register")
    //@SentinelResource(value = "fallback")//没有配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback")//fallback只负责业务
    @SentinelResource(value = "fallback",blockHandler = "blockHandler")//blockHandler只负责Sentinel控制台配置
    //如果两者都配置则 被限流降级而抛出BlockException时只会进入blockHandler
    //@SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler")
    //exceptionsToIgnore排除异常
    //@SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler", exceptionsToIgnore={IllegalArgumentException.class})
    public AjaxResult register(@RequestBody Map<String,Object> map){
        return userService.register(map);
    }
    // 服务流量控制处理，参数最后多一个 BlockException，其余与原函数一致。
    public AjaxResult blockHandler(Map<String,Object> map, BlockException exception){
        return new AjaxResult(444,exception.getClass().getCanonicalName()+"\t 服务端注册服务繁忙");
    }
    // 服务熔断降级处理，函数签名与原函数一致或加一个 Throwable 类型的参数
    public AjaxResult handlerFallback(Map<String,Object> map,Throwable exception){
        return new AjaxResult(444,"handlerFallback异常兜底服务"+exception.getMessage()+" 服务端注册服务异常");
    }
    @PostMapping(value = "/user/selectUserByUserName")
    @SentinelResource(value = "fallback",blockHandler = "blockHandler")//blockHandler只负责Sentinel控制台配置
    public AjaxResult selectUserByUserName(@RequestBody Map<String,Object> map){
        return userService.selectUserByUserName(map);
    }
    @PostMapping(value = "/user/login")
    @SentinelResource(value = "fallback",blockHandler = "blockHandler")//blockHandler只负责Sentinel控制台配置
    public AjaxResult login(@RequestBody Map<String,Object> map){
        return userService.login(map);
    }
}
