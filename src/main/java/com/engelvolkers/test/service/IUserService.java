package com.engelvolkers.test.service;

import java.util.List;

import com.engelvolkers.test.domain.entity.Property;
import com.engelvolkers.test.domain.entity.User;

/**
 * This class is the service layer for the User entity
 * 
 * @author Guilherme Vital
 *
 */
public interface IUserService {

    /**
     * Returns a user by its email and password
     * if it exists, otherwise throws an exception
     * 
     * @param email
     * @param pass
     * @return User
     */
    User findByEmailAndPassword(String email, String pass);

    /**
     * Returns all users
     * If there are no users, returns an empty list
     * 
     * @return List<User>
     */
    List<User> findAll();

    /**
     * Returns a user by its email
     * If it does not exist, throws an exception
     * 
     * @param username
     * @return User
     */
    User findByEmail(String email);

    /**
     * Adds a visit property to a user if user exists
     * if it does not exist, throws an exception
     * 
     * @param username
     * @param property
     * @return
     */
    User addProperty(String username, Property property);
}
