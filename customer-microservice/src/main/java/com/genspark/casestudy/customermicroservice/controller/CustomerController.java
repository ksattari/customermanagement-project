package com.genspark.casestudy.customermicroservice.controller;


import com.genspark.casestudy.customermicroservice.model.Customer;
import com.genspark.casestudy.customermicroservice.model.Dependent;
import com.genspark.casestudy.customermicroservice.model.Order;
import com.genspark.casestudy.customermicroservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());

        return "register_form";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout){

        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");


        return "login";
    }

    @PostMapping("/process_register")
    public String processRegister(Customer customer) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        customerService.saveCustomer(customer);

        return "redirect:/register_success";
    }

    @GetMapping("/customer")
    public String customerPanel(Authentication authentication, Model model ){

        Customer customer = customerService.findByEmail(authentication.getName());
        model.addAttribute("customer",customer);
        model.addAttribute("content", "customerHome");

        return "customer";
    }

    @GetMapping("/customerInfo")
    public String customerInfo(Authentication authentication, Model model ){

        Customer customer = customerService.findByEmail(authentication.getName());
        model.addAttribute("customer",customer);
        model.addAttribute("content", "customerInfo");

        return "customer";
    }

    @GetMapping("/dependentRegister")
    public String dependentRegisterForm(Authentication authentication, Model model ){

        Customer customer = customerService.findByEmail(authentication.getName());
        model.addAttribute("customer",customer);
        model.addAttribute("dependent",new Dependent());
        model.addAttribute("content", "dependentRegister");

        return "customer";
    }

    @PostMapping("/dependentRegister")
    public String dependentRegisterForm(Dependent dependent,
                                        Authentication authentication, Model model ){

        Customer customer = customerService.findByEmail(authentication.getName());
        customer.addDependent(dependent);
        customerService.saveCustomer(customer);

        return "redirect:/dependentView";
    }

    @GetMapping("/dependentView")
    public String dependentView(Authentication authentication, Model model ) {

        Customer customer = customerService.findByEmail(authentication.getName());
        model.addAttribute("customer", customer);
        model.addAttribute("content", "dependentView");

        return "customer";
    }

    @GetMapping("/dependentDelete")
    public String dependentDelete(Dependent dependent, Authentication authentication, Model model ){

        Customer customer = customerService.findByEmail(authentication.getName());
        model.addAttribute("customer",customer);
        model.addAttribute("content", "dependentDelete");

        return "customer";
    }

    @PostMapping("/dependentDelete")
    public String processDependentDelete(@RequestParam String dependentId,
                                         Authentication authentication, Model model ){

        Customer customer = customerService.findByEmail(authentication.getName());
        log.info(dependentId);
        log.info(Boolean.toString(customer.removeDependent(Long.parseLong(dependentId))));
        log.info(customer.getDependents().toString());
        customerService.saveCustomer(customer);
        model.addAttribute("customer",customer);
        model.addAttribute("content", "dependentView");

        return "customer";
    }

    @GetMapping("/orderCreate")
    public String orderCreateView(Authentication authentication, Model model ) {

        Customer customer = customerService.findByEmail(authentication.getName());
        model.addAttribute("customer", customer);
        model.addAttribute("content", "orderCreate");
        model.addAttribute("order",new Order());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDateTime now = LocalDateTime.now();
        String dateTimeString = now.format(formatter);	//03-28-2019
        model.addAttribute("date",dateTimeString);

        return "customer";
    }

    @PostMapping("/processOrder")
    public String processOrder(@RequestParam("file") MultipartFile file,
                               Authentication authentication,
                               Order order, Model model) throws Exception {

        Customer customer = customerService.findByEmail(authentication.getName());
        order.setAttachment(customerService.createAttachment(file));
        order = customerService.sendOrder(order);   // sends order to order-microservice
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm");
        LocalDateTime orderDate = order.getOrderDate();
        String dateTimeString = orderDate.format(formatter);
        model.addAttribute("content", "orderConfirmation");
        model.addAttribute("customer", customer);
        model.addAttribute("order",order);
        model.addAttribute("orderdate",dateTimeString);
        model.addAttribute("ordertotal",
                order.getQty() * order.getOrderItemPrice());

        return "customer";
    }

    @GetMapping("/register_success")
    public String registerSuccess(){
      return "register_success";
    }
}

