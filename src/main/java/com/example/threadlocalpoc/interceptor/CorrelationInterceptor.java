/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.threadlocalpoc.interceptor;

import com.example.threadlocalpoc.Model.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CorrelationInterceptor implements ClientHttpRequestInterceptor{
    
    private final static String CORRELATION_ID = "X-CORRELATION-ID";

    @Autowired
    private RequestContext requestContext;
    
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // ToDo
        request.getHeaders().set(CORRELATION_ID, this.requestContext.getCorrelationId());
        return execution.execute(request, body);
    }
}
