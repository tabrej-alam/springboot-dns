package com.example.threadlocalpoc.service;

import com.example.threadlocalpoc.Model.Person;
import com.example.threadlocalpoc.Model.RequestContext;
import com.example.threadlocalpoc.repository.PersonRepository;
import com.example.threadlocalpoc.threadlocal.MyThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RequestContext requestContext;

   public List<Person> getAllPersons(){
       String apikey = requestContext.getApiKey();
       String correlationId = requestContext.getCorrelationId();
       this.logger.debug("2. Init operation service layer API key:{}",apikey);
       this.logger.debug("2. Init operation service layer correlationId:{}",correlationId);

       List<Person> people = personRepository.getAll();
       this.logger.debug("5. End operation service layer API key:{}",apikey);
       this.logger.debug("5. End operation service layer correlationId:{}",correlationId);
       return people;

   }

}
