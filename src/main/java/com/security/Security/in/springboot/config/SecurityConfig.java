package com.security.Security.in.springboot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private UserDetailsService userDetailsService;

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


    // from this you can provide userName , password , and connect accordingly
    // this is for hard coded value, means not involves database
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("shivam")
//                .password("star123")
//                .roles("USER")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(user1);
//    }


    // for database we use authentication provider
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


}
