package com.aloranking.lfds.services;

import com.aloranking.lfds.models.UserRegistration;
import org.springframework.stereotype.Service;

@Service
public interface UserRegService {

    public UserRegistration getUser(Long id);
    public  void saveUser(UserRegistration user);
    public boolean isEmailAlreadyPresent(UserRegistration userRegistration);
}
