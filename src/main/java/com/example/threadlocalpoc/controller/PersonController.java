package com.example.threadlocalpoc.controller;

import com.example.threadlocalpoc.Model.Person;
import com.example.threadlocalpoc.Model.RequestContext;
import com.example.threadlocalpoc.interceptor.CorrelationInterceptor;
import com.example.threadlocalpoc.interceptor.Ns1TokenInterceptor;
import com.example.threadlocalpoc.service.PersonService;
import com.example.threadlocalpoc.threadlocal.MyThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonService personService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RequestContext requestContext;

    @Autowired
    private CorrelationInterceptor correlationInterceptor;

    @Autowired
    private Ns1TokenInterceptor ns1TokenInterceptor;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Person> helloWorld() {
        String apiKey = requestContext.getApiKey();
        String correlationId = requestContext.getCorrelationId();
        this.logger.debug("1. Init operation controller API key:{}", apiKey);
        this.logger.debug("1. Init operation controller correlationId:{}", correlationId);
        List<Person> allPersons = personService.getAllPersons();

        this.restTemplate.getInterceptors().clear();
        this.restTemplate.getInterceptors().add(ns1TokenInterceptor);
        this.restTemplate.getInterceptors().add(correlationInterceptor);
        ResponseEntity<String> response = this.restTemplate.getForEntity("https://api.nsone.net" + "/v1/zones", String.class);
        this.logger.debug("6. End operation controller API key:{}", apiKey);
        this.logger.debug("6. End operation controller correlationId:{}", correlationId);
        return allPersons;
    }

    private void SetRequestContextInHeader()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("headerName", "headerValue");

        HttpEntity<RequestContext> httpEntity = new HttpEntity<>(new RequestContext(), headers);
    }
}
