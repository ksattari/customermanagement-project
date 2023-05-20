package com.genspark.casestudy.adminmicroservice.controller;

import com.genspark.casestudy.adminmicroservice.config.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        System.out.println("INSIDE LOGIN");
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(userDetails);


        return ResponseEntity.ok(new LoginResponse(token));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    @NoArgsConstructor
    private static class LoginResponse {
        private String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }
}
