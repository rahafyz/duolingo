package com.mapsa.duolingo.config;

import com.mapsa.duolingo.security.UserDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class AppConfig {
    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserDetail userDetail() {
        UserDetail userDetail = new UserDetail();
        return userDetail;
    }


}
