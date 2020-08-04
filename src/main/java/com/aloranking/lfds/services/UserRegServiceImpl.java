package com.aloranking.lfds.services;

import com.aloranking.lfds.models.UserRegistration;
import com.aloranking.lfds.repositories.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegServiceImpl  implements UserRegService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Override
    public UserRegistration getUser(Long id) {

        UserRegistration userRegistration = new UserRegistration();

        userRegistration = userRegistrationRepository.getOne(id);

        return userRegistration;

    }
}
