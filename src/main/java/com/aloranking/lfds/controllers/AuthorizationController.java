package com.aloranking.lfds.controllers;


import com.aloranking.lfds.exception.CustomException;
import com.aloranking.lfds.models.AuthenticationRequest;
import com.aloranking.lfds.models.AuthenticationResponse;
import com.aloranking.lfds.services.MyUserDetailService;
import com.aloranking.lfds.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/lfds")
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );


        }catch (BadCredentialsException e){
            throw new CustomException("Incorrect Username or Password");
        }
        final UserDetails userDetails = myUserDetailService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
