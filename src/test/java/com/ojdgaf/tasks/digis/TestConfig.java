package com.ojdgaf.tasks.digis;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@TestConfiguration
public class TestConfig {
    @Bean
    public PodamFactory podamFactory() {
        return new PodamFactoryImpl();
    }
}
