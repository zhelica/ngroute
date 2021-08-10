package com.cmos.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author lizhe
 * @date 2021-04-16 20:21
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("****come in MyLogGateWayFilter:"+new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
//        if(uname==null){
////            log.info("*****用户名为null，非法用户！");
////            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
////            return exchange.getResponse().setComplete();
////        }
        Mono<Void> filter = chain.filter(exchange);
        return filter;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
