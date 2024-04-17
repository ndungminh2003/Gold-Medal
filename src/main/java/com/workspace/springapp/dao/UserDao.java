package com.workspace.springapp.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {

    private final static List<UserDetails> USERS = Arrays.asList(
            new User(
                    "minhdungnguyen2003@gmail.com",
                    "123456",
                    Collections.singleton(new SimpleGrantedAuthority("ADMIN"))
            ),
            new User(
                    "user@gmail.com",
                    "123456",
                    Collections.singleton(new SimpleGrantedAuthority("USER"))
            )
    );


    public UserDetails findUserByEmail(String email){
        System.out.println("abc");
        return USERS
                .stream()
                .filter(userDetails -> userDetails.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No user was found"))
                ;
    }
}
