package com.security.Security.in.springboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // this will tell that use this security method don't go for default
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(customizer -> customizer.disable()); // avoid csrf
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated()); // no one able to access the page without autharization.
        http.formLogin(Customizer.withDefaults()); // it will implement the default login page and u can use that form provided by springBoot
        http.httpBasic(Customizer.withDefaults()); // now you can implement stuffs from postman also, because without this you are getting code of form not the data
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // it will give a new session id everytime , so that it is more secure.


        return http.build();
    }
}
