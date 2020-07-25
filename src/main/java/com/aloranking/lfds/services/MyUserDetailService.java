package com.aloranking.lfds.services;

import com.aloranking.lfds.models.UserRegistration;
import com.aloranking.lfds.repositories.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserRegistration userRegistration = userRegistrationRepository.findByEmail(email);

        try {

            return new UserRegDetails(userRegistration);

        }catch (Exception e){

            return null;

        }


    }
}
