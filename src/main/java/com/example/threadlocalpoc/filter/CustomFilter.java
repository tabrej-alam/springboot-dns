package com.example.threadlocalpoc.filter;

import com.example.threadlocalpoc.Model.RequestContext;
import com.example.threadlocalpoc.threadlocal.MyThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
public class CustomFilter implements Filter {

    @Autowired
    RequestContext requestContext;
    private final static String DNS_API_TOKEN = "X-UMS-DNS-API-KEY";

    @Override
    public void init(FilterConfig fc) throws ServletException {
        // nothing
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String apiKey = request.getHeader(DNS_API_TOKEN);

        try {
            // call filter(s) upstream for the real processing of the request
            this.requestContext.setApiKey(apiKey);
            this.requestContext.setCorrelationId(UUID.randomUUID().toString());
            chain.doFilter(servletRequest, res);
        } finally {
            // it's important to always clean the local thread context
            // this Thread goes to the pool so another request using this thread would have this value.
            this.requestContext.clear();
        }

    }

    @Override
    public void destroy() {
        // nothing
    }
}