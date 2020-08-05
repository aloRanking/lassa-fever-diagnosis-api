package com.aloranking.lfds.controllers;

import com.aloranking.lfds.exception.CustomException;
import com.aloranking.lfds.exception.UserAlreadyExistException;
import com.aloranking.lfds.exception.UserNotFoundException;
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

     boolean isAlreadyExists = userRegService.isEmailAlreadyPresent(userRegistration);

     if (isAlreadyExists)
         throw new UserAlreadyExistException("Email Already Exits");



        Role role = roleRepository.findByRole("USER");
        userRegistration.setRole(role);
        userRegistration.setPassword(passwordEncoder.encode(userRegistration.getPassword()));





        userRegistrationRepository.saveAndFlush(userRegistration);

        return new ResponseEntity<>(userRegistration, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<UserRegistration> getUser (@PathVariable Long id){
        try {
            UserRegistration existingUser = userRegService.getUser(id);
            return  new ResponseEntity<>(existingUser, HttpStatus.FOUND);

        }catch (Exception e) {

            throw new UserNotFoundException("this user with id " + id + "  does not exist");
        }


    }

    @PostMapping("/user")
    public ResponseEntity<UserRegistration> getUserByMail (@RequestBody EmailRequest emailRequest){

        UserRegistration userEmail = userRegistrationRepository.findByEmail(emailRequest.getEmail());
        if (userEmail==null)
            throw new UserNotFoundException("Email does not Exist");



        return new ResponseEntity(userEmail, HttpStatus.OK);
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<UserRegistration> updateUser(@RequestBody Map<Object, Object> updates,
                                        @PathVariable("id") Long id){

        UserRegistration existingUser = userRegService.getUser(id);
        if (existingUser == null)
            throw new UserNotFoundException("User Not found");



        updates.forEach((k,v) ->{

            Field field = org.springframework.util.ReflectionUtils.findField(UserRegistration.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingUser, v);

                   });

        System.out.println(existingUser);



        userRegService.saveUser(existingUser);
        return new ResponseEntity(existingUser, HttpStatus.ACCEPTED);



    }
}
