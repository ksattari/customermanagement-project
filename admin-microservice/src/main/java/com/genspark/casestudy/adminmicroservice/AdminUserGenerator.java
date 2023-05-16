//package com.genspark.casestudy.adminmicroservice;
//
//import com.genspark.casestudy.adminmicroservice.service.AdminService;
//import com.genspark.casestudy.adminmicroservice.service.AdminServiceImp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
//    This class is used to generate admin users at the time of application startup.
//    This is not a good practice to generate users like this. This is just for demo purpose.
//    In real world, you should have a separate application to manage users.
// */
//
//@Component
//public class AdminUserGenerator implements CommandLineRunner {
//
//    @Autowired
//    private AdminService userService;
//
//    List<String> adminUsernames = Arrays.asList("admin1", "admin2", "admin3");
//    List<String> adminPasswords = Arrays.asList("password1", "password2", "password3");
//
//
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        for (int i = 0; i < adminUsernames.size(); i++) {
//            String username = adminUsernames.get(i);
//            String password = adminPasswords.get(i);
//            userService.saveAdminUser(username, password);
//        }
//    }
//}
