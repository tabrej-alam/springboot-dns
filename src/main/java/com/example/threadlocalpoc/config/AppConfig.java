package com.example.threadlocalpoc.config;

import com.example.threadlocalpoc.Model.RequestContext;
import com.example.threadlocalpoc.filter.CustomFilter;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean(name = "rest-template")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public CustomFilter customFilter() {
        return new CustomFilter();
    }

    @Bean
    public FilterRegistrationBean customFilterRegistration() {
        FilterRegistrationBean result = new FilterRegistrationBean();
        result.setFilter(this.customFilter());
        result.addUrlPatterns("/*","/**");
        result.setName("CustomFilter");
        result.setOrder(1);
        return result;
    }

    @Bean(destroyMethod = "destroy")
    public ThreadLocalTargetSource threadLocalRequestContext() {
        ThreadLocalTargetSource result = new ThreadLocalTargetSource();
        result.setTargetBeanName("requestContext");
        return result;
    }

    @Primary
    @Bean(name = "proxiedThreadLocalTargetSource")
    public ProxyFactoryBean proxiedThreadLocalTargetSource(ThreadLocalTargetSource threadLocalTargetSource) {
        ProxyFactoryBean result = new ProxyFactoryBean();
        result.setTargetSource(threadLocalTargetSource);
        return result;
    }

    @Bean(name = "requestContext")
    @Scope(scopeName = "prototype")
    public RequestContext requestContext(){
        RequestContext context= new RequestContext();
        return context;
    }
}