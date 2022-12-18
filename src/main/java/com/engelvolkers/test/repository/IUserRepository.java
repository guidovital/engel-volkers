package com.engelvolkers.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engelvolkers.test.domain.entity.User;

/**
 * This class is used to access the User table
 * 
 * @author Guilherme Vital
 *
 */
public interface IUserRepository extends JpaRepository<User, String> {

    /**
     * Find user by email and password
     * 
     * @param email
     * @param pass
     * @return Optional<User>
     */
    Optional<User> findByEmailAndPassword(String email, String pass);

    /**
     * Find user by email
     * 
     * @param email
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);
}
