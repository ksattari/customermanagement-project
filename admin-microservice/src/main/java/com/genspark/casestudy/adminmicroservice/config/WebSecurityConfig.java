package com.genspark.casestudy.adminmicroservice.config;

import com.genspark.casestudy.adminmicroservice.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebSecurityConfigurer<WebSecurity> {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder BCryptPasswordEncoder;
    private final ObjectPostProcessor<Object> objectPostProcessor;

    @Autowired
    private CorsFilter corsFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                             UserDetailsServiceImpl userDetailsService,
                             BCryptPasswordEncoder passwordEncoder,
                             ObjectPostProcessor<Object> objectPostProcessor) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
        this.BCryptPasswordEncoder = passwordEncoder;
        this.objectPostProcessor = objectPostProcessor;
    }

    @Override
    public void init(WebSecurity builder) throws Exception {
        builder.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsFilter, ChannelProcessingFilter.class)
        .csrf().disable()
                .formLogin().disable()
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/getOrders").permitAll()
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(BCryptPasswordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationManagerBuilder().build();
    }



    private AuthenticationManagerBuilder authenticationManagerBuilder() throws Exception {
        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);
        builder.userDetailsService(userDetailsService).passwordEncoder(BCryptPasswordEncoder);
        return builder;
    }

}
