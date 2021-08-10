package com.cmos.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lizhe
 * @date 2021-05-07 20:02
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NgrouteServer9002 {
    public static void main(String[] args) {
        SpringApplication.run(NgrouteServer9002.class,args);
    }
}
