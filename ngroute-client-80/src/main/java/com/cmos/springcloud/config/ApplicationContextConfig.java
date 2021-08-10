package com.cmos.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author lizhe
 * @date 2021-04-05 13:37
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced//开启负载均衡的功能
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
