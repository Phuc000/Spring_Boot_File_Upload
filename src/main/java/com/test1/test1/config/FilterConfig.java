package com.test1.test1.config;

import com.test1.test1.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class FilterConfig {

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtFilter();
    }
}