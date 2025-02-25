package com.example.springsecurity.repo;

import com.example.springsecurity.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Integer> {

    AppUser findByEmail(String email);
}