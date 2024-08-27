//package com.fifame.train.gateway.config.filter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * @ClassName TestFilter1
// * @Description TODO
// * @Author fifame
// * @DATE 2024-08-27 16:27
// * @Version 1.0
// */
//@Component
//public class TestFilter2 implements GlobalFilter, Ordered {
//    private static final Logger LOG = LoggerFactory.getLogger(TestFilter2.class);
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        LOG.info("TestFilter2");
//        return null;
//    }
//
//    @Override
//    public int getOrder() {
//        return 1;
//    }
//}
//
