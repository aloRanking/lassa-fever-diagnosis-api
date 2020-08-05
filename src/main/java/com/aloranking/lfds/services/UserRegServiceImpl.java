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

    @Override
    public void saveUser(UserRegistration user) {
        getUser(user.getUser_id()).setUser_id(user.getUser_id());
       getUser(user.getUser_id()).setRes_home_address(user.getRes_home_address());
       getUser(user.getUser_id()).setRes_state(user.getRes_state());

        userRegistrationRepository.saveAndFlush(user);

    }

    @Override
    public boolean isEmailAlreadyPresent(UserRegistration userRegistration) {

        UserRegistration user = userRegistrationRepository.findByEmail(userRegistration.getEmail());
        if (user == null) {
            return false;
        }else
            return true;
    }
}
