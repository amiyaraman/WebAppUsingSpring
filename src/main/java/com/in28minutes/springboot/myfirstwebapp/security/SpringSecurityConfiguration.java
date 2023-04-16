package com.in28minutes.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    //LDAP or Database-where ever you want to return username and password you make use of these things
    // TODO: 16-04-2023 Learn LDAP ,Mockito , Lombok and all the remaining topic
    //here inmemory
    //InMemoryUserDetailsManager

    @Bean
    public  InMemoryUserDetailsManager creatInMemoryUserDetailsManager() {

        UserDetails userDetails1 = createNewUser("in22years", "root");
        UserDetails userDetails2 = createNewUser("UserA" , "root");
        UserDetails userDetails3 = createNewUser("UserB" , "root");
        UserDetails userDetails4 = createNewUser("UserC" , "root");


        return  new InMemoryUserDetailsManager(userDetails1,userDetails2,userDetails3,userDetails4);
    }

    private UserDetails createNewUser(String userName, String password) {
        Function<String, String> passwordEncoder=input->passwordEncoder().encode(input);
        UserDetails userDetails = User
                .builder()
                .passwordEncoder(passwordEncoder)
                .username(userName)
                .password(password)
                .roles("USER","ADMIN")
                .build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }
}
