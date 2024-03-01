package com.hels.ecommerceengine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.LocalDateTime;

@Slf4j
@Configuration
public class BeanConfig {
    @Bean
    public Clock getClock() {
        Clock clock = Clock.systemDefaultZone();
        log.info("{}", LocalDateTime.now(clock));
        return clock;
    }
}
