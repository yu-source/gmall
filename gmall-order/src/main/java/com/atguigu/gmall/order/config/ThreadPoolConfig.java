package com.atguigu.gmall.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(
            @Value("${gmall.threadPool.corePoolSize}") Integer corePoolSize,
            @Value("${gmall.threadPool.maximunPoolSize}") Integer maximunPoolSize,
            @Value("${gmall.threadPool.keepAliveTime}") Integer keepAliveTime) {

        return new ThreadPoolExecutor(corePoolSize, maximunPoolSize, keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5000));
    }
}