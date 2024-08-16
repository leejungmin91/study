package com.store.memberapi.config;
import com.store.memberapi.util.HttpRequestEndpointChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final HandlerExceptionResolver resolver;

    @Autowired
    private HttpRequestEndpointChecker httpRequestEndpointChecker;

    public JwtAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        if(!httpRequestEndpointChecker.isEndpointExist(request)){
            HttpHeaders httpHeaders = Collections.list(request.getHeaderNames())
                    .stream()
                    .collect(Collectors.toMap(
                            Function.identity(),
                            h -> Collections.list(request.getHeaders(h)),
                            (oldValue, newValue) -> newValue,
                            HttpHeaders::new
                    ));
            resolver.resolveException(request, response, null, new NoHandlerFoundException(request.getMethod(), request.getRequestURI(), httpHeaders));
        }else {
            resolver.resolveException(request, response, null, (Exception) request.getAttribute("exception"));
        }
    }
}
