package com.rochelle.book_club.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rochelle.book_club.models.User;

public interface UserRepository extends CrudRepository <User, Long>{
    // Need to modify findByEmail
    Optional<User> findByEmail(String email);
}
