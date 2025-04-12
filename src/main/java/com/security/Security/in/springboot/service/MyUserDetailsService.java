package com.security.Security.in.springboot.service;

import com.security.Security.in.springboot.model.UserPrincipal;
import com.security.Security.in.springboot.model.Users;
import com.security.Security.in.springboot.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{

        Users user= userRepo.findByUsername(userName);

        if(user == null){
            System.out.println("user not found!");
            throw new UsernameNotFoundException("user not found!");
        }

        return new UserPrincipal(user);
    }

}
