package com.obito.api_gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    RouteValidator validator;

    @Autowired
    RestTemplate restTemplate;
    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchane,chain)-> {
            if (validator.isSecured.test(exchane.getRequest())) {
                //check if header is present or not.
                if (!exchane.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing Authentiction Header");
                }

            System.out.println("Hi");
            String authHeaders = exchane.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if (authHeaders != null && authHeaders.startsWith("Bearer ")) {
                authHeaders = authHeaders.substring(7);
                System.out.println("" + authHeaders);
            }
            try {
                System.out.println("Hi");
                // restTemplate.getForObject("http://localhost:9044/user/validatetoken?token="+authHeaders, String.class);
            } catch (Exception e) {
                System.out.println("Invalid token ....");
                throw new RuntimeException("Invalid Token");
            }

        }
            return chain.filter(exchane);
        });
    }
    public static class Config{

    }
}
