package com.aloranking.lfds.controllers;

import com.aloranking.lfds.models.Role;
import com.aloranking.lfds.models.UserRegistration;
import com.aloranking.lfds.repositories.RoleRepository;
import com.aloranking.lfds.repositories.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/lfds")
public class RegistrationController {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping( "/register")

    public ResponseEntity<UserRegistration> createUser (@RequestBody final UserRegistration userRegistration){

      UserRegistration userReg = new UserRegistration();
        Role role = roleRepository.findByRole("USER");
        userRegistration.setRole(role);
        userReg.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        userRegistration.setPassword(userReg.getPassword());




        userRegistrationRepository.saveAndFlush(userRegistration);

        return new ResponseEntity<>(userRegistration, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public UserRegistration getUser (@PathVariable Long id){
        return userRegistrationRepository.getOne(id);
    }

}
