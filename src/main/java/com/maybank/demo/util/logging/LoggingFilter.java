package com.maybank.demo.util.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();
        Logger logger = LoggerFactory.getLogger(requestURI);

        logger.info("REQUEST METHOD: {}", httpServletRequest.getMethod());
        chain.doFilter(httpServletRequest, httpServletResponse);

        logger.info("RESPONSE STATUS CODE: {}", httpServletResponse.getStatus());
    }

}
