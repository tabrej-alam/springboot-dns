package com.example.threadlocalpoc.repository;

import com.example.threadlocalpoc.Model.Person;
import com.example.threadlocalpoc.Model.RequestContext;
import com.example.threadlocalpoc.threadlocal.MyThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class PersonRepository {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RequestContext requestContext;

    @Autowired
    private RestTemplate restTemplate;

    public List<Person> getAll() {

        String apiKey = requestContext.getApiKey();
        String correlationId = requestContext.getCorrelationId();
        this.logger.debug("3. Init operation repository layer API key:{}",apiKey);
        this.logger.debug("3. Init operation repository layer correlationId:{}",correlationId);
        Person person = new Person();
        person.setName("Tabrej");
        person.setSurname("Alam");
        person.setAge(31);
        person.setAdress("Bangalore");
        List<Person> people = Arrays.asList(person);
        this.logger.debug("4. End operation repository layer API key:{}",apiKey);
        this.logger.debug("4. End operation repository layer correlationId:{}",correlationId);
        return people;


    }
}
