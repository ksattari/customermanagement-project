package com.genspark.casestudy.adminmicroservice.config;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

@Configuration
public class BeanConfiguration {

     @Bean
     @LoadBalanced
     public RestTemplate restTemplate(){
        return new RestTemplate();
    }


//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        requestFactory.setOutputStreaming(false);
//        requestFactory.setConnectTimeout(3000);
//        requestFactory.setReadTimeout(3000);
//
//        RestTemplate restTemplate = new RestTemplate(requestFactory);
//
//        // Add a CORS interceptor to the RestTemplate
//        restTemplate.setInterceptors(Collections.singletonList(new CorsInterceptor()));
//
//        return restTemplate;
//    }
//
//    private static class CorsInterceptor implements ClientHttpRequestInterceptor {
//        @Override
//        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
//                throws IOException {
//            HttpHeaders headers = request.getHeaders();
//            headers.add("Access-Control-Allow-Origin", "*");
//            headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//            headers.add("Access-Control-Allow-Headers", "Authorization, Content-Type");
//
//            return execution.execute(request, body);
//        }
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

