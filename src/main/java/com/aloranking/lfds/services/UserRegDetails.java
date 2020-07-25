package com.aloranking.lfds.services;

import com.aloranking.lfds.models.Role;
import com.aloranking.lfds.models.UserRegistration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserRegDetails implements UserDetails {

    private UserRegistration userRegistration;

    public UserRegDetails(UserRegistration userRegistration) {
        this.userRegistration = userRegistration;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = userRegistration.getRole();
        System.out.println(role);
        /*Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : authUser.getRole()){
            grantedAuthorities.add(new SimpleGrantedAuthority(roles.getName()));
        }*/
        return Arrays.asList( new SimpleGrantedAuthority(role.getRole()));
    }

    @Override
    public String getPassword() {
        return userRegistration.getPassword();
    }

    @Override
    public String getUsername() {
        return userRegistration.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
