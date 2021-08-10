package com.cmos.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lizhe
 * @date 2021-04-18 22:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NgrouteClient80 {
    public static void main(String[] args) {
        SpringApplication.run(NgrouteClient80.class,args);
    }
}
