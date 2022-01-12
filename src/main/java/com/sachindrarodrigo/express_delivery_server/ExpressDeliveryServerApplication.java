package com.sachindrarodrigo.express_delivery_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableAsync
public class ExpressDeliveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpressDeliveryServerApplication.class, args);
    }

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }

}
