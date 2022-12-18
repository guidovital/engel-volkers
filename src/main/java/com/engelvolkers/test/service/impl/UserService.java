/**
 * 
 */
package com.engelvolkers.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engelvolkers.test.domain.entity.Property;
import com.engelvolkers.test.domain.entity.User;
import com.engelvolkers.test.exception.ObjectNotFoundException;
import com.engelvolkers.test.repository.IUserRepository;
import com.engelvolkers.test.service.IUserService;

import lombok.AllArgsConstructor;

/**
 * This class is the service layer for the User entity
 * 
 * @author Guilherme Vital
 *
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements IUserService {

    private IUserRepository repository;

    @Override
    public User findByEmailAndPassword(String email, String pass) {
        return repository.findByEmailAndPassword(email, pass)
                .orElseThrow(() -> new ObjectNotFoundException("Email and Password do not match"));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("No user with that email"));
    }

    @Override
    public User addProperty(String username, Property property) {
        var user = findByEmail(username);

        if (user != null && !user.getProperties().contains(property)) {
            user.getProperties().add(property);
            repository.save(user);
        }

        return user;
    }
}
