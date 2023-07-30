package com.isomer.gateway.filter;

import com.isomer.redis.core.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/28 12:30
 */
@Order(-3)
@Component
public class OriginFilter implements GlobalFilter {

    @Autowired
    private RedisService<String, String> redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String code = genCode();
        redisService.setValue(code, "", 10, TimeUnit.SECONDS);
        request.mutate().header("Code", code);

        return chain.filter(exchange);
    }

    private String genCode() {
        return UUID.randomUUID().toString();
    }
}
