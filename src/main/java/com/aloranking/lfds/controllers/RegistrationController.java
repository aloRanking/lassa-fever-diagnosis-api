package com.aloranking.lfds.controllers;

import com.aloranking.lfds.models.UserRegistration;
import com.aloranking.lfds.repositories.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/lfds")
public class RegistrationController {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @PostMapping( "/register")

    public ResponseEntity<UserRegistration> createUser (@RequestBody final UserRegistration userRegistration){
        System.out.println(userRegistration);

        userRegistrationRepository.saveAndFlush(userRegistration);

        return new ResponseEntity<>(userRegistration, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public UserRegistration getUser (@PathVariable Long id){
        return userRegistrationRepository.getOne(id);
    }

}
