package com.aloranking.lfds.services;


import com.aloranking.lfds.models.Role;
import com.aloranking.lfds.repositories.RoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppInit implements InitializingBean {


    @Autowired
private RoleRepository repo;

    @Override
public void afterPropertiesSet() throws Exception {
        boolean rolesNotPresent = repo.count() <= 0;



        if( ! rolesNotPresent ) {
            return;

        }else {
            createRoles();

        }

}



    public void createRoles(){
    List<Role> roles = new ArrayList<>();
    Role role = new Role("USER");
    Role role2 = new Role("ADMIN");

        roles.add(role2) ;
    roles.add(role);

    repo.saveAll(roles);
}
}
