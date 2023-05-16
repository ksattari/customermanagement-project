package com.genspark.casestudy.adminmicroservice.service;

import com.genspark.casestudy.adminmicroservice.Repository.UserRepository;
import com.genspark.casestudy.adminmicroservice.model.AdminUser;
import com.genspark.casestudy.adminmicroservice.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AdminUser> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return new UserPrincipal(user.get());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
