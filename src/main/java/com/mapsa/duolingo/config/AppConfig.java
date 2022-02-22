package com.mapsa.duolingo.config;

import com.mapsa.duolingo.security.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean <Filter> registrationBean = new FilterRegistrationBean();
        Filter Filter = new Filter();

        registrationBean.setFilter(Filter);
        registrationBean.addUrlPatterns("/course/*");
        registrationBean.addUrlPatterns("/user/delete account");
        return registrationBean;
    }
}
