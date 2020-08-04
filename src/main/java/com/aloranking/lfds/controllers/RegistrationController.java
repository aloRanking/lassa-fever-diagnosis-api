package com.aloranking.lfds.controllers;

import com.aloranking.lfds.models.EmailRequest;
import com.aloranking.lfds.models.Role;
import com.aloranking.lfds.models.UserRegistration;
import com.aloranking.lfds.repositories.RoleRepository;
import com.aloranking.lfds.repositories.UserRegistrationRepository;
import com.aloranking.lfds.services.UserRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;

@RestController
@RequestMapping("api/v1/lfds")
public class RegistrationController {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private UserRegService userRegService;

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

    @PostMapping("/user")
    public UserRegistration getUserByMail (@RequestBody EmailRequest email){
        String userEmail = email.getEmail();

        return userRegistrationRepository.findByEmail(userEmail);
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody Map<Object, Object> updates,
                                        @PathVariable("id") Long id){

        UserRegistration existingUser = userRegService.getUser(id);

        updates.forEach((k,v) ->{
            Field field = ReflectionUtils.findField(UserRegistration.class, (org.springframework.util.ReflectionUtils.FieldFilter) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingUser, v);

                   });



        userRegistrationRepository.save(existingUser);
        return ResponseEntity.ok("resource updated");



    }
}
