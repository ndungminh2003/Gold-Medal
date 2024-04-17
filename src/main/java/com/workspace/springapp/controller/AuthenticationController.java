package com.workspace.springapp.controller;


import com.workspace.springapp.config.JwtUtils;
import com.workspace.springapp.dao.UserDao;
import com.workspace.springapp.dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private JwtUtils jwtUtils;


    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDetails user = userDao.findUserByEmail(request.getEmail());
        if(user != null){
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error has occurred");

    }
}
