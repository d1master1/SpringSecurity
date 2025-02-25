package com.example.springsecurity.service;

import com.example.springsecurity.model.AppUser;
import com.example.springsecurity.repo.AppUserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepo repo;

    public AppUserService(AppUserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser appUser = repo.findByEmail(email);

        if (appUser != null) {

            return User.withUsername(appUser.getEmail())
                    .password(appUser.getPassword())
                    .roles(appUser.getRole())
                    .build();

        }
        return null;
    }
}