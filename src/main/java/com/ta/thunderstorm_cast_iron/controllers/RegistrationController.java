package com.ta.thunderstorm_cast_iron.controllers;

import com.ta.thunderstorm_cast_iron.model.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String processRegistrationForm(@ModelAttribute("registrationForm") RegistrationForm form) {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Users.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        Session sessionFactory = configuration.buildSessionFactory(builder.build()).openSession();
        Transaction transaction = sessionFactory.beginTransaction();
        Users user = new Users();
        user.setUserName(form.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(form.getPassword()));
        user.setEmail(form.getEmail());
        sessionFactory.persist(user);
        transaction.commit();
        sessionFactory.close();


        //System.out.println("Processing registration: " + form);
        System.out.println("Username: " + form.getUsername());
        return "/registration-success";
    }

}
